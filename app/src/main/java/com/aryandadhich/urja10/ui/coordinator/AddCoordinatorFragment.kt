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
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddCoordinatorBinding


class AddCoordinatorFragment : Fragment() {
    private lateinit var _binding: FragmentAddCoordinatorBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddCoordinatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCoordinatorBinding.inflate(inflater, container, false)

        viewModel = ViewModelProviders.of(this).get(AddCoordinatorViewModel::class.java);

        binding.viewModel  = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                toastStatus();
                navigateToCoordinatorFragment();
            }
        })

        binding.fragAddCoordinatorAddBtn.setOnClickListener {
            callViewModelAddBtn()
        }

        return binding.root;
    }


    private fun toastStatus(){
        Toast.makeText(context, "${viewModel.message.value}", Toast.LENGTH_SHORT).show();
    }

    private fun navigateToCoordinatorFragment(){
        findNavController().navigate(AddCoordinatorFragmentDirections.actionAddCoordinatorFragmentToCoordinatorFragment())
    }

    private fun callViewModelAddBtn() {
        viewModel.collegeId = binding.fragAddCoordinatorCollegeIdEditTxt.text.toString()
        viewModel.name = binding.fragAddCoordinatorNameEditTxt.text.toString()
        viewModel.year = binding.fragAddCoordinatorYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragAddCoordinatorBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragAddCoordinatorWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragAddCoordinatorWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragAddCoordinatorMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragAddCoordinatorMobileNumberEditTxt.text.toString();
        viewModel.loginId = binding.fragAddCoordinatorLoginIdEditTxt.text.toString()
        viewModel.password = binding.fragAddCoordinatorPasswordEditTxt.text.toString()

        viewModel.addCoordinatorFromAddBtn()
    }

}