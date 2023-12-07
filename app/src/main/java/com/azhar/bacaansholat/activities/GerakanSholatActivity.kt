package com.azhar.bacaansholat.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.azhar.bacaansholat.R
import kotlinx.android.synthetic.main.activity_gerakan_sholat.toolbar

class GerakanSholatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerakan_sholat)

        val webView: WebView = findViewById<WebView>(R.id.webView)
        val video : String = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VKyRSN7nes0?si=KCdUgC1KenwYESYS\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"
        webView.loadData(video, "text/html", "utf-8")
        webView.getSettings().javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        toolbar.setOnClickListener{
            val intentHome = Intent(this@GerakanSholatActivity, MainActivity::class.java)
            startActivity(intentHome)
        }
    }
}