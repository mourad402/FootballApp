package com.example.mvpsample.ui.listejoueurs


import com.example.mvpsample.models.Joueurs
import com.example.mvpsample.ui.base.BaseContract

class ListJoueursContract{
    // define what the view should do
    // display the loaded data and errors in the view + display the result of user interaction

    interface View: BaseContract.View { //implements

        // add functions to the list of functions of View in BaseContract
        // functions to update ui
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(joueurs: Joueurs)
    }

    // define what the presenter should do :
    // load the data and send it to the view + handle equipe click
    interface Presenter: BaseContract.Presenter<View> { //implements

        // add functions to the list of functions of Presenter view in BaseContract
        // function to get user interaction
        fun loadData(idEquipe: String)
    }
}