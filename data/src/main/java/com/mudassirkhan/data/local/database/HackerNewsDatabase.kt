package com.mudassirkhan.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mudassirkhan.data.local.dao.NewsItemDao
import com.mudassirkhan.data.local.model.NewsLocalModel

@Database(entities = [NewsLocalModel::class],version = 1,exportSchema = false)
abstract class HackerNewsDatabase :RoomDatabase(){

    abstract fun hackerNewsDao(): NewsItemDao

    companion object{
        fun newInstance(context: Context): HackerNewsDatabase {
            return Room.databaseBuilder(context,
                HackerNewsDatabase::class.java,
                "hacker_news.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}