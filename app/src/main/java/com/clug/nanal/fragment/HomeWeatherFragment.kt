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
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import android.os.StrictMode
import kotlinx.android.synthetic.main.fragment_homeweather.*
import org.jetbrains.anko.support.v4.toast
import android.support.v4.view.ViewCompat.setY
import android.support.v4.view.ViewCompat.setX
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.jetbrains.anko.db.INTEGER
import com.clug.nanal.adapter.HomeScreenWeather
import kotlin.collections.ArrayList


class HomeWeatherFragment : Fragment() {

    enum class Outer constructor(internal val num: Int, internal val warm: Int) {
        CARDIGAN(21, 2), ZIPUP(22, 3), JACKET(23, 4), LEATHER(24, 5), COAT(25, 7), PADDING(26, 10)
    }

    enum class Top constructor(internal val num: Int, internal val warm: Int) {
        SLEEVELESS(11, 0), SHORT_TSHIRT(12, 0), SHORT_SHIRT(13, 0), TSHIRT(14, 1),
        SHIRT(15, 1), POLAR(16, 6), MTM(17, 3), HOOD(18, 3), KNIT(19, 5)
    }

    enum class Bottom constructor(internal val num: Int, internal val warm: Int) {
        SHORT_PANTS(1, 0), PANTS(2, 1), THICK_PANTS(3, 3), STOCKING(4, 1), SKIRT(5, 0), LEGGINGS(6, 2)
    }

    lateinit var name: String
    lateinit var sex: String
    var tempo: Float = 0.0F

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

