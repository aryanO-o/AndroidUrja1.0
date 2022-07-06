package com.aryandadhich.urja10.ui.games.teamGames.tennis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentUpdateTennisGameBinding

class UpdateTennisGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateTennisGameBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: UpdateTennisGameViewModel
    private lateinit var viewModelFactory: UpdateTennisGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateTennisGameBinding.inflate(inflater, container, false)
        val eventId = UpdateTennisGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamA = UpdateTennisGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamB = UpdateTennisGameFragmentArgs.fromBundle(arguments!!).teamB
        
        viewModelFactory = UpdateTennisGameViewModelFactory(eventId, teamA, teamB);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateTennisGameViewModel::class.java)
        
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToTennisGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateTennisUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }


        return binding.root
    }

    private fun navigateToTennisGamesFragment() {
        findNavController().navigate(UpdateTennisGameFragmentDirections.actionUpdateTennisGameFragmentToTennisFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateTennisTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateTennisTeamBScoreEditText.text.toString().toInt()
        viewModel.updateTennisGameScores();
    }

    private fun updateData() {
        binding.fragUpdateTennisTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateTennisTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}