package com.example.cozy

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomItemDecoration(context: Context, private val num: Int) : RecyclerView.ItemDecoration() {

    var size = dpToPx(context, num)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1)
            outRect.bottom = size
    }

    private fun dpToPx(context: Context, dp:Int):Int{
        return return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(),
            context.resources.displayMetrics).toInt()
    }
}