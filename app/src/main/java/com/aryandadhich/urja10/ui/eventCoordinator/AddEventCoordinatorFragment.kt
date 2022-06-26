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
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddEventCoordinatorBinding


class AddEventCoordinatorFragment : Fragment() {

    private lateinit var _binding: FragmentAddEventCoordinatorBinding;
    val binding get() = _binding!!

    private lateinit var viewModel: AddEventCoordinatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEventCoordinatorBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddEventCoordinatorViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                toastStatus();
                navigateToEventCoordinatorFragment();
            }
        })

        binding.fragAddEventCoordinatorAddBtn.setOnClickListener {
            callViewModelAddBtn()
        }

        return binding.root;
    }

    private fun navigateToEventCoordinatorFragment() {
        findNavController().navigate(AddEventCoordinatorFragmentDirections.actionAddEventCoordinatorFragmentToEventCoordinatorFragment())
    }

    private fun toastStatus(){
        Toast.makeText(context, "${viewModel.message.value}", Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddBtn() {
        viewModel.collegeId = binding.fragAddEventCoordinatorCollegeIdEditTxt.text.toString()
        viewModel.name = binding.fragAddEventCoordinatorNameEditTxt.text.toString()
        viewModel.year = binding.fragAddEventCoordinatorYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragAddEventCoordinatorBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragAddEventCoordinatorWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragAddEventCoordinatorWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragAddEventCoordinatorMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragAddEventCoordinatorMobileNumberEditTxt.text.toString();
        viewModel.loginId = binding.fragAddEventCoordinatorLoginIdEditTxt.text.toString()
        viewModel.password = binding.fragAddEventCoordinatorPasswordEditTxt.text.toString()

        viewModel.addEventCoordinatorFromAddBtn()
    }

}