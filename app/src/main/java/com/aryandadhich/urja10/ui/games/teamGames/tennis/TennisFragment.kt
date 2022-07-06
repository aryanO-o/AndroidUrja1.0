package com.aryandadhich.urja10.ui.games.teamGames.tennis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentTennisBinding
import com.aryandadhich.urja10.ui.games.teamGames.tennis.*
import com.aryandadhich.urja10.utils.stringUtils


class TennisFragment : Fragment() {
    private lateinit var _binding: FragmentTennisBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: TennisFragmentViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTennisBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(TennisFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        binding.tennisFragmentRecyclerView.adapter = TennisAdapter(TennisTeamListener {
            navigateToGameInfoFragment(it)
        }, UpdateTennisGameListener {
                game->
            navigateToUpdateTennisFragment(game)
        })

        if(stringUtils.role == "")
            binding.addTennisGameFab.visibility = View.GONE;

        binding.addTennisGameFab.setOnClickListener{
            navigateToAddTennisGameFragment()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it) {
                removeLoadingScreen()
            }
        })

        return binding.root
    }

    private fun navigateToAddTennisGameFragment() {
        findNavController().navigate(TennisFragmentDirections.actionTennisFragmentToAddTennisGameFragment())
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    private fun navigateToUpdateTennisFragment(game: TennisGame) {
        findNavController().navigate(TennisFragmentDirections.actionTennisFragmentToUpdateTennisGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun navigateToGameInfoFragment(it: String) {
        findNavController().navigate(TennisFragmentDirections.actionTennisFragmentToGameInfoFragment(it));
    }

}