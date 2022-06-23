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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentHomeBinding
import com.aryandadhich.urja10.utils.stringUtils

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        if (context?.let { checkForInternet(it) } == true && stringUtils.isLoggedIn == false) {
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToFragSignIn())
        } else if(context?.let { checkForInternet(it) } == false) {
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNoInternetFragment())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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