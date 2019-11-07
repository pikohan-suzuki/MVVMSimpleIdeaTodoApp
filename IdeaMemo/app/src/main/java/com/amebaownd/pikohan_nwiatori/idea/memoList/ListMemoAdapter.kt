package com.amebaownd.pikohan_nwiatori.idea.memoList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo
import com.amebaownd.pikohan_nwiatori.idea.databinding.ItemListMemoBinding

class ListMemoAdapter(private val viewModel: ListMemoViewModel,private val context: Context?) :
    ListAdapter<Memo, ListMemoAdapter.ViewHolder>(UserListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel,item)
    }

    class ViewHolder(private val binding: ItemListMemoBinding,private val context:Context?) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel:ListMemoViewModel,item:Memo){
            binding.viewModel = viewModel
            binding.memo = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent:ViewGroup,context:Context?):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListMemoBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding,context)
            }
        }
    }
}

class UserListDiffCallback : DiffUtil.ItemCallback<Memo>() {
    override fun areItemsTheSame(
        oldItem: Memo,
        newItem: Memo
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Memo,
        newItem: Memo
    ): Boolean {
        return oldItem.equals(newItem)
    }
}