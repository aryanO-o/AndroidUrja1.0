package com.aryandadhich.urja10.ui.home

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
import com.aryandadhich.urja10.databinding.FragmentEditNoticeBinding
import com.aryandadhich.urja10.utils.stringUtils
import kotlinx.android.synthetic.main.fragment_home.*

class EditNoticeFragment : Fragment() {
    private lateinit var _binding: FragmentEditNoticeBinding
    val binding get() = _binding

    private lateinit var viewModel: EditNoticeViewModel
    private lateinit var viewModelFactory: EditNoticeViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoticeBinding.inflate(inflater, container, false);

        val noticeId = EditNoticeFragmentArgs.fromBundle(arguments!!).noticeId;

        viewModelFactory = EditNoticeViewModelFactory(noticeId);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EditNoticeViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.editBtn.setOnClickListener {
            onEditBtnClicked()
        }

        binding.updateBtn.setOnClickListener {
            onUpdateBtnClicked()
        }

        binding.deleteNoticeFab.setOnClickListener{
            onDeleteBtnClicked();
        }

        if(stringUtils.role == "") {
            binding.editBtn.visibility = View.GONE;
            binding.updateBtn.visibility = View.GONE;
            binding.deleteNoticeFab.visibility = View.GONE;
        }


        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it){
                updateEditTextViews()
                viewModel.settingDataComplete();
            }
        })

        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            navigate->
                if(navigate){
                    navigateBackToHomeFragment()
                }
        })


        return binding.root
    }

    private fun updateEditTextViews() {
        binding.headingEditText.setText(viewModel.heading);
        binding.noticeEditText.setText(viewModel.message);
    }

    private fun onDeleteBtnClicked() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete notice")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteNotice();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }


    private fun onUpdateBtnClicked() {
        viewModel.heading = binding.headingEditText.text.toString();
        viewModel.message = binding.noticeEditText.text.toString();

        viewModel.updateNotice();

        binding.noticeEditText.isFocusableInTouchMode = false;
        binding.headingEditText.isFocusableInTouchMode =false;
    }

    private fun onEditBtnClicked() {
        binding.headingEditText.isFocusableInTouchMode = true;
        binding.noticeEditText.isFocusableInTouchMode = true;
    }

    private fun navigateBackToHomeFragment() {
        findNavController().navigate(EditNoticeFragmentDirections.actionEditNoticeFragmentToNavHome())
    }



}