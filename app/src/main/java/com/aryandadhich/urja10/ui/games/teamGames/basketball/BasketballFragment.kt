package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
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

        }, DeleteBasketballTeamListner {

        })

        if(role == "")
            binding.addBasketballGameFab.visibility = View.GONE;

        return binding.root
    }

}