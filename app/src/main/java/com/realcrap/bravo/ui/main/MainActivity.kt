package com.realcrap.bravo.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.home.Home
import com.realcrap.bravo.ui.offers.Offers
import com.realcrap.bravo.ui.profile.Profile
import com.realcrap.bravo.ui.referandearn.Refer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {


    companion object {

        const val TAG = "MainActivity"

    }

    private var activeFragment : Fragment? = null


    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        bottomNavigation.run {

            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        viewModel.onHomeSelected()
                        true
                    }
                    R.id.offer -> {
                        viewModel.onOfferSelected()
                        true
                    }
                    R.id.refer -> {
                        viewModel.onReferSelected()
                        true
                    }


                    R.id.profile -> {
                        viewModel.onProfileSelected()
                        true
                    }
                    else -> false


                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.homeNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showHome() }
        })

        viewModel.profileNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showProfile() }
        })

        viewModel.offerNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showOffers() }
        })

        viewModel.referNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showRefer() }
        })

    }

    private fun showRefer() {
        if (activeFragment is Refer) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Refer.TAG) as Refer?

        if (fragment == null) {
            fragment = Refer.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, Refer.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment    }


    private fun showHome() {
        if (activeFragment is Home) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Home.TAG) as Home?

        if (fragment == null) {
            fragment = Home.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, Home.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }


    private fun showProfile() {
        if (activeFragment is Profile) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Profile.TAG) as Profile?

        if (fragment == null) {
            fragment = Profile.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, Profile.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }


    private fun showOffers() {
        if (activeFragment is Offers ) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(Offers.TAG) as Offers?

        if (fragment == null) {
            fragment = Offers.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, Offers.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }




}
