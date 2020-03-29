package com.realcrap.bravo.ui.mybooking.booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.realcrap.bravo.R
import kotlinx.android.synthetic.main.urbookings_row.view.*

class BookingAdapter(
        val list : List<Booking>
) : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookingViewHolder(layoutInflater.inflate(R.layout.urbookings_row, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val items = list[position]
        holder.bind(items)

    }


    class BookingViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bind(items: Booking) {
            itemView.urBookName.text = items.storename
            itemView.urBookDesc.text = items.unique_id
            itemView.urBookDate.text = items.storename
            itemView.urBookTotalAmount.text = items.totalamount
            itemView.urOrderPaymentMode.text = items.paymenttype
            itemView.urBookStatus.text = items.orderprocess
            itemView.urBookServices.text = items.services


        }

    }
}