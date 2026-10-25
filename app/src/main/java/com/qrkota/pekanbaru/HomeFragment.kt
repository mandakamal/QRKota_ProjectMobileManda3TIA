package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.qrkota.pekanbaru.data.FacilityAdapter
import com.qrkota.pekanbaru.data.FacilityRepository

/** Beranda: shortcut menuju fitur dan daftar fasilitas populer. */
class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val prefs = requireContext().getSharedPreferences("QRKotaPrefs", android.content.Context.MODE_PRIVATE)
        val username = prefs.getString("username", "Pengguna") ?: "Pengguna"
        view.findViewById<TextView>(R.id.tvGreeting).text = "Selamat Datang, $username"
        val popular = FacilityRepository.all.take(3)
        val list = view.findViewById<ListView>(R.id.listPopular)
        list.adapter = FacilityAdapter(requireContext(), popular)
        list.setOnItemClickListener { _, _, position, _ ->
            (activity as? MainActivity)?.showFacilityDetail(popular[position].id)
        }
        view.findViewById<MaterialCardView>(R.id.cardScan).setOnClickListener { (activity as? MainActivity)?.selectTab(R.id.nav_scan) }
        view.findViewById<MaterialCardView>(R.id.cardSearch).setOnClickListener { (activity as? MainActivity)?.selectTab(R.id.nav_search) }
        view.findViewById<MaterialCardView>(R.id.cardHistory).setOnClickListener { (activity as? MainActivity)?.openHistory() }
        view.findViewById<MaterialCardView>(R.id.cardFavorites).setOnClickListener { (activity as? MainActivity)?.selectTab(R.id.nav_favorites) }
        view.findViewById<EditText>(R.id.etHomeSearch).setOnEditorActionListener { textView, _, _ ->
            (activity as? MainActivity)?.openSearch(textView.text.toString())
            true
        }
    }
}
