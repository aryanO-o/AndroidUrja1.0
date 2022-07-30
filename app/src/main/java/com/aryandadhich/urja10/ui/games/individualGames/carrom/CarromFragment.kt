package com.aryandadhich.urja10.ui.games.individualGames.carrom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentCarromBinding
import com.aryandadhich.urja10.utils.stringUtils


class CarromFragment : Fragment() {
    private lateinit var _binding: FragmentCarromBinding
    val binding get() = _binding;

    private lateinit var viewModel: CarromFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarromBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(CarromFragmentViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.carromFragmentRecyclerView.adapter = CarromAdapter(CarromTeamListener {
                eventId->
            navigateToGameInfoFragment(eventId)
        }, UpdateCarromGameListener {
                game->
            navigateToUpdateCarromGameFragment(game)
        })

        if(stringUtils.role == "")
            binding.addCarromGameFab.visibility = View.GONE;

        binding.addCarromGameFab.setOnClickListener{
            navigateToAddCarromGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData()
            startLoadingScreen()
            pullToRefresh.isRefreshing = false
        }
        return binding.root
    }

    private fun refreshData() {
        viewModel.fetchCarromGames()
    }

    private fun navigateToUpdateCarromGameFragment(game: CarromGame) {
        findNavController().navigate(CarromFragmentDirections.actionCarromFragmentToUpdateCarromGameFragment(game.id))
    }

    private fun navigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(CarromFragmentDirections.actionCarromFragmentToGameInfoFragment(eventId));
    }

    private fun navigateToAddCarromGame() {
        findNavController().navigate(CarromFragmentDirections.actionCarromFragmentToAddCarromGameFragment())
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }
    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }
}