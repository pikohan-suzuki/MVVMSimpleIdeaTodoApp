package com.amebaownd.pikohan_nwiatori.idea.detailMemo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.idea.R
import com.amebaownd.pikohan_nwiatori.idea.databinding.FragmentDetailMemoBinding
import com.amebaownd.pikohan_nwiatori.idea.util.EventObserver
import com.amebaownd.pikohan_nwiatori.idea.util.getViewModelFactory

class DetailMemoFragment: Fragment(){

    private val viewModel:DetailMemoViewModel by viewModels { getViewModelFactory() }
    private val args:DetailMemoFragmentArgs by navArgs()

    private lateinit var fragmentDetailBinding: FragmentDetailMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentDetailBinding = FragmentDetailMemoBinding.inflate(inflater,container,false).apply {
            viewModel= this@DetailMemoFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setHasOptionsMenu(true)
        return fragmentDetailBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_memo_menu, menu)
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.id
        viewModel.start(id)
        onCheckChanged()
        openEdit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.detailMemo_menu_delete->{
                showConfirmDialog()
                return true
            }
        }
        return false
    }

    private fun showConfirmDialog(){
        val alertDialog = AlertDialog.Builder(this.context)
            .setTitle("Caution!")
            .setMessage("Are you sure you want to delete?")
            .setPositiveButton("OK", object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    deleteMemo()
                }
            })
            .setNegativeButton("Cancel", null)
        alertDialog.show()
    }

    private fun deleteMemo(){
        viewModel.deleteMemo(args.id)
        navigateToListMemoFragment()
    }


    private fun onCheckChanged(){
        viewModel.isChecked.observe(this, Observer {
                viewModel.updateIsActive(args.id,!it)
        })
    }

    private fun openEdit(){
        viewModel.openEditEvent.observe(this,EventObserver{
            if(it)
                navigationToAddEditFragment()
        })
    }

    private fun navigationToAddEditFragment(){
        val action = DetailMemoFragmentDirections
            .actionDetailMemoFragmentToAddEditMemoFragment(args.id,"Add Memo")
        findNavController().navigate(action)
    }

    private fun navigateToListMemoFragment(){
        findNavController().popBackStack()
    }
}