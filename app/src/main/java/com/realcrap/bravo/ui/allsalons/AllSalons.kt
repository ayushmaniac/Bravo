package com.realcrap.bravo.ui.allsalons

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.allsalons.salon.SalonAdapter
import com.realcrap.bravo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_all_salons.*
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class AllSalons : BaseActivity<AllSalonsViewModel>() {


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var salonAdapter : SalonAdapter

    var cal = Calendar.getInstance()
    val year = cal.get(Calendar.YEAR)
    val month = cal.get(Calendar.MONTH)
    val day = cal.get(Calendar.DAY_OF_MONTH)

    override fun provideLayoutId(): Int = R.layout.activity_all_salons

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        allSalonsRecycler.apply {
            layoutManager = linearLayoutManager
            adapter = salonAdapter
        }
        changeDate.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, yearO, monthOfYear, dayOfMonth ->
               updateDate(yearO, monthOfYear, dayOfMonth)
            }, year, month, day)
            dpd.show()

        }

        //just for branch check


    }

    private fun updateDate(yearO: Int, monthOfYear: Int, dayOfMonth: Int) {
        val calendar : Calendar = Calendar.getInstance()
        calendar.timeInMillis = 0
        calendar.set(yearO, monthOfYear, dayOfMonth)
        val chooseDate : Date = calendar.time
        val newDate = DateFormat.getDateInstance(DateFormat.FULL).format(chooseDate)
        viewModel.getTodaysDate.postValue(newDate)

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.dataAllSalons.observe(this, Observer {
            it.data?.run { salonAdapter.appendData(this) }
        })

       viewModel.getTodaysDate.observe(this, Observer {

           changeDate.text = it
       })

        viewModel.allProgress.observe(this, Observer {

            if(it == true){
                wait.visibility = View.VISIBLE
                wait.playAnimation()
                waitText.visibility = View.VISIBLE

            }
            else {

                wait.visibility = View.GONE
                wait.cancelAnimation()
                waitText.visibility = View.GONE

            }
        })
    }


   fun newMethod{
       
   }
}
