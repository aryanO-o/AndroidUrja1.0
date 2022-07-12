package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddChessGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner
import kotlinx.android.synthetic.main.fragment_chess.*


class AddChessGameFragment : androidx.fragment.app.Fragment() {
    private lateinit var _binding: FragmentAddChessGameBinding
    val binding get() = _binding
    
    private lateinit var viewModel: AddChessGameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddChessGameBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddChessGameFragmentViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToChessGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.chessAddGameAddBtn.setOnClickListener {
            callViewModelAddChessGame()
        }

        binding.chessAddGameCancleBtn.setOnClickListener {
            goBackToChessGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToChessGamesFragment() {
        findNavController().navigate(AddChessGameFragmentDirections.actionAddChessGameFragmentToChessFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddChessGame() {
        viewModel.playerA = binding.fragAddChessEnterPlayerAEditText.text.toString()
        viewModel.playerB = binding.fragAddChessEnterPlayerBEditText.text.toString();
        viewModel.winner = binding.fragAddChessEnterWinnerEditText.text.toString()
        viewModel.addChessGame();
    }

}