package com.example.mvpsample.ui.base

class BaseFragmentContract {


    interface View: BaseContract.View {

    }

        interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
    }



}