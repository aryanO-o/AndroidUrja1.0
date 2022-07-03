package com.aryandadhich.urja10.ui.games.teamGames.teams

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddTeamBinding


class AddTeamFragment : Fragment() {
    private lateinit var _binding: FragmentAddTeamBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddTeamFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTeamBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddTeamFragmentViewModel::class.java)
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.fragAddTeamCancleBtn.setOnClickListener {
            navigateToTeamFragment()
        }

        binding.fragAddTeamAddBtn.setOnClickListener {
            callViewModelAddBtn()
        }

        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateToTeamFragment()
                viewModel.onNavigationComplete();
            }
        })

        return binding.root
    }

    private fun callViewModelAddBtn() {
        viewModel.teamName = binding.fragAddTeamTeamNameEditText.text.toString()

        viewModel.addTeam();
    }

    private fun navigateToTeamFragment() {
        findNavController().navigate(AddTeamFragmentDirections.actionAddTeamFragmentToTeamFragment())
    }

}