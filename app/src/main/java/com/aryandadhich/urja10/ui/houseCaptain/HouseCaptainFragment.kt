package com.aryandadhich.urja10.ui.houseCaptain

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.aryandadhich.urja10.databinding.FragmentHouseCaptainBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


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

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        if(role == "supervisor")
        {
            binding.addHouseCaptainFab.visibility = View.VISIBLE
        }

        binding.addHouseCaptainFab.setOnClickListener{
            it.findNavController().navigate(HouseCaptainFragmentDirections.actionHouseCaptainFragmentToAddHouseCaptainFragment())
        }

        val pullToRefresh = binding.refreshHouseCaptains
        pullToRefresh.setColorSchemeColors(R.color.holo_orange_dark)
        pullToRefresh.setOnRefreshListener {
            refreshData()
            startLoadingScreen()
            pullToRefresh.isRefreshing = false
        }



        return binding.root
    }

    private fun refreshData() {
        viewModel.getHouseCaptainData();
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }
}