package com.realcrap.bravo.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T: Any, VH : BaseItemViewHolder<T, BaseItemViewModel<T>>>(

        private val dataList : ArrayList<T>


):RecyclerView.Adapter<VH>() {

    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        holder.onStart()
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onStop()
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dataList[position])

    }

    fun appendData(dataList: List<T>){



    }
}
