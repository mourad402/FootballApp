package com.example.mvpsample.ui.listejoueurs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvpsample.R
import com.example.mvpsample.di.component.DaggerFragmentComponent
import com.example.mvpsample.di.module.FragmentModule
import com.example.mvpsample.models.Championnat
import com.example.mvpsample.models.Joueurs
import com.example.mvpsample.ui.listequipes.ListEquipesContract
import com.example.mvpsample.ui.listequipes.ListEquipesFragment
import com.example.mvpsample.utils.Constants
import kotlinx.android.synthetic.main.fragment_list_equipes.*
import kotlinx.android.synthetic.main.fragment_list_joueurs.*
import javax.inject.Inject

class ListJoueursFragment:  Fragment(), ListJoueursContract.View {

    // implements View and instantiate Presenter
    @Inject
    lateinit var presenter: ListJoueursContract.Presenter

    private lateinit var rootView: View

    fun newInstance(equipeId: String): ListJoueursFragment {

        val fragment = ListJoueursFragment()
        val args = Bundle()
        args.putString(Constants.ID_EQUIPE,equipeId)
        fragment.arguments = args
        return fragment

        //return ListJoueursFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_list_joueurs, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    private fun initView() {

        val idEquipe = arguments!!.getString(Constants.ID_EQUIPE, "nooop")

        Log.d("mvpmvp", "initView  $idEquipe")

        presenter.loadData(idEquipe)
    }
    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        listComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBarJoueurs.visibility = View.VISIBLE
        } else {
            progressBarJoueurs.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Log.e("mvpmvp errror jr", error)
    }

    override fun loadDataSuccess(joueurs: Joueurs) {

        Log.d("mvpmvp", "loadDataSuccesssssss ")
        Log.d("mvpmvp", "joueurs number  "+joueurs.player.size)

        var adapter = ListJoueursAdapter(activity!!.applicationContext, joueurs.player.toMutableList(), this)
        recyclerViewListJoueurs!!.layoutManager = LinearLayoutManager(activity)
        recyclerViewListJoueurs!!.itemAnimator =  DefaultItemAnimator()
        recyclerViewListJoueurs!!.setHasFixedSize(true)
        recyclerViewListJoueurs!!.adapter = adapter

    }


}
