package com.amebaownd.pikohan_nwiatori.idea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amebaownd.pikohan_nwiatori.idea.addEditMemo.AddEditMemoViewModel
import com.amebaownd.pikohan_nwiatori.idea.data.repository.IdeaMemoRepository
import com.amebaownd.pikohan_nwiatori.idea.detailMemo.DetailMemoViewModel
import com.amebaownd.pikohan_nwiatori.idea.memoList.ListMemoViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val ideaMemoRepository: IdeaMemoRepository
): ViewModelProvider.NewInstanceFactory(){

    override fun <T: ViewModel> create(modelClass:Class<T>): T {
        val t = with(modelClass) {
            when {
                isAssignableFrom(ListMemoViewModel::class.java) ->
                    ListMemoViewModel(ideaMemoRepository)
                isAssignableFrom(AddEditMemoViewModel::class.java) ->
                    AddEditMemoViewModel(ideaMemoRepository)
                isAssignableFrom(DetailMemoViewModel::class.java) ->
                    DetailMemoViewModel(ideaMemoRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModelClass $modelClass")
            }
        } as T
        return t
    }
}