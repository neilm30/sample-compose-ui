package com.githubrepo.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass
import dagger.hilt.android.AndroidEntryPoint

abstract class BaseFragment<VM:ViewModel, DB : ViewDataBinding>(clazz: KClass<VM>) : Fragment(){


    //protected abstract val viewModel: VM
    abstract val bindingVariable: Int
    protected lateinit var dataBinding: DB


    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()
    }

    /**
     * Returns binding object to remove usage of Kotlin Synthetic extension
     *
     */
    protected fun getViewBinding(): DB {
        return dataBinding
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}