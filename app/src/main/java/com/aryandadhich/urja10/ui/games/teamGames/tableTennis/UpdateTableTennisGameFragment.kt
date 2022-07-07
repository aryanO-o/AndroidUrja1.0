package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentUpdateTableTennisGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.tennis.UpdateTennisGameFragmentDirections


class UpdateTableTennisGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateTableTennisGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateTableTennisGameViewModel
    private lateinit var viewModelFactory: UpdateTableTennisGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTableTennisGameBinding.inflate(inflater, container, false)
        val eventId = UpdateTableTennisGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamA = UpdateTableTennisGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamB = UpdateTableTennisGameFragmentArgs.fromBundle(arguments!!).teamB

        viewModelFactory = UpdateTableTennisGameViewModelFactory(eventId, teamA, teamB)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateTableTennisGameViewModel::class.java)

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToTableTennisGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateTableTennisUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }


        return binding.root
    }

    private fun navigateToTableTennisGamesFragment() {
        findNavController().navigate(UpdateTableTennisGameFragmentDirections.actionUpdateTableTennisGameFragmentToTableTennisFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateTableTennisTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateTableTennisTeamBScoreEditText.text.toString().toInt()
        viewModel.updateTableTennisGameScores();
    }

    private fun updateData() {
        binding.fragUpdateTableTennisTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateTableTennisTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}