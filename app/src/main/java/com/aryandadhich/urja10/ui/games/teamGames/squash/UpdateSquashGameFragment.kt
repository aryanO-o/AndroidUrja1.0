package com.aryandadhich.urja10.ui.games.teamGames.squash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentUpdateSquashGameBinding


class UpdateSquashGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateSquashGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateSquashGameViewModel
    private lateinit var viewModelFactory: UpdateSquashGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateSquashGameBinding.inflate(inflater, container, false)

        val eventId = UpdateSquashGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateSquashGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateSquashGameFragmentArgs.fromBundle(arguments!!).teamB

        viewModelFactory = UpdateSquashGameViewModelFactory(eventId, teamAName, teamBName);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateSquashGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToSquashGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateSquashUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }


        return binding.root
    }

    private fun navigateToSquashGamesFragment() {
        findNavController().navigate(UpdateSquashGameFragmentDirections.actionUpdateSquashGameFragmentToSquashFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateSquashTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateSquashTeamBScoreEditText.text.toString().toInt()
        viewModel.updateSquashGameScores();
    }

    private fun updateData() {
        binding.fragUpdateSquashTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateSquashTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}