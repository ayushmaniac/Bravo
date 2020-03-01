package com.realcrap.bravo.ui.buisnesspage.services

import android.app.TimePickerDialog
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.service_row.view.bookServiceButton
import kotlinx.android.synthetic.main.service_row.view.tvserviceName
import kotlinx.android.synthetic.main.service_row_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class ServicesViewHolder(parent : ViewGroup
,    private var itemSelectionListener: ItemSelectionListener<Services>
) : BaseItemViewHolder<Services,ServicesViewModel>(
        R.layout.service_row_item, parent
) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {


        itemView.bookServiceButton.setOnClickListener {
            itemSelectionListener(adapterPosition, "nowwhat")
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                itemView.bookServiceButton.setText(SimpleDateFormat("HH:mm").format(cal.time)+" "+"Change Time")
                itemView.cancelService.visibility = View.VISIBLE
                addService(viewModel.serviceId)
            }

            TimePickerDialog(itemView.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()



        }

        itemView.cancelService.setOnClickListener {

            itemView.bookServiceButton.setText("Book")
            itemView.cancelService.visibility = View.GONE

        }


    }

    private fun addService(serviceId: LiveData<String>) {




    }

    override fun setupObserver() {
        super.setupObserver()

        viewModel.serviceName.observe(this, Observer {

            itemView.tvserviceName.text = it.toString()

        })

        viewModel.serviceDesc.observe(this, Observer {

            itemView.tvserviceDesc.text = it.toString()

        })
        viewModel.servicePrice.observe(this, Observer {

            itemView.tvservicePrice.text = "Rs $it"

        })
    }




}