package com.alaaalrayes.gads2020leaderboard.ui.learningleaders

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
import com.alaaalrayes.gads2020leaderboard.R
import kotlinx.android.synthetic.main.learning_leaders_fragment.*

class LearningLeadersFragment : Fragment() {

    companion object {
        fun newInstance() =
            LearningLeadersFragment()
    }

    private lateinit var viewModel: LearningLeadersViewModel
    private lateinit var mAdapter: LearningLeaderAdapter
    lateinit  var LearningLeaderList: List<LearningLeaderModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.learning_leaders_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LearningLeadersViewModel::class.java)
        viewModel!!.getLearningLeader()
        viewModel!!.LearningLeaderMutableLiveData.observe(requireActivity() , Observer { list ->
            Log.w("OrdersMutableLiveData" , "OrdersMutableLiveData")
            Toast.makeText(requireContext(), list.get(0).name, Toast.LENGTH_SHORT).show()
            LearningLeaderList = list
            initLearningLeaderRecyclerView()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun initLearningLeaderRecyclerView() {
        mAdapter = LearningLeaderAdapter(LearningLeaderList,requireContext())
        learningleader_recyclerView.setHasFixedSize(true)
        learningleader_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        learningleader_recyclerView.adapter = mAdapter
    }


}