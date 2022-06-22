package com.aryandadhich.urja10.ui.houseCaptain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentHouseCaptainBinding


class HouseCaptainFragment : Fragment() {

    private lateinit var _binding: FragmentHouseCaptainBinding
    val binding get() = _binding!!

    private lateinit var viewModel: HouseCaptainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHouseCaptainBinding.inflate(inflater, container, false)
        binding.houseCaptainRecyclerView.adapter = HouseCaptainAdapter()
        viewModel = ViewModelProviders.of(this).get(HouseCaptainViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }


}