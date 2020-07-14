package com.example.cozy.views.map

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R

class MapAdapter(
    private val context: Context,
    val datas : MutableList<MapData>, val onClick : (MapData) -> Unit
) : RecyclerView.Adapter<MapViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bookstore_list, parent, false)

        return MapViewHolder(view, onClick)

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}