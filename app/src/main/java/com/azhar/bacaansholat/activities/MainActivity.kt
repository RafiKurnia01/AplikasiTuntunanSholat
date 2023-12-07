package com.azhar.bacaansholat.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.azhar.bacaansholat.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.ayat_pendek
import kotlinx.android.synthetic.main.main.bacaan_sholat
import kotlinx.android.synthetic.main.main.doa
import kotlinx.android.synthetic.main.main.gerakan_sholat
import kotlinx.android.synthetic.main.main.jadwal_sholat
import kotlinx.android.synthetic.main.main.niat_sholat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

//        linearNiatShalat.setOnClickListener(this)
//        linearBacaanShalat.setOnClickListener(this)
//        linearAyatKursi.setOnClickListener(this)

        niat_sholat.setOnClickListener(this)
        bacaan_sholat.setOnClickListener(this)
        ayat_pendek.setOnClickListener(this)
        doa.setOnClickListener(this)
        gerakan_sholat.setOnClickListener(this)
        jadwal_sholat.setOnClickListener(this)



    }

    override fun onClick(view: View) {
        when (view.id) {
//            R.id.linearNiatShalat -> {
//                val intentNiat = Intent(this@MainActivity, NiatShalatActivity::class.java)
//                startActivity(intentNiat)
//            }
//            R.id.linearBacaanShalat -> {
//                val intentBacaan = Intent(this@MainActivity, BacaanShalatActivity::class.java)
//                startActivity(intentBacaan)
//            }
//            R.id.linearAyatKursi -> {
//                val intentAyatKursi = Intent(this@MainActivity, AyatKursiActivity::class.java)
//                startActivity(intentAyatKursi)
//            }

            R.id.niat_sholat -> {
                val intentNiatSholat = Intent(this@MainActivity, NiatShalatActivity::class.java)
                startActivity(intentNiatSholat)
            }

            R.id.bacaan_sholat -> {
                val intentBacaanSholat = Intent(this@MainActivity, BacaanShalatActivity::class.java)
                startActivity(intentBacaanSholat)
            }
            R.id.ayat_pendek -> {
                val intentAyatPendek = Intent(this@MainActivity, AyatPendekActivity::class.java)
                startActivity(intentAyatPendek)
            }
            R.id.doa -> {
                val intentDoa = Intent(this@MainActivity, DoaActivity::class.java)
                startActivity(intentDoa)
            }
            R.id.gerakan_sholat -> {
                val intentGerakanSholat = Intent(this@MainActivity, GerakanSholatActivity::class.java)
                startActivity(intentGerakanSholat)
            }
            R.id.jadwal_sholat -> {
                val intentJadwalSholat = Intent(this@MainActivity, JadwalSholatActivity::class.java)
                startActivity(intentJadwalSholat)
            }
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val activityWindow = activity.window
            val layoutParams = activityWindow.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            activityWindow.attributes = layoutParams
        }
    }
}