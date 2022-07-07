package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

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
import com.aryandadhich.urja10.databinding.FragmentAddTableTennisGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner


class AddTableTennisGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddTableTennisGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddTableTennisGameFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTableTennisGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddTableTennisGameFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.tableTennisAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.tableTennisAddGameTeamANameText.setText(team.houseName);
            binding.tableTennisAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.tableTennisAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.tableTennisAddGameTeamBNameText.setText(team.houseName);
            binding.tableTennisAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToTableTennisGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.tableTennisAddGameAddBtn.setOnClickListener {
            callViewModelAddTennisGame()
        }

        binding.tableTennisAddGameCancleBtn.setOnClickListener {
            goBackToTableTennisGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToTableTennisGamesFragment() {
        findNavController().navigate(AddTableTennisGameFragmentDirections.actionAddTableTennisGameFragmentToTableTennisFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddTennisGame() {
        viewModel.addTableTennisGame();
    }

}