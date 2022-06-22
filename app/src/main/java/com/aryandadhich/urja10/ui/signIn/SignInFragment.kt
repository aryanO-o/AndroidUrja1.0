package com.aryandadhich.urja10.ui.signIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aryandadhich.urja10.MainActivity
import com.aryandadhich.urja10.databinding.FragmentSignInBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.JWT
import com.aryandadhich.urja10.utils.stringUtils.Companion.role


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    val binding get() = _binding!!

    private lateinit var viewModel: SignInViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.hide()
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(SignInViewModel::class.java)

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this)

        viewModel.login.observe(viewLifecycleOwner, Observer { login->
            if(login){
                showLoginToast();
                viewModel.onLoginComplete()
            }
        }

        )

        binding.fragSignInLoginBtn.setOnClickListener {
            viewModel.loginId = binding.fragSignInLoginIdEditTxt.text.toString();
            viewModel.password = binding.fragSignInPassEditTxt.text.toString()
            viewModel.login()
        }

        (activity as MainActivity).lockDrawer()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).unlockDrawer()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    fun showLoginToast(){
        Toast.makeText(context, "${viewModel.loginMessage}", Toast.LENGTH_SHORT).show()
        Log.i("SignInFragment", "$JWT")
        Log.i("SignInFragment", "$role")
    }

    fun navigateToHome(){
    }

}