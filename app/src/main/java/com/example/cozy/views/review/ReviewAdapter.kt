package com.example.cozy.views.review

import android.content.Context
import android.text.Layout
import android.text.SpannableString
import android.text.style.AlignmentSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.cozy.R

class ReviewAdapter(private val context: Context) : RecyclerView.Adapter<ReviewViewHolder>() {
    var data = mutableListOf<ReviewData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_map_comment, parent, false)
        return ReviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(data[position])

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
                        Toast.makeText(context, "edit!", Toast.LENGTH_LONG).show()
                    }
                    R.id.delete -> {
                        Toast.makeText(context, "delete!", Toast.LENGTH_LONG).show()
                    }
                }
                true
            }
            popup.show()
        }
    }

}