package com.aryandadhich.urja10.ui.games.teamGames.badminton

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentBadmintonBinding

import com.aryandadhich.urja10.ui.games.teamGames.badminton.BadmintonTeamListener
import com.aryandadhich.urja10.utils.stringUtils


class BadmintonFragment : Fragment() {
    private lateinit var _binding: FragmentBadmintonBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: BadmintonFragmentViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBadmintonBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(BadmintonFragmentViewModel::class.java);
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.badmintonFragmentRecyclerView.adapter = BadmintonAdapter(BadmintonTeamListener {
            navigateToGameInfoFragment(it)
        }, UpdateBadmintonGameListener {
                game->
            navigateToUpdateBadmintonFragment(game)
        })

        if(stringUtils.role == "")
            binding.addBadmintonGameFab.visibility = View.GONE;

        binding.addBadmintonGameFab.setOnClickListener{
            navigateToAddBadmintonGameFragment()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it) {
                removeLoadingScreen()
            }
        })

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData() // your code
            pullToRefresh.isRefreshing = false
        }
        
        return binding.root
    }

    private fun refreshData() {
        viewModel.fetchBadmintonGames();
    }

    private fun navigateToAddBadmintonGameFragment() {
        findNavController().navigate(BadmintonFragmentDirections.actionBadmintonFragmentToAddBadmintonGameFragment())
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    private fun navigateToUpdateBadmintonFragment(game: BadmintonGame) {
        findNavController().navigate(BadmintonFragmentDirections.actionBadmintonFragmentToUpdateBadmintonGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun navigateToGameInfoFragment(it: String) {
        findNavController().navigate(BadmintonFragmentDirections.actionBadmintonFragmentToGameInfoFragment(it))
    }

}