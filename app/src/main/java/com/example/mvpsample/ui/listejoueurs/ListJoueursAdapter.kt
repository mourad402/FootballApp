package com.example.mvpsample.ui.listejoueurs

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mvpsample.R
import com.example.mvpsample.models.Equipe
import com.example.mvpsample.models.Joueur
import com.example.mvpsample.ui.listequipes.ListEquipesAdapter

class ListJoueursAdapter (private val context: Context, private val list: MutableList<Joueur>, fragment: Fragment)
    :RecyclerView.Adapter<ListJoueursAdapter.ListJoueursViewHolder>(){



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListJoueursViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_joueur_layout, parent, false)
        return ListJoueursAdapter.ListJoueursViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListJoueursViewHolder, p1: Int) {

        val joueur = list[p1]

        Glide.with(context)
            .load(joueur.strThumb)
            .into(holder.image)
        holder.nomJoueur.text = joueur.strPlayer
        holder.positioJoueur.text = joueur.strPosition
        holder.dateNaissanceJoueur.text = joueur.dateBorn
        holder.prixJoueur.text = joueur.strSigning


    }


    class ListJoueursViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val nomJoueur = itemView.findViewById<TextView>(R.id.nom_joueur)
        val positioJoueur = itemView.findViewById<TextView>(R.id.position_joueur)
        val dateNaissanceJoueur = itemView.findViewById<TextView>(R.id.date_naissance_joueur)
        val prixJoueur = itemView.findViewById<TextView>(R.id.prix_joueur)


        val image = itemView.findViewById<ImageView>(R.id.joueurImageView)

        fun bind(item: Equipe) {
            // title = item.post
            // body etc.
        }
    }



}
//
//
//    override fun onBindViewHolder(holder: ListEquipesViewHolder, position: Int) {
//        var team = list[position]
//
//        // holder!!.bind(post)
//        holder.title!!.text = team.strTeam // !! converts any value to a non-null type and throws an exception if the value is null
//        holder.body.text = team.strTeam
//
//        holder.layout!!.setOnClickListener {
//            listener.itemDetail(team.idTeam.toString())
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEquipesViewHolder {

//    }
//
//    fun removeAt(position: Int) {
//        list.removeAt(position)
//        notifyItemRemoved(position)
//    }
//
//    class ListJoueursViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
//        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
//        val title = itemView.findViewById<TextView>(R.id.item_title)
//        val body = itemView.findViewById<TextView>(R.id.item_body)
//
//        fun bind(item: Equipe) {
//            // title = item.post
//            // body etc.
//        }
//    }
//
//
//}