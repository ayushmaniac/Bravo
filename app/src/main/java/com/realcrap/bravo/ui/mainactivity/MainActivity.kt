package com.realcrap.bravo.ui.mainactivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.realcrap.bravo.R
import com.realcrap.bravo.ui.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>() {


    override fun provideLayoutId(): Int  = R.layout.activity_main


}