package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
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
        }, DeleteBasketballTeamListner {

        })

        if(role == "")
            binding.addBasketballGameFab.visibility = View.GONE;

        binding.addBasketballGameFab.setOnClickListener{
            navigateToAddBasketballGame()
        }

        return binding.root
    }

    private fun navigateToGameInfoFragment(eventId: String) {
        findNavController().navigate(BasketballFragmentDirections.actionBasketballFragmentToGameInfoFragment(eventId))
    }

    private fun navigateToAddBasketballGame() {
        findNavController().navigate(BasketballFragmentDirections.actionBasketballFragmentToAddBasketballGameFragment())
    }

}