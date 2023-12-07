package com.azhar.bacaansholat.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toolbar
import com.azhar.bacaansholat.R
import kotlinx.android.synthetic.main.activity_ayat_kursi.toolbar
import kotlinx.android.synthetic.main.activity_jadwal_sholat.kota
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_asyar
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_isya
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_maghrib
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_subuh
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_tanggal
import kotlinx.android.synthetic.main.activity_jadwal_sholat.tv_zuhur
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.collections.ArrayList


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class JadwalSholatActivity : AppCompatActivity() {

    private var listDaftarKota: MutableList<DaftarKota> = ArrayList()
    private var mDaftarKotaAdapter: ArrayAdapter<DaftarKota>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sholat)



//        mDaftarKotaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDaftarKota)
        mDaftarKotaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDaftarKota)

        mDaftarKotaAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        kota.adapter = mDaftarKotaAdapter
        kota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val kota = mDaftarKotaAdapter!!.getItem(position)
                if (kota != null) {
                    loadJadwal(kota.id)
                }
            }
        }

        loadKota()
    }

    private fun loadJadwal(id: Int?){
        try{
            val idKota = id.toString()
            val current = SimpleDateFormat("yyyy-MM-dd")
            val tanggal = current.format(Date())

            val url = "https://api.banghasan.com/sholat/format/json/jadwal/kota/$idKota/tanggal/$tanggal"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener{
                override fun onPostExecute(result: String) {
                    Log.d("JadwalData", result)
                    try {
                        val jsonObj = JSONObject(result)
                        val objJadwal = jsonObj.getJSONObject("jadwal")
                        val obData = objJadwal.getJSONObject("data")

                        tv_tanggal.text = obData.getString("tanggal")
                        tv_subuh.text = obData.getString("subuh")
                        tv_zuhur.text = obData.getString("dzuhur")
                        tv_asyar.text = obData.getString("ashar")
                        tv_maghrib.text = obData.getString("maghrib")
                        tv_isya.text = obData.getString("isya")

                        Log.d("dataJadwal", obData.toString())
                    }catch (e: JSONException){
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun loadKota(){
        try{
            val url = "https://api.banghasan.com/sholat/format/json/kota"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener{
                override fun onPostExecute(result: String) {
                    Log.d("KotaData", result)
                    try {
                        val jsonObj = JSONObject(result)
                        val jsonArray = jsonObj.getJSONArray("kota")
                        var daftarKota: DaftarKota?
                        for(i in 0 until jsonArray.length()){
                            val obj = jsonArray.getJSONObject(i)
                            daftarKota = DaftarKota()
                            daftarKota.id = obj.getInt("id")
                            daftarKota.nama = obj.getString("nama")
                            listDaftarKota!!.add(daftarKota)
                        }
                        mDaftarKotaAdapter!!.notifyDataSetChanged()
                    }catch (e: JSONException){
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}