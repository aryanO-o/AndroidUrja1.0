package com.aryandadhich.urja10.ui.forms

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAddFormBinding


class AddFormFragment : DialogFragment() {
    private lateinit var _binding: FragmentAddFormBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AddFormViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFormBinding.inflate(inflater,container, false);
        viewModel = ViewModelProviders.of(this).get(AddFormViewModel::class.java)
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            if(status){
                toastStatus();
            }
        })

        binding.fragAddCoordinatorAddBtn.setOnClickListener {
            callViewModelAddBtn();
            AvailableFormsFragment.goBackToFormFragment();
        }

        return binding.root;
    }

    private fun toastStatus(){
        Toast.makeText(context, "${viewModel.message.value}", Toast.LENGTH_SHORT).show();
    }

    private fun callViewModelAddBtn() {
        viewModel.toSelect = binding.fragAddFormToSelectEditText.text.toString();
        viewModel.isActive = binding.isActiveBtn.isChecked

        viewModel.createForm();
    }

}