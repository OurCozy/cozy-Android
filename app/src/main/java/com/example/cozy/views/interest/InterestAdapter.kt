package com.example.cozy.views.interest

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.BookstoreInfo
import com.example.cozy.views.map.MapDetailActivity
import kotlinx.android.synthetic.main.fragment_interest.*

class InterestAdapter (private val context: Context, val data : MutableList<BookstoreInfo>, val onClick : (BookstoreInfo) -> Unit) : RecyclerView.Adapter<InterestViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bookstore_list, parent, false)

        /*
    view.bookstore.outlineProvider = @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    object : ViewOutlineProvider(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun getOutline(view: View?, outline: Outline?) {
            outline?.setRoundRect(20, -50, view!!.width, (view.height).toInt(), 50f)
        }
    }
*/
        return InterestViewHolder(view, onClick)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: InterestViewHolder, position: Int) {
        holder.bind(data[position])

        holder.bookmark.setImageResource(R.drawable.ic_small_bookmark_selected)
        
        holder.bookmark.setOnClickListener {
            val sharedPref = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            val token = sharedPref.getString("token", "token")
            val header = mutableMapOf<String, String?>()
            header["Content-Type"] = "application/json"
            header["token"] = token

            RequestToServer.service.requestBookmarkUpdate(data[position].bookstoreIdx, header).customEnqueue(
                onError = {Log.d("RESPONSE", "error")},
                onSuccess = {
                    if(it.success) {
                        Log.d("RESPONSE", it.message)
                        data.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, data.size)
                    }
                    else Log.d("RESPONSE", it.message)
                }
            )
        }
    }
}