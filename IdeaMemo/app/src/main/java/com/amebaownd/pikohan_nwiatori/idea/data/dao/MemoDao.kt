package com.amebaownd.pikohan_nwiatori.idea.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo

@Dao
interface MemoDao{

    @Query("SELECT * FROM memo_table")
    fun getAll():List<Memo>

    @Query("SELECT * FROM memo_table WHERE isActive=(:isActive)")
    fun getByIsActive(isActive: Boolean):List<Memo>

    @Query("SELECT * FROM memo_table WHERE id=(:id)")
    fun getById(id:String):Memo

    @Query("DELETE FROM memo_table WHERE id =(:id)")
    fun deleteById(id:String)

    @Insert
    fun insert(memo:Memo)

    @Query("UPDATE memo_table SET isActive=(:isActive) WHERE id = (:id)")
    fun updateActive(id:String,isActive:Boolean)

    @Query("UPDATE memo_table SET title=(:title),memo=(:memo) WHERE id=(:id)")
    fun update(id:String,title:String,memo:String)
}