    fun setSkyState(input_data: Int) {

        val requestOptions = RequestOptions()

        when (input_data) {
            0 -> {
                tv_homeweather_title.text = "정보 없음"
                tv_homeweather_info.text = ""

            }
            1 -> {
                tv_homeweather_title.text = "비 오는 날"
                tv_homeweather_info.text = ""
                Glide.with(activity!!)
                        .setDefaultRequestOptions(requestOptions)
                        .load("https://github.com/CLUG-kr/nanal/blob/master/mise/rain.png?raw=true")
                        .thumbnail(0.5f)
                        .into(img_homeweather_weather_icon)
            }
            2 -> {
                tv_homeweather_title.text = "비 또는 눈"
                tv_homeweather_info.text = ""

            }
            3 -> {
                tv_homeweather_title.text = "눈 오는 날"
                tv_homeweather_info.text = ""
            }
            4 -> {
                tv_homeweather_title.text = "오늘은 맑음"
                tv_homeweather_info.text = ""
                Glide.with(activity!!)
                        .setDefaultRequestOptions(requestOptions)
                        .load("https://github.com/CLUG-kr/nanal/blob/master/mise/sun.png?raw=true")
                        .thumbnail(0.5f)
                        .into(img_homeweather_weather_icon)
            }
            5 -> {
                tv_homeweather_title.text = "구름 조금"
                tv_homeweather_info.text = ""
                Glide.with(activity!!)
                        .setDefaultRequestOptions(requestOptions)
                        .load("https://github.com/CLUG-kr/nanal/blob/master/mise/blur.png?raw=true")
                        .thumbnail(0.5f)
                        .into(img_homeweather_weather_icon)
            }
            6 ->{
                tv_homeweather_title.text = "구름 많음"
                tv_homeweather_info.text = ""
                Glide.with(activity!!)
                        .setDefaultRequestOptions(requestOptions)
                        .load("https://github.com/CLUG-kr/nanal/blob/master/mise/cloud.png?raw=true")
                        .thumbnail(0.5f)
                        .into(img_homeweather_weather_icon)
            }
            7 ->{
                tv_homeweather_title.text = "흐림"
                tv_homeweather_info.text = ""
                Glide.with(activity!!)
                        .setDefaultRequestOptions(requestOptions)
                        .load("https://github.com/CLUG-kr/nanal/blob/master/mise/fog.png?raw=true")
                        .thumbnail(0.5f)
                        .into(img_homeweather_weather_icon)
            }
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

    private fun getTime(): String {
        mNow = System.currentTimeMillis()
        val now = Integer.parseInt(tFormat.format(mNow))
        if (now % 100 / 10 < 2) {
            if (now / 100 == 1) {
                return "00" + Integer.toString(now - 70)
            }
            return if (now / 1000 == 0) {
                "0" + Integer.toString(now - 70)
            } else {
                Integer.toString(now - 70)
            }
        }
        return if ((now - 2400) / 100 == 0) {
            "00" + Integer.toString(now - 2400)
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
            val url = URL("https://raw.githubusercontent.com/CLUG-kr/nanal/master/weather_location2.xml")

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

                        if (city.equals(home.getCity())) {
                            if (borough.equals(home.getBorough())) {
                                if (dong.equals("없음")) {
                                    home.setX(x!!);
                                    home.setY(y!!);
                                }
                                if (dong.equals(home.getDong())) {
                                    home.setX(x!!);
                                    home.setY(y!!);
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
                        if (category == "PTY") {
                            if (obsrValue != "0") {
                                home.setSky(Integer.parseInt(obsrValue!!))
                            }
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

        if (Integer.parseInt(getTime()) < 200) {
            baseDate = Integer.toString(Integer.parseInt(getDate()) - 1);
        }

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
                        } else if (category == "SKY") {
                            if (home.getSky() == -1) {
                                home.setSky(Integer.parseInt(fcstValue!!) + 3)
                            }
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

        locateParsing(homeweather)

        homeParsing(homeweather)

        tv_homeweather_now_degree.text = homeweather.getTemperature() + "ºC"
        //SharedPreferenceController.setNowTempo(activity!!, java.lang.Float.parseFloat(homeweather.getTemperature()))
        tv_homeweather_mm_degree.text = homeweather.getHighest() + "/" + homeweather.getLowest() + "ºC"

        setSkyState(homeweather.getSky())
    }

    fun abs(num: Float): Float {
        return if (num >= 0)
            num
        else
            -num
    }

    fun rounding(num: Float): Int {
        return if (num - num.toInt() >= 0.5) {
            num.toInt() + 1
        } else
            num.toInt()
    }

    fun sort(arr: IntArray): IntArray {
        var temp: Int
        for (i in arr.indices) {
            if (arr[i] == 0) arr[i] = 100
        }
        for (i in arr.indices) {
            for (j in 1 until arr.size - i) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1]
                    arr[j - 1] = arr[j]
                    arr[j] = temp
                }
            }
        }
        for (i in arr.indices) {
            if (arr[i] == 100) arr[i] = 0
        }
        return arr
    }

    fun SexStringtoInt(s: String): Int {
        if (s.equals("남")) {
            return 1
        } else {
            return 2
        }
    }

    fun combOfBottom(remain: Int, gender: Int): IntArray {
        val bottom = IntArray(2)
        val rand = Random()
        when (remain) {
            0 -> {
                if (gender == 2) { //여자인 경우
                    if (rand.nextInt(2) == 0) {  //반반 확률로 치마 or 바지
                        bottom[0] = Bottom.SHORT_PANTS.num
                    } else {
                        bottom[0] = Bottom.SKIRT.num
                    }
                } else {  //남자인 경우 바지만
                    bottom[0] = Bottom.SHORT_PANTS.num
                }
            }

            1 -> {
                if (gender == 2) { //여자인 경우
                    if (rand.nextInt(2) == 0) {  //반반 확률로 치마 or 바지
                        bottom[0] = Bottom.PANTS.num
                    } else {
                        bottom[0] = Bottom.STOCKING.num
                        bottom[1] = Bottom.SKIRT.num
                    }
                } else {  //남자인 경우
                    bottom[0] = Bottom.SHORT_PANTS.num
                }
            }

            2 -> {
                if (gender == 1)
                    bottom[0] = Bottom.PANTS.num
                else
                    bottom[0] = Bottom.LEGGINGS.num
            }

            else -> {
                bottom[0] = Bottom.THICK_PANTS.num
            }
        }
        return bottom
    }

    fun combOfTop(remain: Int, gender: Int): IntArray {
        val top = IntArray(3)
        val rand = Random()
        val num: Int
        when (remain) {
            0 -> {
                num = rand.nextInt(3)
                if (num == 0) {
                    if (gender == 1)
                        top[0] = Top.SHORT_TSHIRT.num  //남자인 경우 티만
                    else
                        top[0] = Top.SLEEVELESS.num
                } else if (num == 1) {
                    top[0] = Top.SHORT_TSHIRT.num
                } else {
                    top[0] = Top.SHORT_SHIRT.num
                }
            }
            1 -> {
                num = rand.nextInt(2)
                if (num == 0)
                    top[0] = Top.TSHIRT.num
                else
                    top[0] = Top.SHIRT.num
            }
            2 -> {
                top[0] = Top.TSHIRT.num
                top[1] = Top.SHIRT.num
            }
            3 -> {
                num = rand.nextInt(2)
                if (num == 1)
                    top[0] = Top.MTM.num
                else
                    top[0] = Top.HOOD.num
            }
            4 -> {
                num = rand.nextInt(4)
                if (num == 0) {
                    top[0] = Top.TSHIRT.num
                    top[1] = Top.MTM.num
                } else if (num == 1) {
                    top[0] = Top.SHIRT.num
                    top[1] = Top.MTM.num
                } else if (num == 2) {
                    top[0] = Top.TSHIRT.num
                    top[1] = Top.HOOD.num
                } else {
                    top[0] = Top.SHIRT.num
                    top[1] = Top.HOOD.num
                }
            }
            5 -> {
                top[0] = Top.KNIT.num
            }
            6 -> {
                num = rand.nextInt(2)
                if (num == 0)
                    top[0] = Top.POLAR.num
                else {
                    top[0] = Top.SHIRT.num
                    top[1] = Top.KNIT.num
                }
            }
            7 -> {
                top[0] = Top.POLAR.num
                top[1] = Top.SHIRT.num
            }
            8 -> {
                top[0] = Top.TSHIRT.num
                top[1] = Top.POLAR.num
                top[2] = Top.SHIRT.num
            }
            else -> {
                top[0] = Top.POLAR.num
                top[1] = Top.HOOD.num
            }
        }
        return top
    }

    fun selectClothes(refTemp: Float, temp: Float, gender: Int): IntArray { //무슨 옷 추천할지 고르는 함수
        val clothes = IntArray(6)
        val x = refTemp - temp
        var min = 10000f  //min==맞추려는 온도와 겉옷의 차이
        var remain = x
        val prob = x * x  //겉옷 입을지 말지에 대한 확률
        val rand = Random()
        if (x >= 1) {  //기준 온도보다 춥다
            if (rand.nextInt(100) <= prob) {  //겉옷 입는다면
                clothes[0] = Outer.CARDIGAN.num
                for (outer in Outer.values()) {
                    if (abs(x / 2 - outer.warm) < min) {
                        min = abs(x / 2 - outer.warm)
                        remain = x - outer.warm
                        clothes[0] = outer.num
                    }
                }
                //하의 구하기
                remain /= 4f
                System.arraycopy(combOfBottom(rounding(remain), gender), 0,
                        clothes, 1, 2)

                //상의 구하기
                remain *= 3f
                System.arraycopy(combOfTop(rounding(remain), gender), 0,
                        clothes, 3, 3)
            } else {
                //겉옷 안 입는다면 5분의 1 하의
                remain = x / 5
                System.arraycopy(combOfBottom(rounding(remain), gender), 0,
                        clothes, 0, 2)
                //나머지는 상의
                remain *= 4f
                System.arraycopy(combOfTop(rounding(remain), gender), 0,
                        clothes, 2, 3)
            }
        } else if (x < 0) { //기준 온도보다 덥다면
            remain = abs(x / 5)
            System.arraycopy(combOfBottom(rounding(remain), gender), 0,
                    clothes, 0, 2)
            //나머지는 상의
            remain *= 4f
            System.arraycopy(combOfTop(rounding(remain), gender), 0,
                    clothes, 2, 3)
        } else { //기준 온도보다 0~1도 차이난다면
            val num = rand.nextInt(2)
            if (num == 0) {
                System.arraycopy(combOfBottom(1, gender), 0, clothes, 0, 1)
                System.arraycopy(combOfTop(0, gender), 0, clothes, 1, 1)
            } else {
                System.arraycopy(combOfBottom(0, gender), 0, clothes, 0, 1)
                System.arraycopy(combOfTop(1, gender), 0, clothes, 1, 1)
            }
        }
        return sort(clothes)
    }

    fun setCloset() {
        val clothes: IntArray = selectClothes(SharedPreferenceController.getUserTempo(activity!!), SharedPreferenceController.getNowTempo(activity!!), SexStringtoInt(SharedPreferenceController.getUserSex(activity!!)))
        var i: Int = 0

        while (clothes[i] != 0) {
            toast("" + clothes[i])
            i++
        }
    }

}