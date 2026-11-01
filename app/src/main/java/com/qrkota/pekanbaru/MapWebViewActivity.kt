package com.qrkota.pekanbaru

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.qrkota.pekanbaru.databinding.ActivityMapWebViewBinding
import android.net.Uri

/** Materi pertemuan 5: WebView dengan Toolbar dan tombol kembali. */
class MapWebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMapWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Lokasi Fasilitas"
        binding.toolbar.setNavigationOnClickListener { finish() }

        val lokasi = intent.getStringExtra("lokasi").orEmpty()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.openstreetmap.org/search?query=" + Uri.encode(lokasi))

        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            binding.appBar.setExpanded(scrollY <= oldScrollY, true)
        }
    }
}
