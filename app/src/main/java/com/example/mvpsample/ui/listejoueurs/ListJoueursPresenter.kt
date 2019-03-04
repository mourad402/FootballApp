package com.example.mvpsample.ui.listejoueurs

import android.os.Bundle
import android.util.Log
import com.example.mvpsample.api.ApiServiceInterface
import com.example.mvpsample.models.Championnat
import com.example.mvpsample.models.Joueurs
import com.example.mvpsample.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListJoueursPresenter: ListJoueursContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()


    // implements Presenter and instantiate View
    private lateinit var view: ListJoueursContract.View

    override fun loadData(idEquipe: String) {


        Log.d("mvpmvp", "ListJoueursPresenter loadData  $idEquipe")

        val subscribe = api.getJoueursParEquipe(idEquipe).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listJoueurs: Joueurs ->
                Log.d("mvpmvp","ListJoueursPresenter loadData okkkkkkk players number "+listJoueurs.player.size)
                view.showProgress(false)
                view.loadDataSuccess(listJoueurs)
            }, { error ->
                Log.d("mvpmvp","ListJoueursPresenter loadData NOP onEquipeClick !!!")
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
        }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListJoueursContract.View) {
        this.view = view
    }

}