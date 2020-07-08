package com.example.cozy.views.map

import android.content.Context
import android.graphics.Outline
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapAdapter (private val context: Context) : RecyclerView.Adapter<MapViewHolder>() {

    var datas = mutableListOf<MapData>()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_map, parent, false)

            /*
        view.bookstore.outlineProvider = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        object : ViewOutlineProvider(){
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(20, -50, view!!.width, (view.height).toInt(), 50f)
            }
        }
*/
        return MapViewHolder(view)

    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}