package com.amebaownd.pikohan_nwiatori.idea.addEditMemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_nwiatori.idea.R
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo
import com.amebaownd.pikohan_nwiatori.idea.data.repository.IdeaMemoRepository
import com.amebaownd.pikohan_nwiatori.idea.util.Event
import com.amebaownd.pikohan_nwiatori.idea.util.MyContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditMemoViewModel (private val repository: IdeaMemoRepository):ViewModel(){

    private var id :String =""

    var title = MutableLiveData<String>("")
    var memo = MutableLiveData<String>()

    private var _isAbleToClickFab = MutableLiveData<Boolean>(false)
    val isAbleToClickFab :LiveData<Boolean> = _isAbleToClickFab

    private var _saveEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val saveEvent :LiveData<Event<Boolean>> =_saveEvent

    private var _fabColor = MutableLiveData<Int>(MyContext.getColor(R.color.colorAccent))
    val fabColor :LiveData<Int> = _fabColor

    fun start(id:String){
        this.id=id
        viewModelScope.launch(Dispatchers.IO){
            val result =repository.getById(id)
            title.postValue(result.title)
            result.memo.let{
                memo.postValue(it)
            }
            isAbleToClickFab()
        }
    }

    fun onFabClicked(){
        _saveEvent.value = Event(true)
        if(id=="") {
            viewModelScope.launch(Dispatchers.IO) {
                repository.insert(Memo(title = title.value!!, memo = memo.value ?:"", isActive = true))
            }
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                repository.update(id=this@AddEditMemoViewModel.id,title=title.value!!,memo = memo.value ?:"")
            }
        }
    }

     fun isAbleToFabClick(){
        _isAbleToClickFab.value=(!title.value.isNullOrBlank() && !title.value.isNullOrEmpty())
         if(_isAbleToClickFab.value!!)
             _fabColor.value = (MyContext.getColor(R.color.colorAccent))
         else
             _fabColor.value=(MyContext.getColor(R.color.colorAccentLight))
    }

    private fun isAbleToClickFab(){
        _isAbleToClickFab.postValue(!title.value.isNullOrBlank() && !title.value.isNullOrEmpty())
        if(_isAbleToClickFab.value!!)
            _fabColor.postValue(MyContext.getColor(R.color.colorAccent))
        else
            _fabColor.postValue(MyContext.getColor(R.color.colorAccentLight))
    }



}