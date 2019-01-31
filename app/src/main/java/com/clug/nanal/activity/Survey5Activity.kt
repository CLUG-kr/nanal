package com.clug.nanal.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.clug.nanal.R
import kotlinx.android.synthetic.main.activity_survey2.*
import kotlinx.android.synthetic.main.activity_survey3.*
import kotlinx.android.synthetic.main.activity_survey4_1.*
import kotlinx.android.synthetic.main.activity_survey4_2.*
import kotlinx.android.synthetic.main.activity_survey4_3.*
import kotlinx.android.synthetic.main.activity_survey5.*
import java.util.*

class Survey5Activity : AppCompatActivity(){
    enum class Outer private constructor(internal val num: Int, internal val warm: Int) {
        CARDIGAN(21, 2), ZIPUP(22, 3), JACKET(23, 4), LEATHER(24, 5), COAT(25, 7), PADDING(26, 10)
    }

    enum class Top private constructor(internal val num: Int, internal val warm: Int) {
        SLEEVELESS(11, 0), SHORT_TSHIRT(12, 0), SHORT_SHIRT(13, 0), TSHIRT(14, 1),
        SHIRT(15, 1), POLAR(16, 6), MTM(17, 3), HOOD(18, 3), KNIT(19, 5)
    }

    enum class Bottom private constructor(internal val num: Int, internal val warm: Int) {
        SHORT_PANTS(1, 0), PANTS(2, 1), THICK_PANTS(3, 3), STOCKING(4, 1), SKIRT(5, 0), LEGGINGS(6, 2)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey5)

        btn_survey5_ok.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    internal fun getRefTemp(refTemp: Int, userTemp: Int, type: Int): Int {
        var userTemp = userTemp
        val soCold = findViewById<View>(R.id.soCold) as RadioButton
        val cold = findViewById<View>(R.id.cold) as RadioButton
        val soHot = findViewById<View>(R.id.soHot) as RadioButton
        val hot = findViewById<View>(R.id.hot) as RadioButton

        when (type) {
            1 -> {  //추위 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 4
                else if (cold.isChecked)
                    userTemp += 2
                else if (soHot.isChecked)
                    userTemp -= 2
                else if (hot.isChecked) userTemp -= 1
            }
            2 -> {  //더위 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 2
                else if (cold.isChecked)
                    userTemp += 1
                else if (soHot.isChecked)
                    userTemp -= 4
                else if (hot.isChecked) userTemp -= 2

            }
            3 -> {  //둘 다 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 2
                else if (cold.isChecked)
                    userTemp += 1
                else if (soHot.isChecked)
                    userTemp -= 2
                else if (hot.isChecked) userTemp -= 1
            }
            4 -> {  //둘 다 잘 안타는 사람
                if (soCold.isChecked)
                    userTemp += 4
                else if (cold.isChecked)
                    userTemp += 2
                else if (soHot.isChecked)
                    userTemp -= 4
                else if (hot.isChecked) userTemp -= 2
            }
        }
        return refTemp / userTemp / 2
    }

    fun onlyOneChecked(v: View) {
        val soCold = findViewById<View>(R.id.soCold) as RadioButton
        val cold = findViewById<View>(R.id.cold) as RadioButton
        val fine = findViewById<View>(R.id.fine) as RadioButton
        val hot = findViewById<View>(R.id.hot) as RadioButton
        val soHot = findViewById<View>(R.id.soHot) as RadioButton

        when (v.id) {
            R.id.soCold -> {
                cold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.cold -> {
                soCold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.fine -> {
                soCold.isChecked = false
                cold.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.hot -> {
                soCold.isChecked = false
                cold.isChecked = false
                fine.isChecked = false
                soHot.isChecked = false
            }
            R.id.soHot -> {
                soCold.isChecked = false
                cold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
            }
        }
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
}