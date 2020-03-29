package com.realcrap.bravo.ui.buisnesspage

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.buisnesspage.services.ServiceClickListener
import com.realcrap.bravo.ui.buisnesspage.services.ServicesAdapter
import com.realcrap.bravo.ui.checkout.Checkout
import com.realcrap.bravo.ui.offers.Transfer
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_buisnesscord.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class Buisness : BaseActivity<BuisnessViewModel>(), ServiceClickListener {
    var selectedDate : String? = null
    var selectedTime : String? = null
    var cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val day = cal.get(Calendar.DAY_OF_MONTH)


    private val transferList = arrayListOf<Transfer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uniqueId=intent.getStringExtra("unique_id")
        buisnessProgress.visibility = View.VISIBLE
        viewModel.loadServices(uniqueId)

    }

    override fun provideLayoutId(): Int = R.layout.activity_buisnesscord

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager



    override fun setupView(savedInstanceState: Bundle?) {

        proceedButton.setOnClickListener {

            if(selectedDate!==null){
                val intent = Intent(applicationContext, Checkout::class.java)
                intent.putExtra("transfer", transferList)
                intent.putExtra("seltime", selectedTime)
                intent.putExtra("seldate", selectedDate)
                startActivity(intent)
            }
            else
            {
                Toaster.show(this, "Please select the date and try again because we are not magicians")

            }

        }

            setupSpinner()

        buisnessSelectDate.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, yearO, monthOfYear, dayOfMonth ->
                updateDate(yearO, monthOfYear, dayOfMonth)
            }, year, month, day)
            dpd.show()
        }

        }

    private fun updateDate(yearO: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar : Calendar = Calendar.getInstance()
        calendar.timeInMillis = 0
        calendar.set(yearO, monthOfYear, dayOfMonth)
        val chooseDate : Date = calendar.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        selectedDate = dateFormat.format(chooseDate)
        buisnessSelectDate.text = DateFormat.getDateInstance(DateFormat.FULL).format(chooseDate)
        buisnessSelectDate.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13F)

    }


    private fun setupSpinner() {
        val time = arrayOf(
                "7:00-8:00AM",
                "8:00-9:00AM",
                "9:00-10:00AM",
                "10:00-11:00AM",
                "11:00-12:00AM",
                "12:00-1:00PM",
                "1:00-2:00PM",
                "2:00-3:00PM",
                "3:00-4:00PM",
                "4:00-5:00PM",
                "5:00-6:00PM",
                "6:00-7:00PM",
                "7:00-8:00PM",
                "8:00-9:00PM")

       if(buisnessSelectTime!=null) {
           val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, time)
           buisnessSelectTime.adapter = arrayAdapter
           arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)


           buisnessSelectTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

               override fun onNothingSelected(parent: AdapterView<*>?) {
               }

               override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                   selectedTime = time[position]

               }

           }


       }
    }


    override fun setupObservers() {
        super.setupObservers()


        viewModel.buisnessData.observe(this, Observer {
            it.data?.run {
                buisnessProgress.visibility = View.GONE
                servicesRecyclerView.apply {
                    layoutManager = linearLayoutManager.apply {
                        true
                    }
                    adapter = ServicesAdapter(this@Buisness, it.data, this@Buisness)
                }
            }
        })
        viewModel.buisnessProgress.observe(this, Observer {

                buisnessProgress.visibility = if(it) View.VISIBLE else View.GONE

        })

        viewModel.buisnessName.observe(this, Observer {
            merchantName.text = it
        })
        viewModel.buisnessType.observe(this, Observer {
            merchantType.text = it
        })

        viewModel.buisnessAddress.observe(this, Observer {
            merchantAddress.text = it
        })

        viewModel.buisnessRating.observe(this, Observer {
            merchantRating.text = it
        })

        viewModel.buisnessError.observe(this, Observer {

            if(it==true){

                sad.visibility = View.VISIBLE
                sad.playAnimation()

            }
        })


    }

    override fun onServiceBookClicked(id: String,title : String, price : String) {
        transferList.add(Transfer(id, title, price))
        if(transferList.isNotEmpty()){
            proceedLayout.visibility = View.VISIBLE

        }
    }

    override fun onServiceCanceledClicked(item: String, title : String, price : String) {
       val toRemove =  transferList.indexOf(Transfer(item, title, price))
        transferList.removeAt(toRemove)
        Log.d("CHECK", transferList.toString())
        if(transferList.isEmpty()){
            proceedLayout.visibility = View.GONE

        }

    }


}
