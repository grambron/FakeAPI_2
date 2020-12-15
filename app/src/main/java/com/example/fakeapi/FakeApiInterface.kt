package com.example.fakeapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

interface FakeApiInterface {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @POST("posts")
    suspend fun addPost(@Body post: Post)

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int)

    companion object Factory {
        fun create(): FakeApiInterface {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(FakeApiInterface::class.java)
        }
    }
}