package com.example.mvpsample.ui.base

class BaseContract {


    interface View

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }



}