package com.clug.nanal.fragment

import android.os.Bundle
import android.os.StrictMode
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import com.clug.nanal.adapter.DustScreenWeather
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_homedust.*
import java.net.URL




class HomeDustFragment : Fragment() {

    lateinit var dustweather: DustScreenWeather
    var faceDegree: Int = 0
    lateinit var location_Large: String
    lateinit var location_Medium: String
    lateinit var location_Small: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homedust, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSharedPreferenceController()

        dustweather = DustScreenWeather()

        setDegree()

        setDetailView()
    }

    fun getSharedPreferenceController() {
        location_Large = SharedPreferenceController.getUserLocationLarge(activity!!)
        location_Medium = SharedPreferenceController.getUserLocationMedium(activity!!)
        location_Small = SharedPreferenceController.getUserLocationSmall(activity!!)
    }

    fun dustParsing(dust: DustScreenWeather) {
        var inItem = false
        var inSidoName = false
        var inSggName = false
        var inUmdName = false
        var inTmX = false
        var inTmY = false
        var sidoName: String? = null
        var sggName: String? = null
        var umdName: String? = null
        var tmX: String? = null
        var tmY: String? = null

        try {
            val url = URL("http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/"
                    + "getTMStdrCrdnt?umdName=" + dust.getDong() + "&pageNo=1&numOfRows=10&"
                    + "ServiceKey=5ny%2BgWDJ3dvUmT9kVraVmNfshj0c5IYbmljioUf41qMAC5CzEeHV%2B1IEFclnyHZmo3TfwvmxQL8n2D8IvF%2F7fA%3D%3D"
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
                        if (parser.name == "sidoName") {
                            inSidoName = true
                        }
                        if (parser.name == "sggName") {
                            inSggName = true
                        }
                        if (parser.name == "umdName") {
                            inUmdName = true
                        }
                        if (parser.name == "tmX") {
                            inTmX = true
                        }
                        if (parser.name == "tmY") {
                            inTmY = true
                        }
                        if (parser.name == "message") { //message 태그를 만나면 에러 출력
                            //                      status1.setText(status1.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                    }

                    XmlPullParser.TEXT//parser가 내용에 접근했을때
                    -> {
                        if (inSidoName) {
                            sidoName = parser.text
                            inSidoName = false
                        }
                        if (inSggName) {
                            sggName = parser.text
                            inSggName = false
                        }
                        if (inUmdName) {
                            umdName = parser.text
                            inUmdName = false
                        }
                        if (inTmX) {
                            tmX = parser.text
                            inTmX = false
                        }
                        if (inTmY) {
                            tmY = parser.text
                            inTmY = false
                        }
                    }

                    XmlPullParser.END_TAG -> if (parser.name == "item") {

                        if (sggName == dust.getBorough()) {
                            dust.setTmx(tmX!!)
                            dust.setTmy(tmY!!)
                        }

                        inItem = false
                    }
                }
                parserEvent = parser.next()
            }
        } catch (e: Exception) {

        }

        var inStationName = false
        var inTm = false
        var stationName: String? = null
        var tm: String? = null
        var shortestWay = Integer.MAX_VALUE.toDouble()

        try {
            val url = URL("http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?"
                    + "tmX=" + dust.getTmx() + "&tmY=" + dust.getTmy() + "&pageNo=1&numOfRows=10&"
                    + "ServiceKey=5ny%2BgWDJ3dvUmT9kVraVmNfshj0c5IYbmljioUf41qMAC5CzEeHV%2B1IEFclnyHZmo3TfwvmxQL8n2D8IvF%2F7fA%3D%3D"
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
                        if (parser.name == "stationName") {
                            inStationName = true
                        }
                        if (parser.name == "tm") {
                            inTm = true
                        }
                        if (parser.name == "message") { //message 태그를 만나면 에러 출력

                        }
                    }

                    XmlPullParser.TEXT//parser가 내용에 접근했을때
                    -> {
                        if (inStationName) {
                            stationName = parser.text
                            inStationName = false
                        }
                        if (inTm) {
                            tm = parser.text
                            inTm = false
                        }
                    }

                    XmlPullParser.END_TAG -> if (parser.name == "item") {

                        val distance = java.lang.Double.parseDouble(tm!!)
                        if (distance < shortestWay) {
                            shortestWay = distance
                            dust.setStationName(stationName!!)
                        }

                        inItem = false
                    }
                }
                parserEvent = parser.next()
            }
        } catch (e: Exception) {

        }

        var inPm10Value = false
        var inPm25Value = false
        var pm10Value = 0
        var pm25Value = 0

        try {
            val url = URL("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?"
                    + "stationName=" + dust.getStationName() + "&dataTerm=daily&pageNo=1&numOfRows=1&ServiceKey="
                    + "5ny%2BgWDJ3dvUmT9kVraVmNfshj0c5IYbmljioUf41qMAC5CzEeHV%2B1IEFclnyHZmo3TfwvmxQL8n2D8IvF%2F7fA%3D%3D&ver=1.3"
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
                        if (parser.name == "pm10Value") {
                            inPm10Value = true
                        }
                        if (parser.name == "pm25Value") {
                            inPm25Value = true
                        }
                        if (parser.name == "message") { //message 태그를 만나면 에러 출력

                        }
                    }

                    XmlPullParser.TEXT//parser가 내용에 접근했을때
                    -> {
                        if (inPm10Value) {
                            pm10Value = Integer.parseInt(parser.text)
                            dust.setPm10Value(pm10Value)
                            inPm10Value = false
                        }
                        if (inPm25Value) {
                            pm25Value = Integer.parseInt(parser.text)
                            dust.setPm25Value(pm25Value)
                            inPm25Value = false
                        }
                    }

                    XmlPullParser.END_TAG -> if (parser.name == "item") {

                        if (pm10Value <= 30)
                            dust.setPm10Degree(1)
                        else if (pm10Value > 30 && pm10Value <= 50)
                            dust.setPm10Degree(2)
                        else if (pm10Value > 50 && pm10Value <= 100)
                            dust.setPm10Degree(3)
                        else
                            dust.setPm10Degree(4)

                        if (pm25Value <= 15)
                            dust.setPm25Degree(1)
                        else if (pm25Value > 15 && pm25Value <= 25)
                            dust.setPm25Degree(2)
                        else if (pm25Value > 25 && pm25Value <= 50)
                            dust.setPm25Degree(3)
                        else
                            dust.setPm25Degree(4)

                        inItem = false
                    }
                }
                parserEvent = parser.next()
            }
        } catch (e: Exception) {

        }

    }


    fun setDegree() {
        StrictMode.enableDefaults();

        dustweather.init(location_Large, location_Medium, location_Small);

        dustParsing(dustweather);

        tv_homedust_normal_degree.text = "" + dustweather.getPm10Value() + "㎛/㎥"

        tv_homedust_normal_state.text = setDegreeInttoString(dustweather.getPm10Degree())

        tv_homedust_small_degree.text = "" + dustweather.getPm25Value() + "㎛/㎥"

        tv_homedust_small_degree2.text = setDegreeInttoString(dustweather.getPm25Degree())
    }

    fun setDegreeInttoString(input_data: Int): String {
        when (input_data) {
            1 -> {
                return "좋음"
            }
            2 -> {
                faceDegree = faceDegree + 1
                return "보통"
            }
            3 -> {
                faceDegree = faceDegree + 2
                return "나쁨"
            }
            4 -> {
                faceDegree = faceDegree + 3
                return "매우 나쁨"
            }
            else -> return ""
        }
    }

    fun setDetailView() {
        if (faceDegree == 0) {
            tv_homedust_title.text = "미세먼지 매우 적은 날"
            tv_homeweather_info.text = "공기가 매우 맑아요! 숨을 한 번 깊게 쉬어볼까요?"
            img_homedust_face.setImageResource(R.drawable.img_very_satisfied)
        } else if (faceDegree == 1) {
            tv_homedust_title.text = "미세먼지 적은 날"
            tv_homeweather_info.text = "밖에 나가도 안심할 수 있는 공기에요."
            img_homedust_face.setImageResource(R.drawable.img_satisfied)
        } else if (faceDegree == 2) {
            tv_homedust_title.text = "미세먼지 보통인 날"
            tv_homeweather_info.text = "나쁘진 않지만 장시간의 외출은 삼가해주세요."
            img_homedust_face.setImageResource(R.drawable.img_neutral)
        } else if (faceDegree == 3) {
            tv_homedust_title.text = "미세먼지 많은 날"
            tv_homeweather_info.text = "마스크 꼭 챙기시고 가급적 외출을 자제해주세요!"
            img_homedust_face.setImageResource(R.drawable.img_dissatisfied)
        } else {
            tv_homedust_title.text = "미세먼지 매우 많은 날"
            tv_homeweather_info.text = "마스크 꼭 챙기시고 가급적 외출을 자제해주세요!"
            img_homedust_face.setImageResource(R.drawable.img_very_dissatisfied)
        }
    }
}
