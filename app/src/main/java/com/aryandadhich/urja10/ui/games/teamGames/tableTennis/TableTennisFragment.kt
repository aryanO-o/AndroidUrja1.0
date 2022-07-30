package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentTableTennisBinding
import com.aryandadhich.urja10.ui.games.teamGames.tennis.*
import com.aryandadhich.urja10.utils.stringUtils


class TableTennisFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentTableTennisBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: TableTennisFragmentViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableTennisBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(TableTennisFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.tableTennisFragmentRecyclerView.adapter = TableTennisAdapter(TableTennisTeamListener {
            navigateToGameInfoFragment(it)
        }, UpdateTableTennisGameListener {
                game->
            navigateToUpdateTableTennisFragment(game)
        })

        if(stringUtils.role == "")
            binding.addTableTennisGameFab.visibility = View.GONE;

        binding.addTableTennisGameFab.setOnClickListener{
            navigateToAddTableTennisGameFragment()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it) {
                removeLoadingScreen()
            }
        })

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData()
            startLoadingScreen()
            pullToRefresh.isRefreshing = false
        }

        return binding.root
    }

    private fun refreshData() {
        viewModel.fetchTableTennisGames()
    }

    private fun navigateToAddTableTennisGameFragment() {
        findNavController().navigate(TableTennisFragmentDirections.actionTableTennisFragmentToAddTableTennisGameFragment())
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }

    private fun navigateToUpdateTableTennisFragment(game: TableTennisGame) {
        findNavController().navigate(TableTennisFragmentDirections.actionTableTennisFragmentToUpdateTableTennisGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun navigateToGameInfoFragment(it: String) {
        findNavController().navigate(TableTennisFragmentDirections.actionTableTennisFragmentToGameInfoFragment(it))
    }

}