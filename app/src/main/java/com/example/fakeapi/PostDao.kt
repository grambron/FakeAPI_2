package com.example.fakeapi

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fakeapi.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post ORDER BY id DESC")
    fun findAll(): LiveData<List<Post>>

    @Insert
    suspend fun insertAll(post: List<Post>)

    @Insert
    suspend fun insert(post: Post)

    @Query("DELETE FROM Post WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM Post")
    suspend fun deleteAll()
}