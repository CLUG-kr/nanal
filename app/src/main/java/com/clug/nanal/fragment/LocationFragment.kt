package com.clug.nanal.fragment

import android.content.SharedPreferences
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.adapter.GpsInfo
import kotlinx.android.synthetic.main.fragment_location.*
import org.jetbrains.anko.support.v4.toast
import java.util.*
import android.content.pm.PackageManager
import android.location.Address
import android.util.Log
import java.io.IOException
import kotlin.collections.ArrayList
import android.support.v4.content.ContextCompat
import android.os.Build
import com.clug.nanal.db.SharedPreferenceController


class LocationFragment : Fragment() {

    private val PERMISSIONS_ACCESS_FINE_LOCATION = 1000

    private val PERMISSIONS_ACCESS_COARSE_LOCATION = 1001

    private var isAccessFineLocation = false

    private var isAccessCoarseLocation = false

    private var isPermission = false

    private var gps: GpsInfo? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setViewClickListener()

        setView()

        callPermission()
    }

    private fun setView() {
        tv_location_notify.text = "설정된 위치 : " + SharedPreferenceController.getUserLocationLarge(activity!!) + " " + SharedPreferenceController.getUserLocationMedium(activity!!) + " " + SharedPreferenceController.getUserLocationSmall(activity!!)
    }

    private fun setViewClickListener() {

        btn_location_now.setOnClickListener {
            getGpsLocation()
        }
        btn_location_select.setOnClickListener {

        }
    }

    fun getGpsLocation() {

        if (!isPermission) {

            callPermission();

            return

        }

        gps = GpsInfo(activity!!)

        // GPS 사용유무 가져오기

        if (gps?.location != null) {

            //GPSInfo를 통해 알아낸 위도값과 경도값

            val latitude = gps!!.latitude

            val longitude = gps!!.longitude

//            toast("lat : " + latitude + " lon : " + longitude)

            //Geocoder

            val gCoder = Geocoder(activity!!, Locale.getDefault())

            var addr: List<Address>? = null


            try {

                addr = gCoder.getFromLocation(latitude, longitude, 1)

                val a = addr!![0]

                toast(a.adminArea + " " + a.subLocality + " " + a.thoroughfare)

                tv_location_notify.text = "설정된 위치 : " + a.adminArea + " " + a.subLocality+ " " + a.thoroughfare

                SharedPreferenceController.setUserLocationLarge(activity!!, a.adminArea)
                SharedPreferenceController.setUserLocationMedium(activity!!, a.subLocality)
                SharedPreferenceController.setUserLocationSmall(activity!!, a.thoroughfare)



/*
    for (i in 0..a.getMaxAddressLineIndex()) {

                    //여기서 변환된 주소 확인할  수 있음

                    Log.v("알림", "AddressLine(" + i + ")" + a.getAddressLine(i) + "\n")

                }
 */


            } catch (e: IOException) {

                e.printStackTrace()

            }



            if (addr != null) {

                if (addr.size == 0) {

                    toast("주소정보 없음")

                }

            }


        } else {

            // GPS 를 사용할수 없으므로

            gps!!.showSettingsAlert()

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            isAccessFineLocation = true


        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION

                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


            isAccessCoarseLocation = true

        }


        if (isAccessFineLocation && isAccessCoarseLocation) {

            isPermission = true

        }

    }

    private fun callPermission() {

        // Check the SDK version and whether the permission is already granted or not.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED)) {


            requestPermissions(

                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),

                    PERMISSIONS_ACCESS_FINE_LOCATION)


        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_COARSE_LOCATION)

                        != PackageManager.PERMISSION_GRANTED)) {


            requestPermissions(

                    arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),

                    PERMISSIONS_ACCESS_COARSE_LOCATION)

        } else {

            isPermission = true

        }

    }

}