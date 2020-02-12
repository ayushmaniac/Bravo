package com.realcrap.bravo.di.component

import com.realcrap.bravo.di.module.FragmentModule
import com.realcrap.bravo.di.scope.FragmentScope
import com.realcrap.bravo.ui.home.Home
import com.realcrap.bravo.ui.offers.Offers
import com.realcrap.bravo.ui.profile.Profile
import dagger.Component


@FragmentScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: Home)
    fun inject(fragment: Offers)
    fun inject(fragment: Profile)



}