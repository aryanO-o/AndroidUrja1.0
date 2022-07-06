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
import com.aryandadhich.urja10.databinding.FragmentAddBadmintonGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.basketball.AddBasketballGameFragmentDirections
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner

class AddBadmintonGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddBadmintonGameBinding
    val binding get() = _binding!!
    
    private lateinit var viewModel: AddBadmintonGameViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBadmintonGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddBadmintonGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.badmintonAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.badmintonAddGameTeamANameText.setText(team.houseName);
            binding.badmintonAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.badmintonAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.badmintonAddGameTeamBNameText.setText(team.houseName);
            binding.badmintonAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToBadmintonGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.badmintonAddGameAddBtn.setOnClickListener {
            callViewModelAddBadmintonGame()
        }

        binding.badmintonAddGameCancleBtn.setOnClickListener {
            goBackToBadmintonGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToBadmintonGamesFragment() {
        findNavController().navigate(AddBadmintonGameFragmentDirections.actionAddBadmintonGameFragmentToBadmintonFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddBadmintonGame() {
        viewModel.addBadmintonGame();
    }

}