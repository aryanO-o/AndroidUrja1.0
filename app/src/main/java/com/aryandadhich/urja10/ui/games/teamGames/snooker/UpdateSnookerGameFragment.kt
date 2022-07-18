package com.aryandadhich.urja10.ui.games.teamGames.snooker

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
import com.aryandadhich.urja10.databinding.FragmentUpdateSnookerGameBinding

class UpdateSnookerGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateSnookerGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateSnookerGameViewModel
    private lateinit var viewModelFactory: UpdateSnookerGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateSnookerGameBinding.inflate(inflater, container, false)

        val eventId = UpdateSnookerGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateSnookerGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateSnookerGameFragmentArgs.fromBundle(arguments!!).teamB

        viewModelFactory = UpdateSnookerGameViewModelFactory(eventId, teamAName, teamBName);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateSnookerGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToSnookerGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateSnookerUpdateBtn.setOnClickListener {
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

    private fun navigateToSnookerGamesFragment() {
        findNavController().navigate(UpdateSnookerGameFragmentDirections.actionUpdateSnookerGameFragmentToSnookerFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateSnookerTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateSnookerTeamBScoreEditText.text.toString().toInt()
        viewModel.updateSnookerGameScores();
    }

    private fun updateData() {
        binding.fragUpdateSnookerTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateSnookerTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}