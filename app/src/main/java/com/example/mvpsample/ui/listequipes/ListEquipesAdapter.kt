package com.example.mvpsample.ui.listequipes

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

class ListEquipesAdapter(private val context: Context, private val list: MutableList<Equipe>, fragment: Fragment)
    : RecyclerView.Adapter<ListEquipesAdapter.ListEquipesViewHolder>() {




    private val listener: ListEquipesAdapter.onItemClickListener

    init {
        this.listener = fragment as ListEquipesAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListEquipesViewHolder, position: Int) {
        val team = list[position]

        // holder!!.bind(post)
        holder.title!!.text = team.strTeam // !! converts any value to a non-null type and throws an exception if the value is null
        holder.body.text = team.strTeam


        Glide.with(context)
            .load(team.strTeamBadge)
            .into(holder.image)

        holder.layout!!.setOnClickListener {
            listener.itemDetail(team.idTeam.toString())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListEquipesViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_equipe_layout, parent, false)
        return ListEquipesAdapter.ListEquipesViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ListEquipesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val body = itemView.findViewById<TextView>(R.id.item_body)
        val image = itemView.findViewById<ImageView>(R.id.equipeImageView)

        fun bind(item: Equipe) {
            // title = item.post
            // body etc.
        }
    }

    interface onItemClickListener {
//        fun itemRemoveClick(post: Post)
        fun itemDetail(postId : String)
    }
}