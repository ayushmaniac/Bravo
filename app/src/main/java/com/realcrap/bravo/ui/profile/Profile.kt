package com.realcrap.bravo.ui.profile


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.editprofile.EditProfile
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.ui.login.Login
import kotlinx.android.synthetic.main.fragment_userprofile.*

/**
 * A simple [Fragment] subclass.
 */
class Profile : BaseFragment<ProfileViewModel>() {

    lateinit var firebaseAuth : FirebaseAuth

    companion object {

        const val TAG = "Profile"

        fun newInstance() : Profile{
            val args = Bundle()
            val fragment = Profile()
            fragment.arguments = args
            return fragment
        }

    }

    override fun provideLayoutId(): Int = R.layout.fragment_userprofile

    override fun injectDependencies(fragmentComponent: FragmentComponent)  =
            fragmentComponent.inject(this)

    override fun setupView(view: View) {

        logoutButton.setOnClickListener{

            viewModel.logOut()
        }

        firebaseAuth = FirebaseAuth.getInstance()
        val currentUser = firebaseAuth.currentUser

        editProfileButton.setOnClickListener {
            startActivity(Intent(context, EditProfile::class.java))
        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.logoutData.observe(this, Observer {

            if(it == true){

                val intent  = Intent(context, Login::class.java)
                intent.apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK

                }
                startActivity(intent)

            }
            else {


            }
        })

        viewModel.userNameData.observe(this, Observer {

            usersName.text = it

        })
    }


}
