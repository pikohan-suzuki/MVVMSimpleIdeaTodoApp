package com.amebaownd.pikohan_nwiatori.idea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amebaownd.pikohan_nwiatori.idea.data.dao.MemoDao
import com.amebaownd.pikohan_nwiatori.idea.data.model.Memo

@Database(entities = [Memo::class],version = 1)

abstract class AppDatabase :RoomDatabase(){
    abstract fun memoDao():MemoDao

    companion object{
        @Volatile
        private var INSTANCE:AppDatabase?=null

        fun getDatabase(
            context: Context
        ):AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null)
                return tempInstance
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
//                    .addMigrations(MIGRATE1_2)
//                    .addCallback()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
