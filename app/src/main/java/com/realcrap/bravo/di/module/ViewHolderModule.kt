package com.realcrap.bravo.di.module

import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.di.scope.ViewHolderScope
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>){

    @Provides
    @ViewHolderScope
    fun provideLifeCycleRegistry() : LifecycleRegistry = LifecycleRegistry(viewHolder)




}