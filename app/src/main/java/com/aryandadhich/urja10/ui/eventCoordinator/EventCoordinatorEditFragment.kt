package com.aryandadhich.urja10.ui.eventCoordinator

import android.app.AlertDialog
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
import com.aryandadhich.urja10.databinding.FragmentEventCoordinatorEditBinding

class EventCoordinatorEditFragment : Fragment() {
    private lateinit var _binding: FragmentEventCoordinatorEditBinding
    val binding get() = _binding!!

    private lateinit var viewModel: EventCoordinatorEditViewModel;
    private lateinit var viewModelFactory: EventCoordinatorEditViewModelFactory;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventCoordinatorEditBinding.inflate(inflater, container, false);

        val loginId = EventCoordinatorEditFragmentArgs.fromBundle(arguments!!).loginId

        viewModelFactory = EventCoordinatorEditViewModelFactory(loginId);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EventCoordinatorEditViewModel::class.java);

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        binding.fragEditEventCoordinatorUpdateBtn.setOnClickListener {
            callViewModelUpdateBtn()
        }

        binding.fragEventCoordinatorEditDeleteFab.setOnClickListener{
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
                navigateToEventCoordinatorFragment()
                viewModel.onNavigationComplete()
            }
        })



        return binding.root;
    }

    private fun navigateToEventCoordinatorFragment() {
        findNavController().navigate(EventCoordinatorEditFragmentDirections.actionEventCoordinatorEditFragmentToEventCoordinatorFragment())
    }

    private fun updateInfo(){
        binding.fragEditEventCoordinatorNameEditTxt.setText(viewModel.name.toString())
        binding.fragEditEventCoordinatorCollegeIdEditTxt.setText(viewModel.collegeId.toString())
        binding.fragEditEventCoordinatorYearEditTxt.setText(viewModel.year.toString())
        binding.fragEditEventCoordinatorBranchEditTxt.setText(viewModel.branch.toString())
        binding.fragEditEventCoordinatorWhatsappCountryCodeEditTxt.setText(viewModel.whatsappCountryCode.toString())
        binding.fragEditEventCoordinatorWhatsappNumberEditTxt.setText(viewModel.whatsappNumber.toString())
        binding.fragEditEventCoordinatorMobileNumberCountryCodeEditTxt.setText(viewModel.mobileNumberCountryCode.toString())
        binding.fragEditEventCoordinatorMobileNumberEditTxt.setText(viewModel.mobileNumber.toString())
    }

    private fun onDeleteButtonClicked() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to remove ${binding.fragEditEventCoordinatorLoginIdEditTxt.text}")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteEventCoordinator();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

    private fun callViewModelUpdateBtn() {
        viewModel.name = binding.fragEditEventCoordinatorNameEditTxt.text.toString()
        viewModel.year = binding.fragEditEventCoordinatorYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragEditEventCoordinatorBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragEditEventCoordinatorWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragEditEventCoordinatorWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragEditEventCoordinatorMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragEditEventCoordinatorMobileNumberEditTxt.text.toString();
        viewModel.collegeId = binding.fragEditEventCoordinatorCollegeIdEditTxt.text.toString()
        viewModel.loginId = binding.fragEditEventCoordinatorLoginIdEditTxt.text.toString()

        viewModel.updateEventCoordinatorInfo()
    }

}