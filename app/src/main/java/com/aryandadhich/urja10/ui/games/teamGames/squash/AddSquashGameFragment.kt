package com.aryandadhich.urja10.ui.games.teamGames.squash

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
import com.aryandadhich.urja10.databinding.FragmentAddSquashGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner

class AddSquashGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddSquashGameBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: AddSquashGameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSquashGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddSquashGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.squashAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.squashAddGameTeamANameText.setText(team.houseName);
            binding.squashAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.squashAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.squashAddGameTeamBNameText.setText(team.houseName);
            binding.squashAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToSquashGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.squashAddGameAddBtn.setOnClickListener {
            callViewModelAddSquashGame()
        }

        binding.squashAddGameCancleBtn.setOnClickListener {
            goBackToSquashGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToSquashGamesFragment() {
        findNavController().navigate(AddSquashGameFragmentDirections.actionAddSquashGameFragmentToSquashFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddSquashGame() {
        viewModel.addSquashGame();
    }

}