package com.aryandadhich.urja10.ui.home

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
import com.aryandadhich.urja10.databinding.FragmentAddNoticeBinding

class AddNoticeFragment : Fragment() {
    private lateinit var _binding: FragmentAddNoticeBinding
    val binding get()  = _binding

    private lateinit var viewModel: AddNoticeViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNoticeBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AddNoticeViewModel::class.java);
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this);

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it){
                navigateToHome()
            }
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            message->
                toastMessage(message)
        })

        binding.cancleBtn.setOnClickListener {
            navigateToHome();
        }

        binding.addBtn.setOnClickListener {
            addBtnClicked()
        }

        return binding.root;
    }

    private fun addBtnClicked() {
        viewModel.heading = binding.headingEditText.text.toString();
        viewModel.notice = binding.noticeEditText.text.toString();

        viewModel.addNotice();
    }

    private fun toastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private fun navigateToHome() {
        findNavController().navigate(AddNoticeFragmentDirections.actionAddNoticeFragmentToNavHome())
    }

}