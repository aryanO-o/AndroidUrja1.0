package com.aryandadhich.urja10.ui.coordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentCoordinatorBinding
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptainFragmentDirections
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptainListner
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


class CoordinatorFragment : Fragment() {
    private lateinit var _binding: FragmentCoordinatorBinding

    val binding get() = _binding!!

    private lateinit var viewModel: CoordinatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false);
        binding.coordinatorRecyclerView.adapter = CoordinatorAdapter(CoordinatorListner {
                loginId -> if(role == "supervisor" || role == "house-captain") {
                    findNavController().navigate(CoordinatorFragmentDirections.actionCoordinatorFragmentToCoordinatorEditFragment(loginId))
                }else{
            Toast.makeText(context, "access denied", Toast.LENGTH_SHORT).show()
        }
        })

        viewModel = ViewModelProviders.of(this).get(CoordinatorViewModel::class.java)
        binding.viewModel = viewModel;

        binding.setLifecycleOwner(this);

        if(role == "supervisor" || role == "house-captain"){
            binding.addCoordinatorFab.visibility = View.VISIBLE;
        }

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        binding.addCoordinatorFab.setOnClickListener{
            it.findNavController().navigate(CoordinatorFragmentDirections.actionCoordinatorFragmentToAddCoordinatorFragment())
        }

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData()
            startLoadingScreen()
            pullToRefresh.isRefreshing = false
        }
        return binding.root;
    }

    private fun refreshData() {
        viewModel.getCoordinatorData()
    }


    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }


    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }

}