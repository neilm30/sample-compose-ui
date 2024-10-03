package com.githubrepo.presentation.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubrepo.intent.DataIntent
import com.githubrepo.state.DataState
import com.githubrepo.ui.BaseFragment
import com.githubrepo.ui.BaseFragmentUI
import com.iiab.mobilebanking.R
import com.iiab.mobilebanking.BR

import com.iiab.mobilebanking.databinding.FragmentRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TrendingRepositoryFragment: Fragment((R.layout.fragment_recyclerview)) {
   /* override val viewModel: TrendingRepositoryViewModel
        get() = viewModel*/
   /* override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutRes: Int
        get() = R.layout.fragment_recyclerview
    private val viewModel by viewModels<TrendingRepositoryViewModel>()*/

  /*  override var useSharedViewModel = true

    override fun getViewModelClass() = TrendingRepositoryViewModel::class.java

    override fun getViewBinding() = FragmentRecyclerviewBinding.inflate(layoutInflater)
*/
 /* override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
      var dataBinding =
          DataBindingUtil.inflate(inflater, R.layout.fragment_recyclerview, container, false)
      dataBinding.lifecycleOwner = viewLifecycleOwner
      return dataBinding.root
  }
*/
    private val viewModel by viewModels<TrendingRepositoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        lifecycleScope.launch{
            viewModel.userIntent.send(DataIntent.FetchReoistory)
        }
    }
   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
        lifecycleScope.launch{
            viewModel.userIntent.send(DataIntent.FetchReoistory)
        }
    }*/

    private fun observeLiveData(){
       /* lifecycleScope.launch {
             viewModel.dataState.collect{
                 when(it){
                     is DataState.Loading -> {
                         Log.d("Worked", "Idle")
                     }

                     is DataState.Success -> {
                        *//* val recyclerViewNews = getViewBinding().cardsList
                         recyclerViewNews.layoutManager = LinearLayoutManager(context)
                         recyclerViewNews.setHasFixedSize(true)
                         viewModel.items.addAll(it.data)*//*
                        // viewModel.items.addAll(listOf("hello world"))
                     }
                     is DataState.Error -> {

                     }
                     is DataState.Inactive -> {

                     }
                 }
             }
        }*/
    }
}