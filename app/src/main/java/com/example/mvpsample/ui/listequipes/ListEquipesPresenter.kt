package com.example.mvpsample.ui.listequipes

import android.app.Application
import android.util.Log
import com.example.mvpsample.R
import com.example.mvpsample.api.ApiServiceInterface
import com.example.mvpsample.models.Championnat
import com.example.mvpsample.models.Joueur
import com.example.mvpsample.models.Joueurs
import com.example.mvpsample.ui.main.MainActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/*

What is Presenter?
In MVP, Presenter retrieves the data from the Model, applies the UI logic and manages the state of the View,
decides what to display and reacts to user input notifications from the View.
 */

class ListEquipesPresenter: ListEquipesContract.Presenter {
    //extends => override Presenter behaviour from contract

    // override presenter functions of BaseContract and ListEquipesContract

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()


    // implements Presenter and instantiate View
    private lateinit var view: ListEquipesContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListEquipesContract.View) {
        this.view = view
    }

    override fun loadData() {

        Log.d("mvpmvp","ListEquipesPresenter")

        val subscribe = api.getEquipesParChampionnat("Spanish La Liga").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ championnat: Championnat ->
                Log.d("mvpmvp","okkkkkkk")
                view.showProgress(false)
                view.loadDataSuccess(championnat)
            }, { error ->
                Log.d("mvpmvp","NOP !!!")
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun onEquipeClick(equipeId: String) {


        val subscribe = api.getJoueursParEquipe(equipeId).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listJoueurs: Joueurs ->
                Log.d("mvpmvp onEquipeClick","okkkkkkk players number "+listJoueurs.player.size)
//                view.showProgress(false)
//                view.loadDataSuccess(championnat)
            }, { error ->
                Log.d("mvpmvp onEquipeClick","NOP onEquipeClick !!!")
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)

//        supportFragmentManager.beginTransaction()
//            .disallowAddToBackStack()
//            .setCustomAnimations(MainActivity.AnimType.SLIDE.getAnimPair().first, MainActivity.AnimType.SLIDE.getAnimPair().second)
//            .replace(R.id.frame, ListEquipesFragment().newInstance(), ListEquipesFragment.TAG)
//            .commit()
    }
}