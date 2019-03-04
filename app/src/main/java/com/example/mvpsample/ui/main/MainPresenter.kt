package com.example.mvpsample.ui.main

import io.reactivex.disposables.CompositeDisposable


/*
What is Presenter?
In MVP, Presenter retrieves the data from the Model, applies the UI logic and manages the state of the View,
decides what to display and reacts to user input notifications from the View.
 */

//In kotlin implementing interfaces and extending existing classes uses the same common syntax :
//
//So class ... : ...

class MainPresenter: MainContract.Presenter { // extends

    // implements Presenter and instantiate View
    // override presenter functions of BaseContract and MainContract
    // lateinit to avoid null checks
    private lateinit var view: MainContract.View

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showListEquipesFragment() // as default
    }

    private val subscriptions = CompositeDisposable()
    override fun subscribe() {
    }
    override fun unsubscribe() {
        subscriptions.clear()
    }



//    override fun onListEquipesItemClick() {
//        view.showListJoueursFragment()
//    }
}