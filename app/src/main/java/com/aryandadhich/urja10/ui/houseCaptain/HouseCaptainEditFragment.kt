package com.aryandadhich.urja10.ui.houseCaptain

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
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentHouseCaptainEditBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log


class HouseCaptainEditFragment : Fragment() {

    private lateinit var _binding: FragmentHouseCaptainEditBinding
    val binding get() = _binding!!

    private lateinit var viewModel: HouseCaptainEditViewModel
    private lateinit var viewModelFactory: HouseCaptainEditViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHouseCaptainEditBinding.inflate(inflater, container, false)
        val loginId = HouseCaptainEditFragmentArgs.fromBundle(arguments!!).loginId
        viewModelFactory = HouseCaptainEditViewModelFactory(loginId = loginId);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HouseCaptainEditViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        binding.fragEditHouseCaptainUpdateBtn.setOnClickListener {
            callViewModelUpdateBtn()
        }

        binding.fragHouseCaptainEditDeleteFab.setOnClickListener{
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
                navigateToHouseCaptainFragment()
                viewModel.onNavigationComplete()
            }
        })
        return binding.root
    }

    private fun navigateToHouseCaptainFragment() {
        findNavController().navigate(HouseCaptainEditFragmentDirections.actionHouseCaptainEditFragmentToHouseCaptainFragment())
    }

    private fun updateInfo(){
        binding.fragEditHouseCaptainNameEditTxt.setText(viewModel.name.toString())
        binding.fragEditHouseCaptainCollegeIdEditTxt.setText(viewModel.collegeId.toString())
        binding.fragEditHouseCaptainYearEditTxt.setText(viewModel.year.toString())
        binding.fragEditHouseCaptainBranchEditTxt.setText(viewModel.branch.toString())
        binding.fragEditHouseCaptainWhatsappCountryCodeEditTxt.setText(viewModel.whatsappCountryCode.toString())
        binding.fragEditHouseCaptainWhatsappNumberEditTxt.setText(viewModel.whatsappNumber.toString())
        binding.fragEditHouseCaptainMobileNumberCountryCodeEditTxt.setText(viewModel.mobileNumberCountryCode.toString())
        binding.fragEditHouseCaptainMobileNumberEditTxt.setText(viewModel.mobileNumber.toString())
    }

    private fun callViewModelUpdateBtn() {
        viewModel.name = binding.fragEditHouseCaptainNameEditTxt.text.toString()
        viewModel.year = binding.fragEditHouseCaptainYearEditTxt.text.toString().toInt()
        viewModel.branch = binding.fragEditHouseCaptainBranchEditTxt.text.toString()
        viewModel.whatsappCountryCode = binding.fragEditHouseCaptainWhatsappCountryCodeEditTxt.text.toString().toInt()
        viewModel.whatsappNumber = binding.fragEditHouseCaptainWhatsappNumberEditTxt.text.toString()
        viewModel.mobileNumberCountryCode = binding.fragEditHouseCaptainMobileNumberCountryCodeEditTxt.text.toString().toInt()
        viewModel.mobileNumber = binding.fragEditHouseCaptainMobileNumberEditTxt.text.toString();
        viewModel.collegeId = binding.fragEditHouseCaptainCollegeIdEditTxt.text.toString()
        viewModel.loginId = binding.fragEditHouseCaptainLoginIdEditTxt.text.toString()

        viewModel.updateHouseCaptainInfo()
    }

    private fun onDeleteButtonClicked(){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to remove ${binding.fragEditHouseCaptainLoginIdEditTxt.text}")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteHouseCaptain();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

}