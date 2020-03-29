package com.realcrap.bravo.ui.buisnesspage.services

import android.app.TimePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.R
import kotlinx.android.synthetic.main.service_row.view.*
import java.text.SimpleDateFormat
import java.util.*


class ServicesAdapter(
        val context : Context,
        val servicesList : List<Services>,
        private val listener: ServiceClickListener

) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        return ServiceViewHolder(layoutInflater.inflate(R.layout.service_row, parent, false))
    }

    override fun getItemCount(): Int = servicesList.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val item = servicesList[position]
        holder.bind(item)
        holder.itemView.bookButton.setOnClickListener {

            holder.itemView.bookButton.visibility = View.GONE
            holder.itemView.cancelButton.visibility = View.VISIBLE
            holder.itemView.cancelButton.animation = (AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left))
            listener.onServiceBookClicked(item.id, item.title, item.price)

        }
        holder.itemView.cancelButton.setOnClickListener {

            holder.itemView.bookButton.visibility = View.VISIBLE
            holder.itemView.cancelButton.visibility = View.GONE
            holder.itemView.bookButton.animation = (AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right))
            listener.onServiceCanceledClicked(item.id, item.title,item.price)


//            listener.onServiceCanceledClicked(position, item.id, time.toString())


        }


    }

    class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(services: Services) {
            itemView.tvserviceName.text = services.title
            itemView.tvserviceDesc.text = services.excerpt
            itemView.tvservicePrice.text = "Rs " + services.price
        }
    }
}

