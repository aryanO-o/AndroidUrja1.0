package com.aryandadhich.urja10.ui.houseCaptain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentHouseCaptainBinding
import com.aryandadhich.urja10.databinding.HouseCaptainListItemBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role
import kotlin.math.log


class HouseCaptainFragment : Fragment() {

    private lateinit var _binding: FragmentHouseCaptainBinding
    val binding get() = _binding!!

    private lateinit var viewModel: HouseCaptainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHouseCaptainBinding.inflate(inflater, container, false)
        binding.houseCaptainRecyclerView.adapter = HouseCaptainAdapter(HouseCaptainListner {
            loginId -> if(role == "supervisor") {
                findNavController().navigate(HouseCaptainFragmentDirections.actionHouseCaptainFragmentToHouseCaptainEditFragment(loginId))
            }else{
                Toast.makeText(context, "access denied", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel = ViewModelProviders.of(this).get(HouseCaptainViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        if(role == "supervisor")
        {
            binding.addHouseCaptainFab.visibility = View.VISIBLE
        }

        binding.addHouseCaptainFab.setOnClickListener{
            it.findNavController().navigate(HouseCaptainFragmentDirections.actionHouseCaptainFragmentToAddHouseCaptainFragment())
        }
        return binding.root
    }


}