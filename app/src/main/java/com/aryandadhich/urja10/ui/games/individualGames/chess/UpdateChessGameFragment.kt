package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentUpdateChessGameBinding

class UpdateChessGameFragment : Fragment() {

    private lateinit var _binding: FragmentUpdateChessGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateChessGameViewModel
    private lateinit var viewModelFactory: UpdateChessGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateChessGameBinding.inflate(inflater, container, false)

        val eventId = UpdateChessGameFragmentArgs.fromBundle(arguments!!).eventId

        viewModelFactory = UpdateChessGameViewModelFactory(eventId);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateChessGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToChessGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.chessUpdateGameBtn.setOnClickListener {
            onUpdateBtnClicked()
        }
        return binding.root
    }

    private fun navigateToChessGamesFragment() {
        findNavController().navigate(UpdateChessGameFragmentDirections.actionUpdateChessGameFragmentToChessFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.playerA = binding.fragAddChessEnterPlayerAEditText.text.toString()
        viewModel.playerB = binding.fragAddChessEnterPlayerBEditText.text.toString()
        viewModel.winner = binding.fragAddChessEnterWinnerEditText.text.toString();
        viewModel.updateChessGameScores();
    }

    private fun updateData() {
        binding.fragAddChessEnterPlayerAEditText.setText(viewModel.game.value!!.playerA)
        binding.fragAddChessEnterPlayerBEditText.setText(viewModel.game.value!!.playerB)
        binding.fragAddChessEnterWinnerEditText.setText(viewModel.game.value!!.winner)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}