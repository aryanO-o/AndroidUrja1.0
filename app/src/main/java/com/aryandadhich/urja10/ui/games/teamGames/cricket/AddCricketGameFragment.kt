package com.aryandadhich.urja10.ui.games.teamGames.cricket

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
import com.aryandadhich.urja10.databinding.FragmentAddCricketGameBinding
import com.aryandadhich.urja10.ui.games.teamGames.teams.DeleteTeamListner
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamAdapter
import com.aryandadhich.urja10.ui.games.teamGames.teams.TeamListner


class AddCricketGameFragment : Fragment() {
    private lateinit var _binding: com.aryandadhich.urja10.databinding.FragmentAddCricketGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddCricketGameFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCricketGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddCricketGameFragmentViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.cricketAddGameTeamARecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamAId = team.teamId;
            binding.cricketAddGameTeamANameText.setText(team.houseName);
            binding.cricketAddGameTeamANameText.setTextSize(24F)

        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        binding.cricketAddGameTeamBRecyclerView.adapter = TeamAdapter(TeamListner {
                team ->
            viewModel.teamBId = team.teamId;
            binding.cricketAddGameTeamBNameText.setText(team.houseName);
            binding.cricketAddGameTeamBNameText.setTextSize(24F)
        }, DeleteTeamListner {
            toastMessage("go to teams fragment to delete team")
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(viewModel.message.value!!)
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
                navigation ->
            if(navigation){
                goBackToCricketGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.cricketAddGameAddBtn.setOnClickListener {
            callViewModelAddCricketGame()
        }

        binding.cricketAddGameCancleBtn.setOnClickListener {
            goBackToCricketGamesFragment()
        }

        return binding.root;
    }

    private fun goBackToCricketGamesFragment() {
        findNavController().navigate(AddCricketGameFragmentDirections.actionAddCricketGameFragmentToCricketFragment())
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddCricketGame() {
        viewModel.addCricketGame();
    }

}