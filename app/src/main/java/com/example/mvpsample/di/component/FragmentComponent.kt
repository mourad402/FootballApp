package com.example.mvpsample.di.component

import com.example.mvpsample.di.module.FragmentModule
import com.example.mvpsample.ui.listejoueurs.ListJoueursFragment
import com.example.mvpsample.ui.listequipes.ListEquipesFragment
import dagger.Component


// provides ListEquipes Presenter and apiService from FragmentModule
// and injects them in ListEquipesFragment fragments

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(listEquipesFragment: ListEquipesFragment)

    fun inject(listJoueursFragment: ListJoueursFragment)

}