package com.alaaalrayes.gads2020leaderboard.ui.learningleaders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alaaalrayes.gads2020leaderboard.R
import kotlinx.android.synthetic.main.learning_leaders_fragment.*

class LearningLeadersFragment : Fragment() {

    private lateinit var learningleader_recyclerView: RecyclerView
    private lateinit var learningleader_progressBar: ProgressBar
    private lateinit var viewModel: LearningLeadersViewModel
    private lateinit var mAdapter: LearningLeaderAdapter

    companion object {
        fun newInstance() =
            LearningLeadersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.learning_leaders_fragment, container, false)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learningleader_recyclerView = view.findViewById(R.id.learningleader_recyclerView)
        mAdapter = LearningLeaderAdapter()
        learningleader_recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        learningleader_recyclerView.adapter = mAdapter

        viewModel = ViewModelProvider(this).get(LearningLeadersViewModel::class.java)
        viewModel.getLearningLeader()
        viewModel.LearningLeaderMutableLiveData.observe(requireActivity(), Observer { list ->
            mAdapter.submitList(list)
        })

    }

}