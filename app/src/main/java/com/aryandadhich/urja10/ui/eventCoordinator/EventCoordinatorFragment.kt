package com.aryandadhich.urja10.ui.eventCoordinator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentCoordinatorBinding
import com.aryandadhich.urja10.databinding.FragmentEventCoordinatorBinding
import com.aryandadhich.urja10.ui.coordinator.CoordinatorFragmentDirections
import com.aryandadhich.urja10.utils.stringUtils
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


class EventCoordinatorFragment : Fragment() {

    private lateinit var _binding: FragmentEventCoordinatorBinding
    val binding get() = _binding!!

    private lateinit var viewModel: EventCoordinatorViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventCoordinatorBinding.inflate(inflater, container, false);

        viewModel = ViewModelProviders.of(this).get(EventCoordinatorViewModel::class.java);

        binding.viewModel  = viewModel;
        binding.setLifecycleOwner(this);

        binding.eventCoordinatorRecyclerView.adapter = EventCoordinatorAdapter(EventCoordinatorListner {
                loginId -> if(stringUtils.role == "supervisor" || stringUtils.role == "house-captain" || role == "coordinator") {
                    findNavController().navigate(EventCoordinatorFragmentDirections.actionEventCoordinatorFragmentToEventCoordinatorEditFragment(loginId))
                }else{
            Toast.makeText(context, "access denied", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        if(role == "supervisor" || role == "house-captain" || role == "coordinator"){
            binding.addEventCoordinatorFab.visibility = View.VISIBLE;
        }

        binding.addEventCoordinatorFab.setOnClickListener{
            findNavController().navigate(EventCoordinatorFragmentDirections.actionEventCoordinatorFragmentToAddEventCoordinatorFragment())
        }



        return binding.root;
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

}