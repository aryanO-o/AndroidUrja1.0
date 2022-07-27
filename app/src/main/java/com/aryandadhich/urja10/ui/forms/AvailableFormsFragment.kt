package com.aryandadhich.urja10.ui.forms

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentAvailableFormsBinding
import com.aryandadhich.urja10.ui.coordinator.CoordinatorFragmentDirections
import com.aryandadhich.urja10.utils.stringUtils
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


class AvailableFormsFragment : Fragment() {
    private lateinit var _binding: FragmentAvailableFormsBinding
    val binding get() = _binding!!

    private lateinit var viewModel: AvailableFormsViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAvailableFormsBinding.inflate(inflater, container, false);
        viewModel = ViewModelProviders.of(this).get(AvailableFormsViewModel::class.java);
        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        binding.availableFormsRecyclerView.adapter = AvailableFormsAdapter(AvailableFormsApplyBtnListener {
          formId->
              findNavController().navigate(AvailableFormsFragmentDirections.actionAvailableFormsFragmentToFillFormFragment(formId))
        }, AvailableFormsEditBtnListner {
            form ->
                viewModel.toggleByViewModel(form);
        }, AvailableFormsDeleteBtnListner {
            form->
                onDeleteButtonClicked(form)
        }, AvailableFormToFilledFormListner {
            formId->
            findNavController().navigate(AvailableFormsFragmentDirections.actionAvailableFormsFragmentToGetFilledFormsFragment(formId))
        })

        viewModel.loadData.observe(viewLifecycleOwner, Observer {
            if(it)
                removeLoadingScreen()
        })

        if(stringUtils.role == "supervisor" || stringUtils.role == "house-captain" || stringUtils.role == "coordinator"){
            binding.addFormsFab.visibility = View.VISIBLE;
        }

        binding.addFormsFab.setOnClickListener{
            dialog.show( parentFragmentManager, "Create Form")
        }

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData() // your code
            pullToRefresh.isRefreshing = false
        }

        return binding.root;
    }

    private fun refreshData() {
        viewModel.getFormsData();
    }

    private fun onDeleteButtonClicked(form: Form) {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete form?")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteFormDeleteBtn(form);
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }
    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    companion object{
        val dialog = AddFormFragment()
        fun goBackToFormFragment(){
            dialog.dismiss()
        }
    }

}