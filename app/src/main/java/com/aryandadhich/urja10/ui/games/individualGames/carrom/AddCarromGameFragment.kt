package com.aryandadhich.urja10.ui.games.individualGames.carrom

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
import com.aryandadhich.urja10.databinding.FragmentAddCarromGameBinding


class AddCarromGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddCarromGameBinding
    val binding get() = _binding

    private lateinit var viewModel: AddCarromGameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCarromGameBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddCarromGameFragmentViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToCarromGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.carromAddGameAddBtn.setOnClickListener {
            callViewModelAddCarromGame()
        }

        binding.carromAddGameCancleBtn.setOnClickListener {
            goBackToCarromGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToCarromGamesFragment() {
        findNavController().navigate(AddCarromGameFragmentDirections.actionAddCarromGameFragmentToCarromFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddCarromGame() {
        viewModel.playerA = binding.fragAddCarromEnterPlayerAEditText.text.toString()
        viewModel.playerB = binding.fragAddCarromEnterPlayerBEditText.text.toString();
        viewModel.winner = binding.fragAddCarromEnterWinnerEditText.text.toString()
        viewModel.addCarromGame();
    }

}