package com.aryandadhich.urja10.ui.games.teamGames.cricket

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
import com.aryandadhich.urja10.databinding.FragmentUpdateCricketGameBinding


class UpdateCricketGameFragment : Fragment() {

    private lateinit var _binding: FragmentUpdateCricketGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateCricketGameViewModel
    private lateinit var viewModelFactory: UpdateCricketGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateCricketGameBinding.inflate(inflater, container, false)

        val eventId = UpdateCricketGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateCricketGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateCricketGameFragmentArgs.fromBundle(arguments!!).teamB

        viewModelFactory = UpdateCricketGameViewModelFactory(eventId, teamAName, teamBName);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateCricketGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToCricketGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateCricketUpdateBtn.setOnClickListener {
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

    private fun navigateToCricketGamesFragment() {
        findNavController().navigate(UpdateCricketGameFragmentDirections.actionUpdateCricketGameFragmentToCricketFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateCricketTeamAScoreEditText.text.toString()
        viewModel.editTextTeamBScore = binding.fragUpdateCricketTeamBScoreEditText.text.toString()
        viewModel.updateCricketGameScores();
    }

    private fun updateData() {
        binding.fragUpdateCricketTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateCricketTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}