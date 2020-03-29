package com.realcrap.bravo.di.component

import com.realcrap.bravo.di.module.ViewHolderModule
import com.realcrap.bravo.di.scope.ViewHolderScope
import com.realcrap.bravo.ui.allsalons.salon.SalonViewHolder
import com.realcrap.bravo.ui.home.homeoffers.HomeOfferItemViewHolder
import com.realcrap.bravo.ui.home.homesalons.HomeSalonsItemViewHolder
import dagger.Component

@ViewHolderScope
@Component (dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class])
interface ViewHolderComponent {

     fun inject(offersHomeViewHolder : HomeOfferItemViewHolder)
     fun inject(offersHomeSalonViewHolder : HomeSalonsItemViewHolder)
     fun inject(salonViewHolder : SalonViewHolder)





}