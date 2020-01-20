package com.realcrap.bravo.di.component

import com.realcrap.bravo.di.module.ViewHolderModule
import com.realcrap.bravo.di.scope.ViewHolderScope
import dagger.Component

@ViewHolderScope
@Component (dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class])
interface ViewHolderComponent {


}