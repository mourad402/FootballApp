package com.example.mvpsample.di.module

import android.app.Activity
import com.example.mvpsample.ui.main.MainContract
import com.example.mvpsample.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

// injects activity and provides it when needed. Also provides presenter

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}