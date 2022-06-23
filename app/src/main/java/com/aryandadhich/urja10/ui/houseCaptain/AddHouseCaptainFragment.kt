package com.aryandadhich.urja10.ui.houseCaptain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddHouseCaptainBinding


class AddHouseCaptainFragment : Fragment() {

    private lateinit var _binding: FragmentAddHouseCaptainBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddHouseCaptainViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddHouseCaptainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(AddHouseCaptainViewModel::class.java)
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                toastStatus();
                navigateToHouseCaptainFrag();
            }
        })

        binding.fragAddHouseCaptainAddBtn.setOnClickListener {
            callViewModelAddBtn()
        }
        return binding.root
    }

    private fun toastStatus(){
        Toast.makeText(context, "${viewModel.message.value}", Toast.LENGTH_SHORT).show();
    }

    private fun navigateToHouseCaptainFrag(){
        findNavController().navigate(AddHouseCaptainFragmentDirections.actionAddHouseCaptainFragmentToHouseCaptainFragment())
    }

    private fun callViewModelAddBtn() {
        viewModel.collegeId = binding.fragAddHouseCaptainCollegeIdEditTxt.text.toString()
        viewModel.name = binding.fragAddHouseCaptainNameEditTxt.text.toString()
        viewModel.year = binding.fragAddHouseCaptainYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragAddHouseCaptainBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragAddHouseCaptainWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragAddHouseCaptainWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragAddHouseCaptainMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragAddHouseCaptainMobileNumberEditTxt.text.toString();
        viewModel.loginId = binding.fragAddHouseCaptainLoginIdEditTxt.text.toString()
        viewModel.password = binding.fragAddHouseCaptainPasswordEditTxt.text.toString()

        viewModel.addHouseCaptainFromAddBtn()
    }

}