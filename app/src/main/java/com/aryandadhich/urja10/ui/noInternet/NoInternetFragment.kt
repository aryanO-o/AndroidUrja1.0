package com.aryandadhich.urja10.ui.noInternet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.aryandadhich.urja10.MainActivity
import com.aryandadhich.urja10.R
import com.aryandadhich.urja10.databinding.FragmentNoInternetBinding


class NoInternetFragment : Fragment() {

    private var _binding: FragmentNoInternetBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNoInternetBinding.inflate(inflater, container, false)

       return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

}