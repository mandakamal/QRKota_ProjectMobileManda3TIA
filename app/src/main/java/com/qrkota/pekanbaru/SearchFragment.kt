package com.qrkota.pekanbaru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.qrkota.pekanbaru.data.FacilityRepository

/** Halaman Cari memakai SimpleAdapter dengan layout dua baris bawaan Android. */
class SearchFragment : Fragment() {
    private var queryOnOpen: String = ""
    private var category: String = "Semua"
    fun setInitialQuery(value: String) { queryOnOpen = value }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val search = view.findViewById<EditText>(R.id.etSearch)
        val list = view.findViewById<ListView>(R.id.listSearch)
        search.setText(queryOnOpen)
        val buttons = listOf(
            view.findViewById<Button>(R.id.filterAll) to "Semua",
            view.findViewById<Button>(R.id.filterPark) to "Taman",
            view.findViewById<Button>(R.id.filterEducation) to "Pendidikan",
            view.findViewById<Button>(R.id.filterMuseum) to "Museum"
        )
        fun render() {
            val q = search.text.toString().trim().lowercase()
            val results = FacilityRepository.all.filter { facility ->
                val matchQuery = q.isBlank() || facility.name.lowercase().contains(q) || facility.category.lowercase().contains(q) || facility.address.lowercase().contains(q)
                val matchCategory = category == "Semua" || facility.category.equals(category, true) || (category == "Museum" && facility.name.contains("Museum", true))
                matchQuery && matchCategory
            }
            val rows = results.map { facility ->
                hashMapOf<String, String>("title" to facility.name, "desc" to "${facility.category} · ${facility.shortDescription}")
            }
            val displayRows = if (rows.isEmpty()) {
                listOf(hashMapOf<String, String>("title" to "Tidak ada hasil", "desc" to "Coba kata kunci atau kategori lain."))
            } else rows
            list.adapter = SimpleAdapter(requireContext(), displayRows, android.R.layout.simple_list_item_2, arrayOf("title", "desc"), intArrayOf(android.R.id.text1, android.R.id.text2))
            list.setOnItemClickListener { _, _, position, _ ->
                if (position < results.size) (activity as? MainActivity)?.showFacilityDetail(results[position].id)
            }
            list.setOnItemLongClickListener { _, _, position, _ ->
                if (position < results.size) {
                    val added = (activity as? MainActivity)?.toggleFavorite(results[position].id) ?: false
                    Toast.makeText(requireContext(), if (added) "Ditambahkan ke favorit" else "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
                }
                true
            }
        }
        buttons.forEach { (button, value) -> button.setOnClickListener { category = value; render() } }
        search.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { render() }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
        render()
    }
}
