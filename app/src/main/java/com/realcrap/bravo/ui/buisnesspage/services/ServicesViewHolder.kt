package com.realcrap.bravo.ui.buisnesspage.services

import android.app.TimePickerDialog
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.service_row.view.*
import kotlinx.android.synthetic.main.service_row.tvserviceName
import kotlinx.android.synthetic.main.service_row.view.*
import kotlinx.android.synthetic.main.service_row.view.tvserviceDesc
import kotlinx.android.synthetic.main.service_row.view.tvservicePrice
import java.text.SimpleDateFormat
import java.util.*

class ServicesViewHolder(parent : ViewGroup,
                         var itemSelectionListener: ItemSelectionListener<Services>
) : BaseItemViewHolder<Services,ServicesViewModel>(
        R.layout.service_row, parent
) {
    var service : Services? = null
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {


        itemView.bookButton.setOnClickListener {
            service?.let { it1 -> itemSelectionListener(adapterPosition, it1) }

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                itemView.timeSelectedText.setText(SimpleDateFormat("HH:mm").format(cal.time))
                itemView.timeSelectedText.visibility = View.VISIBLE
                itemView.editOrder.visibility = View.VISIBLE
                itemView.bookButton.visibility = View.GONE
//                addService(viewModel.serviceId)
            }

            TimePickerDialog(itemView.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show()


            itemView.cancelButton.setOnClickListener{

                itemView.editOrder.visibility = View.GONE
                itemView.bookButton.visibility = View.VISIBLE
            }
        }

//        itemView.cancelService.setOnClickListener {
//
//            itemView.bookServiceButton.setText("Book")
//            itemView.cancelService.visibility = View.GONE
//
//        }




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

        viewModel.wholeService.observe(this, Observer {
            service = it

        })
    }




}