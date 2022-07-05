package com.aryandadhich.urja10.ui.games.teamGames.basketball

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
import com.aryandadhich.urja10.databinding.FragmentUpdateBasketballGameBinding


class UpdateBasketballGameFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentUpdateBasketballGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateBasketballGameViewModel;
    private lateinit var viewModelFactory: UpdateBasketballGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBasketballGameBinding.inflate(inflater, container, false)

        val eventId = UpdateBasketballGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateBasketballGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateBasketballGameFragmentArgs.fromBundle(arguments!!).teamB
        viewModelFactory = UpdateBasketballGameViewModelFactory(eventId, teamAName, teamBName)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateBasketballGameViewModel::class.java)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it)
                navigateToBasketballGamesFragment()
        })

        binding.fragUpdateBasketballUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }


        return binding.root
    }

    private fun navigateToBasketballGamesFragment() {
        findNavController().navigate(UpdateBasketballGameFragmentDirections.actionUpdateBasketballGameFragmentToBasketballFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateBasketballTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateBasketballTeamBScoreEditText.text.toString().toInt()
        viewModel.updateBasketballGameScores();
    }

    private fun updateData() {
        binding.fragUpdateBasketballTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateBasketballTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}