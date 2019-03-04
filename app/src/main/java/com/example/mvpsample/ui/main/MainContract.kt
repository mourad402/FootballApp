package com.example.mvpsample.ui.main

import com.example.mvpsample.ui.base.BaseContract

/*
What is Contract?
Presenter and its corresponding View is defined in a Contract interface class, making the code more readable
and the connection between the two easier to understand.
 */

//In kotlin implementing interfaces and extending existing classes uses the same common syntax :
//
//So class ... : ...
class MainContract {

    // define what the view should do
    // display the results of user interactions
    interface View: BaseContract.View { //implements
        // add functions to the list of functions of View in BaseContract
        // functions to update ui
        fun showListEquipesFragment()
//        fun showListJoueursFragment()
    }

    // define what the presenter should do
    // handle the actions made by user
    interface Presenter: BaseContract.Presenter<MainContract.View> { //implements
        // add functions to the list of functions of Presenter iew in BaseContract
        // function to get user interaction
        //fun onListEquipesItemClick() // add onListEquipesItemClick to the list of functions in BaseContract Presenter interface
    }

}