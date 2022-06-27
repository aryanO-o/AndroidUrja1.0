package com.aryandadhich.urja10.ui.forms

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
import com.aryandadhich.urja10.databinding.FragmentAddFormBinding
import com.aryandadhich.urja10.databinding.FragmentFillFormBinding


class FillFormFragment : Fragment() {
    private lateinit var _binding: FragmentFillFormBinding

    val binding get() = _binding!!

    private lateinit var viewModel: FillFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFillFormBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(FillFormViewModel::class.java);
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        val formId = FillFormFragmentArgs.fromBundle(arguments!!).formId

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                toastStatus();
                navigateToAvailableFormsFragment();
            }
        })

        binding.fragFillFormAddBtn.setOnClickListener {
            updateDataAndApply(formId)
        }

        return binding.root;
    }

    private fun updateDataAndApply(formId: Int) {
        viewModel.collegeId = binding.fragFillFormCollegeIdEditTxt.text.toString()
        viewModel.name = binding.fragFillFormNameEditTxt.text.toString()
        viewModel.year = binding.fragFillFormYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragFillFormBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragFillFormWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragFillFormWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragFillFormMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragFillFormMobileNumberEditTxt.text.toString();

        viewModel.insertIntoParticipantsInfo(formId)
    }

    private fun navigateToAvailableFormsFragment() {
        findNavController().navigate(FillFormFragmentDirections.actionFillFormFragmentToAvailableFormsFragment())
    }

    private fun toastStatus(){
        Toast.makeText(context, "${viewModel.message.value}", Toast.LENGTH_SHORT).show();
    }

}