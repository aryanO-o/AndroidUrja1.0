package com.aryandadhich.urja10.ui.home

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentHomeBinding
import com.aryandadhich.urja10.utils.stringUtils
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(context?.let { checkForInternet(it) } == false){
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNoInternetFragment())

        }else{
            binding.viewModel = viewModel;
            binding.setLifecycleOwner(this)

            binding.noticeFragmentRecyclerView.adapter = NoticeAdapter(updateNoticeListener = UpdateNoticeListener{
                    noticeId->
                findNavController().navigate(HomeFragmentDirections.actionNavHomeToEditNoticeFragment(noticeId));
            })

            viewModel.loadData.observe(viewLifecycleOwner, Observer {
                if(it)
                    removeLoadingScreen()
            })

            binding.addNoticeFab.setOnClickListener{
                navigateToAddNotice()
            }
        }

        if(stringUtils.role == ""){
            binding.addNoticeFab.visibility = View.GONE
        }

        val pullToRefresh = binding.refreshFragment
        pullToRefresh.setOnRefreshListener {
            refreshData()
            startLoadingScreen()
            pullToRefresh.isRefreshing = false
        }

        return binding.root
    }

    private fun refreshData() {
        viewModel.getNoticesData()
    }

    private fun navigateToAddNotice() {
        findNavController().navigate(HomeFragmentDirections.actionNavHomeToAddNoticeFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun removeLoadingScreen() {
        binding.loadingPanel.visibility = View.GONE
    }

    private fun startLoadingScreen() {
        binding.loadingPanel.visibility = View.VISIBLE
    }

    private fun checkForInternet(context: Context): Boolean {

        val ConnectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = ConnectionManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected == true) {
            return true
        } else {
            Toast.makeText(context, "Network Not Available", Toast.LENGTH_LONG).show()
            return false
        }
    }

}