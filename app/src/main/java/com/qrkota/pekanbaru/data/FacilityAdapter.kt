package com.qrkota.pekanbaru.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.qrkota.pekanbaru.R

/** Custom Adapter: mengubah satu objek Facility menjadi tampilan item_facility.xml. */
class FacilityAdapter(
    context: Context,
    private val facilities: List<Facility>
) : ArrayAdapter<Facility>(context, 0, facilities) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Gunakan kembali baris lama jika tersedia agar ListView lebih efisien.
        val row = convertView ?: LayoutInflater.from(getContext())
            .inflate(R.layout.item_facility, parent, false)
        val facility = facilities[position]
        row.findViewById<ImageView>(R.id.imgFacility).setImageResource(facility.imageRes)
        row.findViewById<TextView>(R.id.tvFacilityName).text = facility.name
        row.findViewById<TextView>(R.id.tvFacilityCategory).text = facility.category
        row.findViewById<TextView>(R.id.tvFacilityDescription).text = facility.shortDescription
        return row
    }
}
