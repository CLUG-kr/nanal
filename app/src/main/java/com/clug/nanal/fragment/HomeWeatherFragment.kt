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
import android.support.v4.view.ViewCompat.setY
import android.support.v4.view.ViewCompat.setX
import org.jetbrains.anko.db.INTEGER


class HomeWeatherFragment : Fragment() {

    lateinit var name: String
    lateinit var sex: String
    var tempo: Int = 0

    lateinit var homeweather: HomeScreenWeather

    lateinit var location_Large: String
    lateinit var location_Medium: String
    lateinit var location_Small: String

    var mNow: Long = 0
    lateinit var mDate: Date
    var tFormat = SimpleDateFormat("kkmm")
    var dFormat = SimpleDateFormat("yyyyMMdd")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homeweather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSharedPreferenceController()

        homeweather = HomeScreenWeather()

        setInfoView()

        setCloset()

        refresh_homeweather.setOnRefreshListener {

            getSharedPreferenceController()

            setInfoView()

            setCloset()

            refresh_homeweather.isRefreshing = false
        }
    }


    fun getSharedPreferenceController() {
        name = SharedPreferenceController.getUserName(activity!!)
        sex = SharedPreferenceController.getUserSex(activity!!)
        tempo = SharedPreferenceController.getUserTempo(activity!!)
        location_Large = SharedPreferenceController.getUserLocationLarge(activity!!)
        location_Medium = SharedPreferenceController.getUserLocationMedium(activity!!)
        location_Small = SharedPreferenceController.getUserLocationSmall(activity!!)
    }

    fun getDate(): String {
        mDate = Date(mNow)
        return dFormat.format(mDate)
    }

    fun getTime(): String {
        mNow = System.currentTimeMillis()
        //return tFormat.format(mNow);
        val now = Integer.parseInt(tFormat.format(mNow))
        return if (now % 100 / 10 <= 2) {
            if (now / 1000 == 0) {
                "0" + Integer.toString(now - 70)
            } else {
                Integer.toString(now - 70)
            }
        } else tFormat.format(mNow)
    }

    fun locateParsing(home: HomeScreenWeather) {

        var city: String? = null
        var borough: String? = null
        var dong: String? = null
        var x: String? = null
        var y: String? = null

        var inCity = false
        var inBorough = false
        var inDong = false
        var inX = false
        var inY = false
        var inText = false

        try {
            val url = URL("https://raw.githubusercontent.com/CLUG-kr/nanal/master/weather_location.xml")

            val parserCreator = XmlPullParserFactory.newInstance()
            val parser = parserCreator.newPullParser()
            parser.setInput(url.openStream(), null)

            var parserEvent = parser.eventType
            println("파싱시작합니다.")

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                when (parserEvent) {
                    XmlPullParser.START_TAG//parser가 시작 태그를 만나면 실행
                    -> {
                        if (parser.name == "city") {
                            inCity = true
                        }
                        if (parser.name == "borough") {
                            inBorough = true
                        }
                        if (parser.name == "dong") {
                            inDong = true
                        }
                        if (parser.name == "x") {
                            inX = true
                        }
                        if (parser.name == "y") {
                            inY = true
                        }
                        if (parser.name == "message") { //message 태그를 만나면 에러 출력
                            //                      status1.setText(status1.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                    }

                    XmlPullParser.TEXT//parser가 내용에 접근했을때
                    -> {
                        if (inCity) {
                            city = parser.text
                            inCity = false
                        }
                        if (inBorough) {
                            borough = parser.text
                            inBorough = false
                        }
                        if (inDong) {
                            dong = parser.text
                            inDong = false
                        }
                        if (inX) {
                            x = parser.text
                            inX = false
                        }
                        if (inY) {
                            y = parser.text
                            inY = false
                        }
                    }

                    XmlPullParser.END_TAG -> if (parser.name == "text") {

                        if (city == home.getCity()) {
                            if (borough == home.getBorough()) {
                                if (dong == home.getDong()) {
                                    home.setX(x!!)
                                    home.setY(y!!)
                                    toast(x + " " + y)
                                }
                            }
                        }

                        inText = false
                    }
                }
                parserEvent = parser.next()
            }
        } catch (e: Exception) {
            //        status1.setText("에러가..났습니다..." + e.getMessage());
        }

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

        homeweather.setCity(location_Large)
        homeweather.setBorough(location_Medium)
        homeweather.setDong(location_Small)

        baseTime = getTime()
        baseDate = getDate()
        nx = home.getX()
        ny = home.getY()

        try {
            val url = URL("http://newsky2.kma.go.kr/service/SecndSrtpdFrcstInfoService2/ForecastGrib?"
                    + "ServiceKey=5ny%2BgWDJ3dvUmT9kVraVmNfshj0c5IYbmljioUf41qMAC5CzEeHV%2B1IEFclnyHZmo3TfwvmxQL8n2D8IvF%2F7fA%3D%3D&"
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
                    + "ServiceKey=5ny%2BgWDJ3dvUmT9kVraVmNfshj0c5IYbmljioUf41qMAC5CzEeHV%2B1IEFclnyHZmo3TfwvmxQL8n2D8IvF%2F7fA%3D%3D"
                    + "&base_date=" + baseDate + "&base_time=0200&nx=" + nx + "&ny=" + ny + "&numOfRows=120"
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

    fun setInfoView() {

        StrictMode.enableDefaults()

        homeweather.setCity(location_Large)
        homeweather.setBorough(location_Medium)
        homeweather.setDong(location_Small)


        toast(location_Large + " " + location_Medium + " " + location_Small)
        locateParsing(homeweather)


        toast("x좌표 : " + homeweather.getX() + " y좌표 : " + homeweather.getY())

        homeParsing(homeweather)

        tv_homeweather_now_degree.text = homeweather.getTemperature() + "ºC"
        tv_homeweather_mm_degree.text = homeweather.getHighest() + "/" + homeweather.getLowest() + "ºC"

        toast("하늘 상태 : " + homeweather.getSky())
    }

    fun setCloset() {
        //나래 사용자 정보가지고 옷 설정하기
    }
}