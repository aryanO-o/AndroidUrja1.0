package com.aryandadhich.urja10.ui.games.teamGames.volleyball

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
import com.aryandadhich.urja10.databinding.FragmentAddVolleyballGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner


class AddVolleyballGameFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentAddVolleyballGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddVolleyballGameFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddVolleyballGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddVolleyballGameFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.volleyballAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.volleyballAddGameTeamANameText.setText(team.houseName);
            binding.volleyballAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.volleyballAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.volleyballAddGameTeamBNameText.setText(team.houseName);
            binding.volleyballAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToVolleyballGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.volleyballAddGameAddBtn.setOnClickListener {
            callViewModelAddVolleyballGame()
        }

        binding.volleyballAddGameCancleBtn.setOnClickListener {
            goBackToVolleyballGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToVolleyballGamesFragment() {
        findNavController().navigate(AddVolleyballGameFragmentDirections.actionAddVolleyballGameFragmentToVolleyballFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddVolleyballGame() {
        viewModel.addVolleyballGame();
    }

}