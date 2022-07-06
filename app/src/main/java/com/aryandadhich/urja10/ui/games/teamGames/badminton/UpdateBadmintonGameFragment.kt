package com.aryandadhich.urja10.ui.games.teamGames.badminton

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
import com.aryandadhich.urja10.databinding.FragmentUpdateBadmintonGameBinding

class UpdateBadmintonGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateBadmintonGameBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: UpdateBadmintonGameViewModel
    private lateinit var viewModelFactory: UpdateBadmintonGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBadmintonGameBinding.inflate(inflater, container, false)
        
        val eventId = UpdateBadmintonGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateBadmintonGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateBadmintonGameFragmentArgs.fromBundle(arguments!!).teamB
        
        viewModelFactory = UpdateBadmintonGameViewModelFactory(eventId, teamAName, teamBName);
        
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateBadmintonGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToBadmintonGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateBadmintonUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }


        return binding.root
    }

    private fun navigateToBadmintonGamesFragment() {
        findNavController().navigate(UpdateBadmintonGameFragmentDirections.actionUpdateBadmintonGameFragmentToBadmintonFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateBadmintonTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateBadmintonTeamBScoreEditText.text.toString().toInt()
        viewModel.updateBadmintonGameScores();
    }

    private fun updateData() {
        binding.fragUpdateBadmintonTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateBadmintonTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}