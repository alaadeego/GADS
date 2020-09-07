package com.alaaalrayes.gads2020leaderboard.Model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LearningLeaderModel {

    @SerializedName("name")
    @Expose
     val name: String? = null

    @SerializedName("hours")
    @Expose
     val hours: Int? = null

    @SerializedName("country")
    @Expose
     val country: String? = null

    @SerializedName("badgeUrl")
    @Expose
     val badgeUrl: String? = null

}