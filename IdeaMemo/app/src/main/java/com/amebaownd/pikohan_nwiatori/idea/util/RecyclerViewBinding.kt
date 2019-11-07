package com.amebaownd.pikohan_nwiatori.idea.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo
import com.amebaownd.pikohan_nwiatori.idea.memoList.ListMemoAdapter

@BindingAdapter("app:memos")
fun setTalkItems(listView: RecyclerView, items:List<Memo>?){
    if(items!=null)
        (listView.adapter as ListMemoAdapter).submitList(items)
}