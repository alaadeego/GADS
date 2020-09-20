package com.alaaalrayes.gads2020leaderboard.ui.learningleaders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import com.alaaalrayes.gads2020leaderboard.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_learning_leader.view.*

//AsyncListDiffer
class LearningLeaderAdapter() :

    RecyclerView.Adapter<LearningLeaderAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<LearningLeaderModel> =
        AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_learning_leader, parent, false)


        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: LearningLeaderAdapter.ViewHolder, position: Int) {
//        holder.bind(values.get(position))
        val obj = asyncListDiffer.currentList.get(position)
        holder.name.text = obj.name
        holder.details_txt.text =
            obj.hours.toString() + " learning hours, " + obj.country

        Picasso.get().load(obj.badgeUrl).into(holder.img)


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name = view.name_txt
        var details_txt = view.details_txt
        var img = view.img

        fun bind(item: LearningLeaderModel) {
//            holder.name.text = values.get(position).name
//            holder.details_txt.text =
//                values.get(position).hours.toString() + " learning hours, " + values.get(position).country
//
//            Picasso.get().load(values.get(position).badgeUrl).into(holder.img)
        }
    }

    fun submitList(newItems: List<LearningLeaderModel>) {
        asyncListDiffer.submitList(newItems)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LearningLeaderModel>() {
            override fun areItemsTheSame(
                oldItem: LearningLeaderModel, newItem: LearningLeaderModel
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: LearningLeaderModel, newItem: LearningLeaderModel
            ): Boolean = oldItem == newItem
        }
    }

}
