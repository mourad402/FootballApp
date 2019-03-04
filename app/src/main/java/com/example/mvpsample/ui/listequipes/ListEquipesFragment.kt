package com.example.mvpsample.ui.listequipes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpsample.R
import com.example.mvpsample.di.component.DaggerFragmentComponent
import com.example.mvpsample.di.module.FragmentModule
import com.example.mvpsample.models.Championnat
import com.example.mvpsample.models.Equipe
import com.example.mvpsample.ui.listejoueurs.ListJoueursFragment
import com.example.mvpsample.ui.main.MainActivity

import kotlinx.android.synthetic.main.fragment_list_equipes.*
import javax.inject.Inject

class ListEquipesFragment:  Fragment(), ListEquipesContract.View, ListEquipesAdapter.onItemClickListener {
   //extends => override View behaviour from contract

    // implements View and instantiate Presenter
    @Inject lateinit var presenter: ListEquipesContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): ListEquipesFragment {
        return ListEquipesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_list_equipes, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun showProgress(show: Boolean) {

        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("mvpmvp errror", error)
    }

    override fun loadDataSuccess(championnat: Championnat) {
        Log.d("mvpmvp", "loadDataSuccesssssss ")
        Log.d("mvpmvp", "team number in la liga "+championnat.teams.size)



        var adapter = ListEquipesAdapter(activity!!.applicationContext, championnat.teams.toMutableList(), this)
        recyclerViewListEquipes!!.layoutManager = GridLayoutManager(activity,2)
        recyclerViewListEquipes!!.itemAnimator =  DefaultItemAnimator()
        recyclerViewListEquipes!!.setHasFixedSize(true)
        recyclerViewListEquipes!!.adapter = adapter
    }

    private fun initView() {
        presenter.loadData()
    }


    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }


    companion object {
        val TAG: String = "ListEquipesFragment"
    }

    override fun itemDetail(equipeId: String) {

        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .setCustomAnimations(MainActivity.AnimType.SLIDE.getAnimPair().first, MainActivity.AnimType.SLIDE.getAnimPair().second)
            .add(R.id.frame, ListJoueursFragment().newInstance(equipeId))
            .commit()

//        presenter.onEquipeClick(equipeId)
    }

    override fun showListJoueursFragment(equipeId: String) {


    }

}