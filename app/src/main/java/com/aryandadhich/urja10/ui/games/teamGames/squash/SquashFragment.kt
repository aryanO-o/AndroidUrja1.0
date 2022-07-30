package com.aryandadhich.urja10.ui.games.teamGames.squash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentSquashBinding
import com.aryandadhich.urja10.utils.stringUtils

class SquashFragment : Fragment() {
    private lateinit var _binding: FragmentSquashBinding
    val binding get() = _binding!!

    private lateinit var viewModel: SquashFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSquashBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(SquashFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.squashFragmentRecyclerView.adapter = SquashAdapter(SquashTeamListener {
                eventId->
            nabigateToGameInfoFragment(eventId)
        }, UpdateSquashGameListener {
                game->
                navigateToUpdateSquashGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addSquashGameFab.visibility = View.GONE;

        binding.addSquashGameFab.setOnClickListener{
            navigateToAddSquashGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
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
        viewModel.fetchSquashGames();
    }

    private fun navigateToUpdateSquashGameFragment(game: SquashGame) {
        findNavController().navigate(SquashFragmentDirections.actionSquashFragmentToUpdateSquashGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun nabigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(SquashFragmentDirections.actionSquashFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddSquashGame() {
        findNavController().navigate(SquashFragmentDirections.actionSquashFragmentToAddSquashGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }
}