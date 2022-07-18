package com.aryandadhich.urja10.ui.games.teamGames.football

import android.app.AlertDialog
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
import com.aryandadhich.urja10.databinding.FragmentUpdateFootballGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.basketball.UpdateBasketballGameFragmentArgs
import com.aryandadhich.urja10.ui.games.teamGames.basketball.UpdateBasketballGameFragmentDirections
import com.aryandadhich.urja10.ui.games.teamGames.basketball.UpdateBasketballGameViewModel
import com.aryandadhich.urja10.ui.games.teamGames.basketball.UpdateBasketballGameViewModelFactory


class UpdateFootballGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateFootballGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateFootballGameViewModel
    private lateinit var viewModelFactory: UpdateFootballGameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateFootballGameBinding.inflate(inflater, container, false)

        val eventId = UpdateFootballGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateFootballGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateFootballGameFragmentArgs.fromBundle(arguments!!).teamB
        viewModelFactory = UpdateFootballGameViewModelFactory(eventId, teamAName, teamBName)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateFootballGameViewModel::class.java)

        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToFootballGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateFootballUpdateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }

        binding.deleteGame.setOnClickListener{
            onDeleteBtnClicked()
        }

        return binding.root
    }

    private fun onDeleteBtnClicked() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete game")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteGame();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

    private fun navigateToFootballGamesFragment() {
        findNavController().navigate(UpdateFootballGameFragmentDirections.actionUpdateFootballGameFragmentToFootballFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateFootballTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateFootballTeamBScoreEditText.text.toString().toInt()
        viewModel.updateFootballGameScores();
    }

    private fun updateData() {
        binding.fragUpdateFootballTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateFootballTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}