package com.aryandadhich.urja10.ui.games.teamGames.football

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentFootballBinding
import com.aryandadhich.urja10.utils.stringUtils
import kotlinx.android.synthetic.*


class FootballFragment : Fragment() {
    private lateinit var _binding: FragmentFootballBinding
    val binding get() = _binding!!

    private lateinit var viewModel: FootballFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFootballBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(FootballFragmentViewModel::class.java);
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.footballFragmentRecyclerView.adapter = FootballAdapter(FootballTeamListener {
            eventId->
                findNavController().navigate(FootballFragmentDirections.actionFootballFragmentToGameInfoFragment(eventId))
        }, UpdateFootballGameListener {
            game->
            findNavController().navigate(FootballFragmentDirections.actionFootballFragmentToUpdateFootballGameFragment(game.id, game.teamA, game.teamB))
        })

        if(stringUtils.role == "")
            binding.addFootballGameFab.visibility = View.GONE;

        binding.addFootballGameFab.setOnClickListener{
            navigateToAddFootballGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData() // your code
            pullToRefresh.isRefreshing = false
        }
        return binding.root
    }

    private fun refreshData() {
        viewModel.fetchFootballGames()
    }

    private fun navigateToAddFootballGame() {
        findNavController().navigate(FootballFragmentDirections.actionFootballFragmentToAddFootballGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
}