package com.aryandadhich.urja10.ui.games.teamGames.volleyball

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
import com.aryandadhich.urja10.databinding.FragmentUpdateVolleyballGameBinding


class UpdateVolleyballGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateVolleyballGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateVolleyballGameViewModel
    private lateinit var viewModelFactory: UpdateVolleyballGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateVolleyballGameBinding.inflate(inflater, container, false)

        val eventId = UpdateVolleyballGameFragmentArgs.fromBundle(arguments!!).eventId
        val teamAName = UpdateVolleyballGameFragmentArgs.fromBundle(arguments!!).teamA
        val teamBName = UpdateVolleyballGameFragmentArgs.fromBundle(arguments!!).teamB

        viewModelFactory = UpdateVolleyballGameViewModelFactory(eventId, teamAName, teamBName);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateVolleyballGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToVolleyballGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.fragUpdateVolleyballUpdateBtn.setOnClickListener {
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

    private fun navigateToVolleyballGamesFragment() {
        findNavController().navigate(UpdateVolleyballGameFragmentDirections.actionUpdateVolleyballGameFragmentToVolleyballFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.editTextTeamAScore = binding.fragUpdateVolleyballTeamAScoreEditText.text.toString().toInt()
        viewModel.editTextTeamBScore = binding.fragUpdateVolleyballTeamBScoreEditText.text.toString().toInt()
        viewModel.updateVolleyballGameScores();
    }

    private fun updateData() {
        binding.fragUpdateVolleyballTeamAScoreEditText.setText(viewModel.game.value!!.teamAScore)
        binding.fragUpdateVolleyballTeamBScoreEditText.setText(viewModel.game.value!!.teamBScore)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}