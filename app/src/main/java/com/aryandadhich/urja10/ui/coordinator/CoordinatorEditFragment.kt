package com.aryandadhich.urja10.ui.coordinator

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.aryandadhich.urja10.databinding.FragmentCoordinatorEditBinding


class CoordinatorEditFragment : Fragment() {
    private lateinit var _binding: FragmentCoordinatorEditBinding
    val binding get() = _binding!!

    private lateinit var viewModel: CoordinatorEditViewModel
    private lateinit var viewModelFactory: CoordinatorEditViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoordinatorEditBinding.inflate(inflater, container, false);
        val loginId = CoordinatorEditFragmentArgs.fromBundle(arguments!!).loginId;
        viewModelFactory = CoordinatorEditViewModelFactory(loginId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CoordinatorEditViewModel::class.java);

        binding.viewModel =viewModel;
        binding.setLifecycleOwner(this)

        binding.fragEditCoordinatorUpdateBtn.setOnClickListener {
            callViewModelUpdateBtn()
        }

        binding.fragCoordinatorEditDeleteFab.setOnClickListener{
            onDeleteButtonClicked();
        }

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                updateInfo();
            }
        })

        viewModel.message.observe(viewLifecycleOwner, Observer { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.navigate.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate) {
                navigateToCoordinatorFragment()
                viewModel.onNavigationComplete()
            }
        })


        return binding.root;
    }

    private fun navigateToCoordinatorFragment() {
        findNavController().navigate(CoordinatorEditFragmentDirections.actionCoordinatorEditFragmentToCoordinatorFragment())
    }

    private fun updateInfo(){
        binding.fragEditCoordinatorNameEditTxt.setText(viewModel.name.toString())
        binding.fragEditCoordinatorCollegeIdEditTxt.setText(viewModel.collegeId.toString())
        binding.fragEditCoordinatorYearEditTxt.setText(viewModel.year.toString())
        binding.fragEditCoordinatorBranchEditTxt.setText(viewModel.branch.toString())
        binding.fragEditCoordinatorWhatsappCountryCodeEditTxt.setText(viewModel.whatsappCountryCode.toString())
        binding.fragEditCoordinatorWhatsappNumberEditTxt.setText(viewModel.whatsappNumber.toString())
        binding.fragEditCoordinatorMobileNumberCountryCodeEditTxt.setText(viewModel.mobileNumberCountryCode.toString())
        binding.fragEditCoordinatorMobileNumberEditTxt.setText(viewModel.mobileNumber.toString())
    }

    private fun onDeleteButtonClicked() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to remove ${binding.fragEditCoordinatorLoginIdEditTxt.text}")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteCoordinator();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

    private fun callViewModelUpdateBtn() {
        viewModel.name = binding.fragEditCoordinatorNameEditTxt.text.toString()
        viewModel.year = binding.fragEditCoordinatorYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragEditCoordinatorBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragEditCoordinatorWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragEditCoordinatorWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragEditCoordinatorMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragEditCoordinatorMobileNumberEditTxt.text.toString();
        viewModel.collegeId = binding.fragEditCoordinatorCollegeIdEditTxt.text.toString()
        viewModel.loginId = binding.fragEditCoordinatorLoginIdEditTxt.text.toString()

        viewModel.updateCoordinatorInfo()
    }

}
