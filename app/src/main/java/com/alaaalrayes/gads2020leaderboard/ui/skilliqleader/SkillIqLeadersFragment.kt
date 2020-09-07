package com.alaaalrayes.gads2020leaderboard.ui.skilliqleader

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alaaalrayes.gads2020leaderboard.Model.LearningLeaderModel
import com.alaaalrayes.gads2020leaderboard.Model.SkillIQModel
import com.alaaalrayes.gads2020leaderboard.R
import com.alaaalrayes.gads2020leaderboard.ui.learningleaders.LearningLeaderAdapter
import com.alaaalrayes.gads2020leaderboard.ui.learningleaders.LearningLeadersViewModel
import kotlinx.android.synthetic.main.learning_leaders_fragment.*
import kotlinx.android.synthetic.main.skill_iq_leaders_fragment.*

class SkillIqLeadersFragment : Fragment() {

    companion object {
        fun newInstance() =
            SkillIqLeadersFragment()
    }

    private lateinit var mAdapter: SkillIQAdapter
    lateinit  var SkillIQList: List<SkillIQModel>

    private lateinit var viewModel: SkillIqLeadersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.skill_iq_leaders_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(SkillIqLeadersViewModel::class.java)
        viewModel!!.getLearningLeader()
        viewModel!!.skillIqMutableLiveData.observe(requireActivity() , Observer { list ->
            Log.w("OrdersMutableLiveData" , "OrdersMutableLiveData")
            Toast.makeText(requireContext(), list.get(0).name, Toast.LENGTH_SHORT).show()
            SkillIQList = list
            initSkillIQRecyclerView()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun initSkillIQRecyclerView() {
        mAdapter = SkillIQAdapter(SkillIQList,requireContext())
        skilliq_recyclerView.setHasFixedSize(true)
        skilliq_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        skilliq_recyclerView.adapter = mAdapter
    }

}