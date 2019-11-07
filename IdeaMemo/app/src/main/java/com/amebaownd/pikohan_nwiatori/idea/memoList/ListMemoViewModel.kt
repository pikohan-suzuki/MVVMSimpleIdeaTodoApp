package com.amebaownd.pikohan_nwiatori.idea.memoList

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo
import com.amebaownd.pikohan_nwiatori.idea.data.repository.IdeaMemoRepository
import com.amebaownd.pikohan_nwiatori.idea.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListMemoViewModel (private val repository: IdeaMemoRepository):ViewModel(){

    private var _memos = MutableLiveData<List<Memo>>()
    val memos :LiveData<List<Memo>> = _memos

    private var _openAddEvent = MutableLiveData< Event<Boolean>>(Event(false))
    val openAddEvent:LiveData<Event<Boolean>> = _openAddEvent

    private var _openDetailEvent = MutableLiveData<Event<String>>(Event(""))
    val openDetailEvent :LiveData<Event<String>> = _openDetailEvent

    private var _title = MutableLiveData<String>("")
    val title :LiveData<String> = _title

    private val _noMemosVisibility = MutableLiveData<Int>(View.GONE)
    val noMemosVisibility :LiveData<Int> =_noMemosVisibility

    fun setAllMemos(){
        _title.value = "All Memos"
        viewModelScope.launch(Dispatchers.IO){
            val result =repository.getAll()
            _memos.postValue(result)
            _noMemosVisibility.postValue(
                if(result.isEmpty())
                    View.VISIBLE
                else
                    View.GONE
            )
        }
    }

    fun setByIsActive(isActive:Boolean){
        _title.value = if(isActive) "Active Memos" else "Closed Memos"
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getByIsActive(isActive)
            _memos.postValue(result)
            _noMemosVisibility.postValue(
                if(result.isEmpty())
                    View.VISIBLE
                else
                    View.GONE
            )
        }
    }

    fun onFabClicked(){
        _openAddEvent.value = Event(true)
    }

    fun onItemClicked(id:String){
        _openDetailEvent.value = Event(id)
    }

}