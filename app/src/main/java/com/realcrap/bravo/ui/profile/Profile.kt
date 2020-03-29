package com.realcrap.bravo.ui.profile


import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.transition.Transition
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition
import com.bumptech.glide.request.transition.TransitionFactory
import com.google.firebase.auth.FirebaseAuth
import com.realcrap.bravo.BuildConfig
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.contactus.ContactUs
import com.realcrap.bravo.ui.editprofile.EditProfile
import com.realcrap.bravo.ui.login.Login
import com.realcrap.bravo.ui.mybooking.MyBookings
import com.realcrap.bravo.ui.notification.Notifications
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

        proMyBooking.setOnClickListener {

            startActivity(Intent(context, MyBookings::class.java))

        }

        profNotifications.setOnClickListener {

            startActivity(Intent(context, Notifications::class.java))

        }


        profContactus.setOnClickListener {

            startActivity(Intent(context, ContactUs::class.java))

        }

        profShare.setOnClickListener {

            //            try {
//                val shareIntent = Intent(Intent.ACTION_SEND)
//                shareIntent.type = "text/plain"
//                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "")
//                var shareMessage = "\nLet me recommend you this application\n\n"
//                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n"
//                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//                startActivity(Intent.createChooser(shareIntent, "choose one"))
//            } catch (e: Exception) {
//            }
//        }

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


        viewModel.dataUser.observe(this, Observer {

            usersName.text = it.data!!.name
            Glide.with(context!!)
                    .load(it.data.profilepic)
                    .into(userProfilePic)


        })
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getUserDetails()
    }


    inner class DrawableAlwaysCrossFadeFactory : TransitionFactory<Drawable> {
        private val resourceTransition: DrawableCrossFadeTransition = DrawableCrossFadeTransition(300, true) //customize to your own needs or apply a builder pattern
        override fun build(dataSource: DataSource?, isFirstResource: Boolean): com.bumptech.glide.request.transition.Transition<Drawable> {
            return resourceTransition
        }
    }

}


