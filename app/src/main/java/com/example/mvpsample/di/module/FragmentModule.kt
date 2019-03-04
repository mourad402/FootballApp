package com.example.mvpsample.di.module

import com.example.mvpsample.api.ApiServiceInterface
import com.example.mvpsample.ui.listejoueurs.ListJoueursContract
import com.example.mvpsample.ui.listejoueurs.ListJoueursPresenter
import com.example.mvpsample.ui.listequipes.ListEquipesContract
import com.example.mvpsample.ui.listequipes.ListEquipesPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideListEquipesPresenter(): ListEquipesContract.Presenter {
        return ListEquipesPresenter()
    }

    @Provides
    fun provideListJoueursPresenter(): ListJoueursContract.Presenter {
        return ListJoueursPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}