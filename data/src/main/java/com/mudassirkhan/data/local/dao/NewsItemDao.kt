package com.mudassirkhan.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mudassirkhan.data.local.model.NewsLocalModel
import io.reactivex.Single

@Dao
interface NewsItemDao {
    @Query("SELECT * FROM `newsItem`")
    fun getAllTopNewsList(): Single<List<NewsLocalModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg newsList:NewsLocalModel)
}