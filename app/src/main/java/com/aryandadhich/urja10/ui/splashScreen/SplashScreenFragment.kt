package com.aryandadhich.urja10.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentSplashScreenBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.isLoggedIn


class SplashScreenFragment : Fragment() {

    private lateinit var  _binding: FragmentSplashScreenBinding
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            (activity as AppCompatActivity).supportActionBar?.hide()
            Log.i("SplashScreenFragment", "onCreateCalled")
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("SplashScreenFragment", "onCreateViewCalled")
        try {
            (activity as AppCompatActivity).supportActionBar?.hide()
        } catch (e: NullPointerException) {
            Log.i("SplashScreenFragment", "${e.toString()}")
        }


        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        Handler(Looper.myLooper()!!).postDelayed({
            if(isLoggedIn == true){
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToNavHome())
            }else{
                findNavController().navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToFragSignIn())
            }

        }, 3000)

        Handler(Looper.myLooper()!!).postDelayed({
            binding.splashScreenWelcomeTxt.visibility = View.GONE

        }, 1000)

        Handler(Looper.myLooper()!!).postDelayed({
            binding.splashScreenToTxt.visibility = View.VISIBLE
        }, 1500)

        Handler(Looper.myLooper()!!).postDelayed({
            binding.splashScreenToTxt.visibility = View.GONE
        }, 2000)

        Handler(Looper.myLooper()!!).postDelayed({
            binding.splashUrjaTxt.visibility = View.VISIBLE
        }, 2500)

//        (activity as MainActivity).lockDrawer()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("SplashScreenFragment", "onDestroyViewCalled")
//        (activity as MainActivity).unlockDrawer()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}