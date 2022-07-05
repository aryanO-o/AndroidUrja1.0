package com.aryandadhich.urja10.ui.games.teamGames.football

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
import com.aryandadhich.urja10.databinding.FragmentAddFootballGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.basketball.AddBasketballGameViewModel
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner

class AddFootballGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddFootballGameBinding
    val binding get() = _binding!!
    private lateinit var viewModel: AddFootballGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFootballGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddFootballGameViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.footballAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.footballAddGameTeamANameText.setText(team.houseName);
            binding.footballAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.footballAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.footballAddGameTeamBNameText.setText(team.houseName);
            binding.footballAddGameTeamBNameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToFootballGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.footballAddGameAddBtn.setOnClickListener {
            callViewModelAddFootballGame()
        }

        binding.footballAddGameCancleBtn.setOnClickListener {
            goBackToFootballGamesFragment()
        }

        return binding.root
    }

    private fun callViewModelAddFootballGame() {
        viewModel.addFootballGame()
    }

    private fun goBackToFootballGamesFragment() {
        findNavController().navigate(AddFootballGameFragmentDirections.actionAddFootballGameFragmentToFootballFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}