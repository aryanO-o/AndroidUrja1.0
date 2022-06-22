package com.aryandadhich.urja10.ui.splashScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentSplashScreenBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.isLoggedIn


class SplashScreenFragment : Fragment() {

    private lateinit var _binding: FragmentSplashScreenBinding
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SplashScreenFragment", "onCreateViewCalled")

        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
            if (isLoggedIn == false && context?.let { checkForInternet(it) } == true) {
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToNavHome())
            } else if (context?.let { checkForInternet(it) } == true) {
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToFragSignIn())
            } else {
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToNoInternetFragment())
            }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("SplashScreenFragment", "onDestroyViewCalled")
        (activity as AppCompatActivity).supportActionBar?.show()
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