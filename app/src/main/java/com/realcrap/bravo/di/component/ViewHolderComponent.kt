package com.realcrap.bravo.di.component

import com.realcrap.bravo.di.module.ViewHolderModule
import com.realcrap.bravo.di.scope.ViewHolderScope
import com.realcrap.bravo.ui.allsalons.salon.SalonViewHolder
import com.realcrap.bravo.ui.buisnesspage.services.ServicesViewHolder
import com.realcrap.bravo.ui.checkout.items.ItemsViewHolder
import com.realcrap.bravo.ui.home.homeoffers.HomeOfferItemViewHolder
import com.realcrap.bravo.ui.home.homesalons.HomeSalonsItemViewHolder
import com.realcrap.bravo.ui.offers.offersutil.OfferViewHolder
import dagger.Component

@ViewHolderScope
@Component (dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class])
interface ViewHolderComponent {

     fun inject(offersViewHolder : OfferViewHolder)
     fun inject(offersHomeViewHolder : HomeOfferItemViewHolder)
     fun inject(offersHomeSalonViewHolder : HomeSalonsItemViewHolder)
     fun inject(servicesViewHolder : ServicesViewHolder)
     fun inject(itemViewHolder : ItemsViewHolder)
     fun inject(salonViewHolder : SalonViewHolder)





}