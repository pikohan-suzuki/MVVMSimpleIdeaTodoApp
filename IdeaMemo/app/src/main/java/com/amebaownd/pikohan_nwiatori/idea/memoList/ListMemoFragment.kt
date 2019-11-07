package com.amebaownd.pikohan_nwiatori.idea.memoList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.amebaownd.pikohan_nwiatori.idea.R
import com.amebaownd.pikohan_nwiatori.idea.databinding.FragmentListMemoBinding
import com.amebaownd.pikohan_nwiatori.idea.util.EventObserver
import com.amebaownd.pikohan_nwiatori.idea.util.getViewModelFactory
import kotlinx.android.synthetic.main.fragment_list_memo.*

class ListMemoFragment : Fragment() {
    private val viewModel: ListMemoViewModel by viewModels { getViewModelFactory() }

    private lateinit var itemListMemoBinding: FragmentListMemoBinding
    private lateinit var adapter: ListMemoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        itemListMemoBinding = FragmentListMemoBinding.inflate(inflater, container, false).apply {
            viewModel = this@ListMemoFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        setHasOptionsMenu(true)
        return itemListMemoBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_memo_menu, menu)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setupRecyclerView()
        viewModel.setAllMemos()
        onFabClicked()
        onItemClicked()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.listMemo_menu_all -> {
                viewModel.setAllMemos()
                return true
            }
            R.id.listMemo_menu_active -> {
                viewModel.setByIsActive(true)
                return true
            }
            R.id.listMemo_menu_closed -> {
                viewModel.setByIsActive(false)
                return true
            }
        }
        return false
    }

    private fun onFabClicked() {
        viewModel.openAddEvent.observe(this, EventObserver {
            if (it)
                navigateToAddEditMemoFragment()
        })
    }

    private fun onItemClicked() {
        viewModel.openDetailEvent.observe(this, EventObserver {
            if (it != "") {
                navigateToDetailMemoFragment(it)
            }
        })
    }

    private fun setupRecyclerView() {
        val viewModel = itemListMemoBinding.viewModel
        viewModel?.let {
            adapter = ListMemoAdapter(it, context)
            itemListMemoBinding.listMemoRecyclerView.adapter=adapter
            itemListMemoBinding.listMemoRecyclerView.layoutManager=
                GridLayoutManager(
                    this.context,
                    2
                )
        }
    }

    private fun navigateToAddEditMemoFragment() {
        val action = ListMemoFragmentDirections
            .actionListMemoFragmentToAddEditMemoFragment(null,"Add Memo")
        findNavController().navigate(action)
    }

    private fun navigateToDetailMemoFragment(id: String) {
        val action = ListMemoFragmentDirections
            .actionListMemoFragmentToDetailMemoFragment(id,"Detail Memo")
        findNavController().navigate(action)
    }
}