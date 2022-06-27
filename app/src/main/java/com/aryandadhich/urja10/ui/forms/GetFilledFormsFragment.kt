package com.aryandadhich.urja10.ui.forms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentGetFilledFormsBinding

class GetFilledFormsFragment : Fragment() {
    private lateinit var _binding: FragmentGetFilledFormsBinding
    val binding get() = _binding!!

    private lateinit var viewModel: GetFilledFormsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGetFilledFormsBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(GetFilledFormsViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.filledFormsRecyclerView.adapter = GetFilledFormAdapter();

        val formId = GetFilledFormsFragmentArgs.fromBundle(arguments!!).formId

        viewModel.getFilledFormsData(formId);

        binding.filledFormsRecyclerView.adapter = GetFilledFormAdapter();

        return binding.root
    }

}