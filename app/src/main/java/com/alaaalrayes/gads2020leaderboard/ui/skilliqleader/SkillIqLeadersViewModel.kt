package com.alaaalrayes.gads2020leaderboard.ui.skilliqleader

import ApiClient
import ApiInterface
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import com.alaaalrayes.gads2020leaderboard.Model.SkillIQModel
import retrofit2.Call
import retrofit2.Callback

class SkillIqLeadersViewModel : ViewModel() {

    var skillIqMutableLiveData = MutableLiveData<List<SkillIQModel>>()


    fun getLearningLeader() {
        val apiInterface: ApiInterface = ApiClient().create()
        val call = apiInterface.GetSkillIQ()
        call.enqueue(object : Callback<List<SkillIQModel>> {
            override fun onResponse(call: Call<List<SkillIQModel>>, response: retrofit2.Response<List<SkillIQModel>>?) {
                if (response != null) {
                    Log.wtf("mainTest", response.message())
                }
                if (response != null) {
                    if (response.isSuccessful) {
                        Log.wtf("test", "isSuccessful")
                        skillIqMutableLiveData.postValue(response.body())
                    }
                }
            }

            override fun onFailure(call: Call<List<SkillIQModel>>, t: Throwable) {
                Log.wtf("onFailure", t.message)
            }
        })
    }

}