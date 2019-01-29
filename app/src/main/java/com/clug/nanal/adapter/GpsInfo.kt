package com.clug.nanal.adapter

import android.annotation.TargetApi
import android.app.AlertDialog
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.provider.Settings
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.WindowManager
import com.clug.nanal.R
import android.support.v4.os.HandlerCompat.postDelayed




class GpsInfo(private val mContext: Context) : Service(), LocationListener {


    // 현재 GPS 사용유무

    internal var isGPSEnabled = false


    // 네트워크 사용유무

    internal var isNetworkEnabled = false

    // GPS 상태값

    //gps가 켜져있는지 확인

    var isGetLocation = false
        internal set

    internal var location: Location? = null

    internal var lat: Double = 0.toDouble() // 위도

    internal var lon: Double = 0.toDouble() // 경도


    protected var locationManager: LocationManager? = null


    //위도값 가져오기

    val latitude: Double
        get() {

            if (location != null) {

                lat = location!!.latitude

            }

            return lat

        }


    //경도값 가져오기

    val longitude: Double
        get() {

            if (location != null) {

                lon = location!!.longitude

            }

            return lon

        }


    init {
        getLocation()
    }


    @TargetApi(23)
    public fun getLocation(): Location? {

        if ((Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            return null
        }


        try {

            locationManager = mContext

                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager


            // GPS 정보 가져오기

            isGPSEnabled = locationManager!!

                    .isProviderEnabled(LocationManager.GPS_PROVIDER)


            // 현재 네트워크 상태 값 알아오기

            isNetworkEnabled = locationManager!!

                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)


            if (!isGPSEnabled && !isNetworkEnabled) {

                // GPS 와 네트워크사용이 가능하지 않을때 소스 구현

            } else {

                this.isGetLocation = true

                // 네트워크 정보로 부터 위치값 가져오기

                if (isNetworkEnabled) {

                    locationManager!!.requestLocationUpdates(

                            LocationManager.NETWORK_PROVIDER,

                            MIN_TIME_BW_UPDATES,

                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)


                    if (locationManager != null) {

                        location = locationManager!!

                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                        if (location != null) {

                            // 위도 경도 저장

                            lat = location!!.latitude

                            lon = location!!.longitude


                            Log.v("알림", "위도 : " + lat + "경도 " + lon)

                        }

                    }

                }


                if (isGPSEnabled) {

                    if (location == null) {

                        locationManager!!.requestLocationUpdates(

                                LocationManager.GPS_PROVIDER,

                                MIN_TIME_BW_UPDATES,

                                MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)

                        if (locationManager != null) {

                            location = locationManager!!

                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER)

                            if (location != null) {

                                lat = location!!.latitude

                                lon = location!!.longitude

                            }

                        }

                    }

                }

            }


        } catch (e: Exception) {

            e.printStackTrace()

        }

        return location

    }


    //gps종료

    fun stopUsingGPS() {

        if (locationManager != null) {

            locationManager!!.removeUpdates(this@GpsInfo)

        }

    }


    //gps설정 정보를 가져올 수 없을 때

    fun showSettingsAlert() {

        makeDialog()

    }


    override fun onBind(arg0: Intent): IBinder? {

        return null

    }


    override fun onLocationChanged(location: Location) {

        // TODO Auto-generated method stub


    }


    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        // TODO Auto-generated method stub


    }


    override fun onProviderEnabled(provider: String) {

        // TODO Auto-generated method stub


    }


    override fun onProviderDisabled(provider: String) {

        // TODO Auto-generated method stub


    }


    private fun makeDialog() {

        val alt_bld = AlertDialog.Builder(mContext)

        alt_bld.setMessage("GPS가 꺼져있습니다. \n설정창으로 가시겠습니까?").setCancelable(

                false).setPositiveButton("네"

        ) { dialog, id ->
            // 네 클릭

            val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

            mContext.startActivity(intent)
        }.setNegativeButton("아니오",

                object : DialogInterface.OnClickListener {

                    override fun onClick(dialog: DialogInterface, id: Int) {

                        // 아니오 클릭. dialog 닫기.

                        dialog.cancel()

                    }

                })

        val alert = alt_bld.create()


        // 대화창 클릭시 뒷 배경 어두워지는 것 막기

        alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);


        // 대화창 제목 설정

        alert.setTitle("GPS 사용 허가")


        // 대화창 아이콘 설정

        alert.setIcon(R.drawable.img_gpsinfo)

        // 대화창 배경 색 설정

        alert.window!!.setBackgroundDrawable(ColorDrawable(Color.argb(255, 149, 219, 209)))


        alert.show()

    }

    companion object {


        // 최소 GPS 정보 업데이트 거리 10미터

        private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 10


        // 최소 GPS 정보 업데이트 시간 밀리세컨(1분)

        private val MIN_TIME_BW_UPDATES = (1000 * 60 * 1).toLong()
    }


}