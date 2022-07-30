package com.aryandadhich.urja10.ui.games.teamGames.volleyball

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentVolleyballBinding
import com.aryandadhich.urja10.ui.games.teamGames.volleyball.*
import com.aryandadhich.urja10.utils.stringUtils

class VolleyballFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentVolleyballBinding
    val binding get() = _binding!!

    private lateinit var viewModel: VolleyballFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVolleyballBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(VolleyballFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.volleyballFragmentRecyclerView.adapter = VolleyballAdapter(VolleyballTeamListener {
                eventId->
            nabigateToGameInfoFragment(eventId)
        }, UpdateVolleyballGameListener {
                game->
            navigateToUpdateVolleyballGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addVolleyballGameFab.visibility = View.GONE;

        binding.addVolleyballGameFab.setOnClickListener{
            navigateToAddVolleyballGame()
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
        viewModel.fetchVolleyballGames()
    }

    private fun navigateToUpdateVolleyballGameFragment(game: VolleyballGame) {
        findNavController().navigate(VolleyballFragmentDirections.actionVolleyballFragmentToUpdateVolleyballGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun nabigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(VolleyballFragmentDirections.actionVolleyballFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddVolleyballGame() {
        findNavController().navigate(VolleyballFragmentDirections.actionVolleyballFragmentToAddVolleyballGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }
}