package com.githubrepo.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.githubrepo.listeners.BackButtonHandlerListener
import com.githubrepo.listeners.BackPressListener
import kotlin.reflect.KClass

/****
 *
 * Author: Meera Manesh
 * Company: Farabi Technologies
 * Created on: 4/21/21
 * Modified on: 4/21/21
 *****/
abstract class BaseActivity<VM : ViewModel, D : ViewDataBinding>(clazz: KClass<VM>) :
    AppCompatActivity(),
    BackButtonHandlerListener {

    lateinit var dataBinding: D

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDataBinding()
    }

    private fun initializeDataBinding() {
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }



    /**
     * Add the back navigation listener here.
     * Call this method from onAttach of your fragment
     * @param listener - back navigation listener
     */
    override fun addBackpressListener(listener: BackPressListener) {
    }

    /**
     * remove the back navigation listener here.
     * Call this method from onDetach of your fragment
     * @param listener - back navigation listener
     */
    override fun removeBackpressListener(listener: BackPressListener) {

    }



    override fun onDestroy() {
        super.onDestroy()
    }

}