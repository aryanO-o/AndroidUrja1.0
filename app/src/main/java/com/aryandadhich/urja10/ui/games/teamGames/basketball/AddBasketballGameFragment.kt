package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddBasketballGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner

class AddBasketballGameFragment : Fragment() {
    private lateinit var _binding: FragmentAddBasketballGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddBasketballGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBasketballGameBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddBasketballGameViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.basketballAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
            team ->
                viewModel.teamAId = team.teamId;
                binding.basketballAddGameTeamANameText.setText(team.houseName);
                binding.basketballAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.basketballAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
            team ->
                viewModel.teamBId = team.teamId;
                binding.basketballAddGameTeamBNameText.setText(team.houseName);
                binding.basketballAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
                toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            navigation ->
            if(navigation){
                goBackToBasketballGamesFragment()
            }
        })

        binding.basketballAddGameAddBtn.setOnClickListener {
            callViewModelAddBasketballGame()
        }

        binding.basketballAddGameCancleBtn.setOnClickListener {
            goBackToBasketballGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToBasketballGamesFragment() {
        findNavController().navigate(AddBasketballGameFragmentDirections.actionAddBasketballGameFragmentToBasketballFragment());
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddBasketballGame() {
        viewModel.addBasketballGame();
    }


}