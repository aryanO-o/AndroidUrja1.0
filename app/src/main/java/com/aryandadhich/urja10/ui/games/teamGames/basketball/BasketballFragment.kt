package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentBasketballBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class BasketballFragment : Fragment() {
    private lateinit var _binding : FragmentBasketballBinding
    val binding get() = _binding!!

    private lateinit var viewModel: BasketballFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasketballBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(BasketballFragmentViewModel::class.java)
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.basketballFragmentRecyclerView.adapter = BasketballAdapter(BasketballTeamListner {
            navigateToGameInfoFragment(it)
        }, UpdateBasketballGameListener {
            game->
            navigateToUpdateBasketballFragment(game)
        })

        if(role == "")
            binding.addBasketballGameFab.visibility = View.GONE;

        binding.addBasketballGameFab.setOnClickListener{
            navigateToAddBasketballGame()
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it) {
                removeLoadingScreen()
            }
        })

        return binding.root
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    private fun navigateToUpdateBasketballFragment(game: BasketballGame) {
        findNavController().navigate(BasketballFragmentDirections.actionBasketballFragmentToUpdateBasketballGameFragment(game.id, game.teamA, game.teamB))
    }

    private fun navigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(BasketballFragmentDirections.actionBasketballFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddBasketballGame() {
        findNavController().navigate(BasketballFragmentDirections.actionBasketballFragmentToAddBasketballGameFragment())
    }

}