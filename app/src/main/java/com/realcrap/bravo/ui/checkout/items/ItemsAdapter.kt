package com.realcrap.bravo.ui.checkout.items

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.R
import com.realcrap.bravo.ui.offers.Transfer
import kotlinx.android.synthetic.main.service_item_row.view.*

class ItemsAdapter(
        val listItem : List<Transfer>
) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemsViewHolder(layoutInflater.inflate(R.layout.service_item_row, parent, false))
    }

override fun getItemCount(): Int  = listItem.size

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
       val item = listItem[position]
        holder.bindItems(item)

    }

    class ItemsViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bindItems(item: Transfer) {

            itemView.serviceContent.text = item.title + " - " + item.price+"INR"

        }

    }

}