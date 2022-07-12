package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentChessBinding
import com.aryandadhich.urja10.utils.stringUtils


class ChessFragment : Fragment() {
    private lateinit var _binding: FragmentChessBinding
    val binding get() = _binding;
    
    private lateinit var viewModel: ChessFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChessBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(ChessFragmentViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.chessFragmentRecyclerView.adapter = ChessAdapter(ChessTeamListener {
                eventId->
            navigateToGameInfoFragment(eventId)
        }, UpdateChessGameListener {
                game->
            navigateToUpdateChessGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addChessGameFab.visibility = View.GONE;

        binding.addChessGameFab.setOnClickListener{
            navigateToAddChessGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        return binding.root
    }

    private fun navigateToUpdateChessGameFragment(game: ChessGame) {
        findNavController().navigate(ChessFragmentDirections.actionChessFragmentToUpdateChessGameFragment(game.id))
    }

    private fun navigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(ChessFragmentDirections.actionChessFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddChessGame() {
        findNavController().navigate(ChessFragmentDirections.actionChessFragmentToAddChessGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
}