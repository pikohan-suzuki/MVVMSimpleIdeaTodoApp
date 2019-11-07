package com.amebaownd.pikohan_nwiatori.idea.data.repository

import com.amebaownd.pikohan_nwiatori.idea.data.dao.MemoDao
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo

class IdeaMemoRepository (private val memoDao:MemoDao){

    fun getAll() =
        memoDao.getAll()

    fun getByIsActive(isActive:Boolean)=
        memoDao.getByIsActive(isActive)

    fun getById(id:String)=
        memoDao.getById(id)

    fun insert(memo: Memo){
        memoDao.insert(memo)
    }

    fun updateIsActive(id:String,isActive:Boolean){
        memoDao.updateActive(id,isActive)
    }

    fun deleteMemo(id:String){
        memoDao.deleteById(id)
    }

    fun update(id:String,title:String,memo:String){
        memoDao.update(id,title,memo)
    }
}