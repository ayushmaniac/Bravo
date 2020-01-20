package com.realcrap.bravo.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.di.application.BravoApplication
import com.realcrap.bravo.di.component.DaggerViewHolderComponent
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.di.module.ViewHolderModule
import javax.inject.Inject

abstract class BaseItemViewHolder<T: Any, VM: BaseItemViewModel<T>>(
        @LayoutRes layoutId : Int, parent: ViewGroup
        ) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)), LifecycleOwner{

    init {
        onCreate()
    }

    override fun getLifecycle(): Lifecycle = lifeCycleRegistry

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifeCycleRegistry: LifecycleRegistry


   open fun bind(data: T){
        viewModel.updateData(data)
    }

    fun onCreate() {
       injectDependencies(buildViewHolderComponent())
       lifeCycleRegistry.markState(Lifecycle.State.INITIALIZED)
       lifeCycleRegistry.markState(Lifecycle.State.CREATED)


       setupObserver()
       setupView(itemView)

   }

     fun onStart() {
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.RESUMED)

    }

    fun onStop() {
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)

    }

    fun onDestroy() {
        lifeCycleRegistry.markState(Lifecycle.State.DESTROYED)


    }


    protected open fun setupObserver() {

    }

    private fun buildViewHolderComponent() =
            DaggerViewHolderComponent
                    .builder()
                    .applicationComponent((itemView.context.applicationContext as BravoApplication).applicationComponent)
                    .viewHolderModule(ViewHolderModule(this))
                    .build()


    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)

    protected abstract fun setupView(view : View)
}

