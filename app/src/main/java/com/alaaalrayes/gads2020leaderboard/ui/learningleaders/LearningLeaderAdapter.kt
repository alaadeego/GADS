package com.alaaalrayes.gads2020leaderboard.ui.learningleaders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import com.alaaalrayes.gads2020leaderboard.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_learning_leader.view.*


class LearningLeaderAdapter(val values: List<LearningLeaderModel>, val context: Context) :
    RecyclerView.Adapter<LearningLeaderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_learning_leader, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return values.size
    }

    override fun onBindViewHolder(holder: LearningLeaderAdapter.ViewHolder, position: Int) {

        holder.name.text = values.get(position).name
        holder.details_txt.text =  values.get(position).hours.toString()+ " learning hours, " + values.get(position).country

        Picasso.get().load(values.get(position).badgeUrl).into(holder.img)


    }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            var name = view.name_txt
            var details_txt = view.details_txt
            var img = view.img


        }


    }
