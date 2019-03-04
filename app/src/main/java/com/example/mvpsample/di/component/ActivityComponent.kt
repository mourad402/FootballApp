package com.example.mvpsample.di.component

import com.example.mvpsample.ui.main.MainActivity
import com.example.mvpsample.di.module.ActivityModule
import dagger.Component

// provides Activity and mainPresenter from ActivityModule
// and inject them to MainActivity

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}