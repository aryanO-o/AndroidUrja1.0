package com.aryandadhich.urja10.ui.games.teamGames.cricket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentCricketBinding
import com.aryandadhich.urja10.utils.stringUtils


class CricketFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentCricketBinding
    val binding get() = _binding!!

    private lateinit var viewModel: CricketFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCricketBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(CricketFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cricketFragmentRecyclerView.adapter = CricketAdapter(CricketTeamListener {
                eventId->
            navigateToGameInfoFragment(eventId)
        }, UpdateCricketGameListener {
                game->
            navigateToUpdateCricketGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addCricketGameFab.visibility = View.GONE;

        binding.addCricketGameFab.setOnClickListener{
            navigateToAddCricketGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        return binding.root
    }

    private fun navigateToUpdateCricketGameFragment(game: CricketGame) {
        findNavController().navigate(CricketFragmentDirections.actionCricketFragmentToUpdateCricketGameFragment(game.id, game.teamA, game.teamB))
     }

    private fun navigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(CricketFragmentDirections.actionCricketFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddCricketGame() {
        findNavController().navigate(CricketFragmentDirections.actionCricketFragmentToAddCricketGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
}