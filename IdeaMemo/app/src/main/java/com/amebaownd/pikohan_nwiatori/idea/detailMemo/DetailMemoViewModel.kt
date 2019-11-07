package com.amebaownd.pikohan_nwiatori.idea.detailMemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_nwiatori.idea.data.repository.IdeaMemoRepository
import com.amebaownd.pikohan_nwiatori.idea.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMemoViewModel (private val repository: IdeaMemoRepository):ViewModel(){

    var title = MutableLiveData<String>("")
    var memo = MutableLiveData<String>("")
    var isChecked = MutableLiveData<Boolean>(false)

    private var _openEditEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val openEditEvent : LiveData<Event<Boolean>> = _openEditEvent

    fun start(id:String){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.getById(id)
            title.postValue(result.title)
            memo.postValue(result.memo)
            isChecked.postValue(!result.isActive)
        }
    }

    fun updateIsActive(id:String,isActive:Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateIsActive(id, isActive)
        }
    }

    fun onFabClicked(){
        _openEditEvent.value = Event(true)
    }

    fun deleteMemo(id:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteMemo(id)
        }
    }
}