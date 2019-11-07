package com.amebaownd.pikohan_nwiatori.idea.addEditMemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.idea.databinding.FragmentAddeditMemoBinding
import com.amebaownd.pikohan_nwiatori.idea.util.EventObserver
import com.amebaownd.pikohan_nwiatori.idea.util.getViewModelFactory

class AddEditMemoFragment:Fragment() {

    private val viewModel :AddEditMemoViewModel by viewModels { getViewModelFactory() }
    private val args :AddEditMemoFragmentArgs by navArgs()

    private lateinit var fragmentAddEditMemoBinding: FragmentAddeditMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddEditMemoBinding = FragmentAddeditMemoBinding.inflate(inflater,container,false).apply {
            lifecycleOwner=viewLifecycleOwner
            viewModel = this@AddEditMemoFragment.viewModel
        }
        return fragmentAddEditMemoBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(args.id!=null){
            viewModel.start(args.id!!)
        }
        onFabClicked()
        onTitleChanged()
    }

    private fun onFabClicked(){
        viewModel.saveEvent.observe(this,EventObserver{
            if(it && args.id!=null)
                navigateToDetailMemoFragment(args.id!!)
            else if(it)
                navigateToListMemoFragment()
        })
    }

    private fun onTitleChanged(){
        viewModel.title.observe(this, Observer {
            viewModel.isAbleToFabClick()
        })
    }

    private fun navigateToListMemoFragment(){
        val action = AddEditMemoFragmentDirections
            .actionAddEditMemoFragmentToListMemoFragment()
        findNavController().navigate(action)
    }

    private fun navigateToDetailMemoFragment(id:String){
        val action = AddEditMemoFragmentDirections
            .actionAddEditMemoFragmentToDetailMemoFragment(id,viewModel.title.value!!)
        findNavController().navigate(action)
    }
}