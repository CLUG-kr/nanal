package com.clug.nanal.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.db.SharedPreferenceController
import org.xmlpull.v1.XmlPullParser
import android.util.Xml.newPullParser
import org.xmlpull.v1.XmlPullParserFactory
import com.clug.nanal.adapter.HomeScreenWeather
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import android.os.StrictMode
import kotlinx.android.synthetic.main.fragment_homeweather.*
import org.jetbrains.anko.support.v4.toast


class HomeWeatherFragment : Fragment() {

    lateinit var name: String
    lateinit var sex: String
    var tempo: Int = 0

    lateinit var homeweather : HomeScreenWeather

    lateinit var location_Large: String
    lateinit var location_Medium: String
    lateinit var location_Small: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homeweather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeweather = HomeScreenWeather()

        getSharedPreferenceController()

        setTempo()

        setCloset()


    }

    fun getSharedPreferenceController() {
        name = SharedPreferenceController.getUserName(activity!!)
        sex = SharedPreferenceController.getUserSex(activity!!)
        tempo = SharedPreferenceController.getUserTempo(activity!!)
        location_Large = SharedPreferenceController.getUserLocationLarge(activity!!)
        location_Medium = SharedPreferenceController.getUserLocationMedium(activity!!)
        location_Small = SharedPreferenceController.getUserLocationSmall(activity!!)
    }

    fun setTempo() {
        //민지 위치 정보 가지고 뷰 설정하기

        var mNow: Long = 0
        lateinit var mDate: Date
        var tFormat = SimpleDateFormat("kkmm")
        var dFormat = SimpleDateFormat("yyyyMMdd")

        fun getDate(): String {
            mDate = Date(mNow)
            return dFormat.format(mDate)
        }

        fun getTime(): String {
            mNow = System.currentTimeMillis()
            //return tFormat.format(mNow);
            val now = Integer.parseInt(tFormat.format(mNow))
            return if (now % 100 / 10 < 2) {
                if (now / 1000 == 0) {
                    "0" + Integer.toString(now - 70)
                } else {
                    Integer.toString(now - 70)
                }
            } else tFormat.format(mNow)
        }

        fun homeParsing(home: HomeScreenWeather) { //여기서 파싱
            var inItem = false
            var inCategory = false
            var inObsrValue = false

            var baseDate: String? = null
            var baseTime: String? = null
            var nx: String? = null
            var ny: String? = null
            var category: String? = null
            var obsrValue: String? = null

            val homeweather = HomeScreenWeather()

            baseTime = getTime()
            baseDate = getDate()
            nx = "55"
            ny = "127"

            try {
                val url = URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?"
                        + "ServiceKey=P7ecMdue5u1fIQnAFq9%2FvJyEUmiawIYoiKumue8dwTY2DDnV5huh5eMmKHydjQntHZVue16UMb3%2FXE1TermSnw%3D%3D&"
                        + "base_date=" + baseDate + "&base_time=" + baseTime + "&nx=" + nx + "&ny=" + ny + "&numOfRows=8"
                )

                val parserCreator = XmlPullParserFactory.newInstance()
                val parser = parserCreator.newPullParser()
                parser.setInput(url.openStream(), null)

                var parserEvent = parser.eventType
                println("파싱시작합니다.")

                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    when (parserEvent) {
                        XmlPullParser.START_TAG//parser가 시작 태그를 만나면 실행
                        -> {
                            if (parser.name == "category") {
                                inCategory = true
                            }
                            if (parser.name == "obsrValue") {
                                inObsrValue = true
                            }
                            if (parser.name == "message") { //message 태그를 만나면 에러 출력
                                //                      status1.setText(status1.getText() + "에러");
                                //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                            }
                        }

                        XmlPullParser.TEXT//parser가 내용에 접근했을때
                        -> {
                            if (inCategory) {
                                category = parser.text
                                inCategory = false
                            }
                            if (inObsrValue) {
                                obsrValue = parser.text
                                inObsrValue = false
                            }
                        }

                        XmlPullParser.END_TAG -> if (parser.name == "item") {

                            if (category == "T1H") {
                                home.setTemperature(obsrValue!!)
                            }

                            inItem = false
                        }
                    }
                    parserEvent = parser.next()
                }
            } catch (e: Exception) {
                //        status1.setText("에러가..났습니다..." + e.getMessage());
            }

            var inFcstValue = false
            var fcstValue: String? = null

            try {
                val url = URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastSpaceData?"
                        + "ServiceKey=P7ecMdue5u1fIQnAFq9%2FvJyEUmiawIYoiKumue8dwTY2DDnV5huh5eMmKHydjQntHZVue16UMb3%2"
                        + "FXE1TermSnw%3D%3D&base_date=" + baseDate + "&base_time=0200&nx=" + nx + "&ny=" + ny + "&numOfRows=120"
                )

                val parserCreator = XmlPullParserFactory.newInstance()
                val parser = parserCreator.newPullParser()
                parser.setInput(url.openStream(), null)

                var parserEvent = parser.eventType
                println("파싱시작합니다.")

                while (parserEvent != XmlPullParser.END_DOCUMENT) {
                    when (parserEvent) {
                        XmlPullParser.START_TAG//parser가 시작 태그를 만나면 실행
                        -> {
                            if (parser.name == "category") {
                                inCategory = true
                            }
                            if (parser.name == "fcstValue") {
                                inFcstValue = true
                            }
                            if (parser.name == "message") { //message 태그를 만나면 에러 출력
                                //                          status1.setText(status1.getText()+"에러");
                            }
                        }

                        XmlPullParser.TEXT//parser가 내용에 접근했을때
                        -> {
                            if (inCategory) {
                                category = parser.text
                                inCategory = false
                            }
                            if (inFcstValue) {
                                fcstValue = parser.text
                                inFcstValue = false
                            }
                        }

                        XmlPullParser.END_TAG -> if (parser.name == "item") {

                            if (category == "TMN") {
                                home.setLowest(fcstValue!!)
                            } else if (category == "TMX") {
                                home.setHighest(fcstValue!!)
                            }

                            inItem = false

                        }
                    }
                    parserEvent = parser.next()
                }
            } catch (e: Exception) {
                //            status1.setText("에러가..났습니다..." + e.getMessage());
            }

        }


        StrictMode.enableDefaults()

        homeParsing(homeweather)

        tv_homeweather_now_degree.text=homeweather.getTemperature()+"ºC"
        tv_homeweather_mm_degree.text=homeweather.getHighest()+"/"+homeweather.getLowest()+"ºC"


        toast("기온 : " + homeweather.getTemperature() + "최고기온 : " + homeweather.getHighest() + "최저기온 : " + homeweather.getLowest())

    }

    fun setCloset() {
        //나래 사용자 정보가지고 옷 설정하기
    }
}