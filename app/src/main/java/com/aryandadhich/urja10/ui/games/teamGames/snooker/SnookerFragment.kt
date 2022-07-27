package com.aryandadhich.urja10.ui.games.teamGames.snooker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentSnookerBinding
import com.aryandadhich.urja10.ui.games.teamGames.snooker.*
import com.aryandadhich.urja10.ui.games.teamGames.squash.SquashFragmentDirections
import com.aryandadhich.urja10.utils.stringUtils


class SnookerFragment : Fragment() {
    private lateinit var _binding: FragmentSnookerBinding
    val binding get() = _binding!!

    private lateinit var viewModel: SnookerFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSnookerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(SnookerFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.snookerFragmentRecyclerView.adapter = SnookerAdapter(SnookerTeamListener {
                eventId->
            nabigateToGameInfoFragment(eventId)
        }, UpdateSnookerGameListener {
                game->
            navigateToUpdateSnookerGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addSnookerGameFab.visibility = View.GONE;

        binding.addSnookerGameFab.setOnClickListener{
            navigateToAddSnookerGame()
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
        viewModel.fetchSnookerGames()
    }

    private fun navigateToUpdateSnookerGameFragment(game: SnookerGame) {
        findNavController().navigate(SnookerFragmentDirections.actionSnookerFragmentToUpdateSnookerGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun nabigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(SnookerFragmentDirections.actionSnookerFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddSnookerGame() {
        findNavController().navigate(SnookerFragmentDirections.actionSnookerFragmentToAddSnookerGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
}