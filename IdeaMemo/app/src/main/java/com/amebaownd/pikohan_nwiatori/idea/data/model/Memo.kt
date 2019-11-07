package com.amebaownd.pikohan_nwiatori.idea.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey
    val id :String = UUID.randomUUID().toString(),
    val title:String = UUID.randomUUID().toString(),
    val memo:String = UUID.randomUUID().toString(),
    val isActive:Boolean = true,
    val timeStump:Long = Date().time
)