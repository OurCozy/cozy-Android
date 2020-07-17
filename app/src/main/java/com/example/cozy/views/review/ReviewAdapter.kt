package com.example.cozy.views.review

import android.content.Context
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R
import com.example.cozy.network.RequestToServer
import com.example.cozy.network.customEnqueue
import com.example.cozy.network.responseData.AllReviewData

class ReviewAdapter(private val context: Context, val data : MutableList<AllReviewData>,val onEmpty: () -> Unit) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_map_comment, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(data[position])

        val sharedPref = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
        val token = sharedPref.getString("token", "token")
        val header = mutableMapOf<String, String?>()
        header["Content-Type"] = "application/json"
        header["token"] = token
        Log.d("이름 : ", data[position].nickname)
        if(sharedPref.getString("nickname", "cozy") != data[position].nickname){
            holder.moreBtn.visibility = View.GONE
        }


        holder.moreBtn.setOnClickListener {
            val popup = PopupMenu(context, holder.moreBtn)
            popup.menuInflater.inflate(R.menu.review_menu, popup.menu)

            // PopupMenu 글자 가운데 정렬
            var item = popup.menu.getItem(0)
            val editString = SpannableString("수정")
            editString.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, editString.length, 0)
            item.setTitle(editString)

            item = popup.menu.getItem(1)
            val deleteString = SpannableString("삭제")
            deleteString.setSpan(AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, editString.length, 0)
            item.setTitle(deleteString)

            // 메뉴 클릭 이벤트 처리
            popup.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.edit -> {
                        RequestToServer.service.requestReviewModi(data[position].reviewIdx, header).customEnqueue(
                            onError = {},
                            onSuccess = {
                                if(it.success){

                                }

                            }

                        )
                    }
                    R.id.delete -> {
                        RequestToServer.service.requestReviewDel(data[position].reviewIdx, header).customEnqueue(
                            onError = {Log.d("RESPONSE", "error")},
                            onSuccess = {
                                if(it.success) {
                                    Log.d("RESPONSE", it.message)
                                    data.removeAt(position)
                                    notifyItemRemoved(position)
                                    notifyItemRangeChanged(position, data.size)
                                    if (data.size == 0) onEmpty()
                                }
                            }
                        )
                    }
                }
                true
            }
            popup.show()
        }
    }

}