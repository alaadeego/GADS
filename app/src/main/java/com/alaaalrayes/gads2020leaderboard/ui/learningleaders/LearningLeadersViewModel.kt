package com.alaaalrayes.gads2020leaderboard.ui.learningleaders

import ApiClient
import ApiInterface
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import retrofit2.Call
import retrofit2.Callback

class LearningLeadersViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var LearningLeaderMutableLiveData = MutableLiveData<List<LearningLeaderModel>>()


    fun getLearningLeader() {
        val apiInterface: ApiInterface = ApiClient().create()
        val call = apiInterface.GetLearningLeaders()
        call.enqueue(object : Callback<List<LearningLeaderModel>> {
            override fun onResponse(call: Call<List<LearningLeaderModel>>, response: retrofit2.Response<List<LearningLeaderModel>>?) {
                if (response != null) {
                    Log.wtf("mainTest", response.message())
                }
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.wtf("test", "isSuccessful")
                        LearningLeaderMutableLiveData.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<List<LearningLeaderModel>>, t: Throwable) {
                Log.wtf("onFailure", t.message)
            }
        })
    }

}