package com.aryandadhich.urja10.ui.games.teamGames.teams

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
import com.aryandadhich.urja10.databinding.FragmentTeamBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class TeamFragment : Fragment() {
    private lateinit var _binding: FragmentTeamBinding;
    val binding get() = _binding!!

    private lateinit var viewModel: TeamFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(TeamFragmentViewModel::class.java)

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.fragTeamRecyclerView.adapter = TeamAdapter(TeamListner {
            teamId ->
            findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToPlayerFragment(teamId))

        }, DeleteTeamListner {
            teamId ->
            callViewModelDeleteTeam(teamId);
        })

        if(role == "") binding.addTeamFab.visibility = View.GONE;

        binding.addTeamFab.setOnClickListener{
            navigateToAddTeamFragment()
        }

        return binding.root;
    }

    private fun callViewModelDeleteTeam(teamId: String) {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete player?")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteTeam(teamId)
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

    private fun navigateToAddTeamFragment() {
        findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToAddTeamFragment())
    }

}