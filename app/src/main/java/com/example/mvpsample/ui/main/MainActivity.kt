package com.example.mvpsample.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvpsample.R
import com.example.mvpsample.di.component.DaggerActivityComponent
import com.example.mvpsample.di.module.ActivityModule
import com.example.mvpsample.ui.listequipes.ListEquipesFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View { //extends appCompat and MainContact.View

    // implements View and instantiate Presenter
    // inject the presenter
    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()

        presenter.attach(this)
    }


    override fun showListEquipesFragment() {

        Log.d("mvpmvp", "showListEquipesFragment")
        supportFragmentManager.beginTransaction()
            .disallowAddToBackStack()
            .setCustomAnimations(AnimType.SLIDE.getAnimPair().first, AnimType.SLIDE.getAnimPair().second)
            .add(R.id.frame, ListEquipesFragment().newInstance())
            .commit()


    }

//    override fun showListJoueursFragment() {
//
//        Log.d("mvpmvp", "showListJoueursFragment mvpmvp")
//    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    enum class AnimType {
        SLIDE,
        FADE;

        fun getAnimPair(): Pair<Int, Int> {
            return when(this) {
                SLIDE -> Pair(R.anim.slide_left, R.anim.slide_right)
                FADE -> Pair(R.anim.fade_in, R.anim.fade_out)
            }
        }
    }

}
