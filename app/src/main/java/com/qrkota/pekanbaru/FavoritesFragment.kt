package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.qrkota.pekanbaru.data.FacilityAdapter
import com.qrkota.pekanbaru.data.FacilityRepository

/** Menampilkan hanya fasilitas yang ID-nya tersimpan pada daftar favorit. */
class FavoritesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onResume() { super.onResume(); view?.let { populate(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { populate(view) }

    private fun populate(view: View) {
        val prefs = requireContext().getSharedPreferences("QRKotaPrefs", android.content.Context.MODE_PRIVATE)
        val ids = prefs.getStringSet("favoriteIds", emptySet()) ?: emptySet()
        val items = FacilityRepository.all.filter { ids.contains(it.id) }
        val list = view.findViewById<ListView>(R.id.listFavorites)
        val empty = view.findViewById<TextView>(R.id.tvEmptyFavorites)
        empty.visibility = if (items.isEmpty()) View.VISIBLE else View.GONE
        list.visibility = if (items.isEmpty()) View.GONE else View.VISIBLE
        list.adapter = FacilityAdapter(requireContext(), items)
        list.setOnItemClickListener { _, _, position, _ -> (activity as? MainActivity)?.showFacilityDetail(items[position].id) }
        list.setOnItemLongClickListener { _, _, position, _ ->
            (activity as? MainActivity)?.toggleFavorite(items[position].id)
            Toast.makeText(requireContext(), "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
            populate(view)
            true
        }
    }
}
