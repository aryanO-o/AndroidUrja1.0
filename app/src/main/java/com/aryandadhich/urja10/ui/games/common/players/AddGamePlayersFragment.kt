package com.aryandadhich.urja10.ui.games.common.players

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddGamePlayersBinding


class AddGamePlayersFragment : Fragment() {
    private lateinit var _binding: FragmentAddGamePlayersBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddGamePlayersFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddGamePlayersBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddGamePlayersFragmentViewModel::class.java);

        val teamId = AddGamePlayersFragmentArgs.fromBundle(arguments!!).teamId

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateToPlayerFragment(teamId)
                viewModel.doneNavigating();
            }
        })

        binding.fragAddPlayerCancleBtn.setOnClickListener {
            navigateToPlayerFragment(teamId);
        }

        binding.fragAddPlayerAddBtn.setOnClickListener {
            addBtnClicked(teamId)
        }

        return binding.root;
    }

    private fun addBtnClicked(teamId: String) {
        viewModel.jerseyNo = binding.fragAddPlayerJerseyNoEditText.text.toString().toInt()
        viewModel.name = binding.fragAddPlayerNameEditText.text.toString()

        viewModel.addPlayer(teamId);
    }

    private fun navigateToPlayerFragment(teamId: String) {
        findNavController().navigate(AddGamePlayersFragmentDirections.actionAddGamePlayersFragmentToPlayerFragment(teamId))
    }

}