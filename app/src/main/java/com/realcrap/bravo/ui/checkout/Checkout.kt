package com.realcrap.bravo.ui.checkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.checkout.items.ItemsAdapter
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class Checkout : BaseActivity<CheckOutViewModel>(){


    override fun provideLayoutId(): Int = R.layout.activity_checkout

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var itemsServiceAdapter : ItemsAdapter

    override fun setupView(savedInstanceState: Bundle?) {

        serviceItemsRecycler.apply {

            layoutManager = linearLayoutManager
            adapter = itemsServiceAdapter

        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.itemsData.observe(this, Observer {
            itemsServiceAdapter.appendData(it)
        })
    }

}
