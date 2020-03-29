package com.realcrap.bravo.ui.editprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.uploadpic.UploadPicture
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.edit_profile.*

class EditProfile : BaseActivity<EditProfileViewModel>() {


    override fun provideLayoutId(): Int = R.layout.edit_profile

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)


    override fun setupView(savedInstanceState: Bundle?) {

        goBackNow.setOnClickListener {
            finish()
        }
        editProfileImage.setOnClickListener{
            startActivity(Intent(this, UploadPicture::class.java))
        }

        editName.addTextChangedListener(object : TextWatcher{


            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onNameChanged(s.toString())
            }


        })

        editEmail.addTextChangedListener(object : TextWatcher{


            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onEmailChanged(s.toString())


            }


        })

        editMobile.addTextChangedListener(object : TextWatcher{


            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onMobileChanged(s.toString())


            }


        })

        saveEditButton.setOnClickListener {

            viewModel.updateProfileDetails()
        }
    }



    override fun setupObservers() {
        super.setupObservers()

        viewModel.dataUser.observe(this, Observer {
            saveEditButton.visibility = View.VISIBLE
            editName.setText(it.data!!.name)
            Glide.with(applicationContext)
                    .load(it.data!!.profilepic)
                    .into(editProfileImage)

            editEmail.setText(it.data!!.email)

            if(it.data.mobile.equals("null")){
                editMobile.setHint("Phone number not registered")
            }
            else {
                editMobile.setText(it.data!!.mobile)
            }
        })

        viewModel.dataProgress.observe(this, Observer {
            editProfProgressBar.visibility = if (it) View.VISIBLE else View.GONE

        })

        viewModel.updateStatus.observe(this, Observer {

            Toaster.show(this, it.toString())
        })

    }

}
