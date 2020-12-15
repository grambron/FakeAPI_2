package com.example.fakeapi

import android.app.Application

class MyApp: Application() {
    var synced = false
    lateinit var repository: FakeApiInterface
    lateinit var postViewModel: PostViewModel

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = FakeApiInterface.create()
        postViewModel = PostViewModel(this)
    }

    companion object {
        lateinit var instance: MyApp
            private set
    }
}