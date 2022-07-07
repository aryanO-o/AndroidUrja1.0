package com.aryandadhich.urja10.ui.games.teamGames.snooker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentAddSnookerGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner

class AddSnookerGameFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentAddSnookerGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddSnookerGameFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSnookerGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddSnookerGameFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.snookerAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.snookerAddGameTeamANameText.setText(team.houseName);
            binding.snookerAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.snookerAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.snookerAddGameTeamBNameText.setText(team.houseName);
            binding.snookerAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToSnookerGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.snookerAddGameAddBtn.setOnClickListener {
            callViewModelAddSnookerGame()
        }

        binding.snookerAddGameCancleBtn.setOnClickListener {
            goBackToSnookerGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToSnookerGamesFragment() {
        findNavController().navigate(AddSnookerGameFragmentDirections.actionAddSnookerGameFragmentToSnookerFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddSnookerGame() {
        viewModel.addSnookerGame();
    }

}