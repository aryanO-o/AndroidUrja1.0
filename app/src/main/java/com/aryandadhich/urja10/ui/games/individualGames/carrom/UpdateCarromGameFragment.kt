package com.aryandadhich.urja10.ui.games.individualGames.carrom

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
import com.aryandadhich.urja10.databinding.FragmentUpdateCarromGameBinding


class UpdateCarromGameFragment : Fragment() {
    private lateinit var _binding: FragmentUpdateCarromGameBinding
    val binding get() = _binding!!

    private lateinit var viewModel: UpdateCarromGameViewModel
    private lateinit var viewModelFactory: UpdateCarromGameViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateCarromGameBinding.inflate(inflater, container, false)

        val eventId = UpdateCarromGameFragmentArgs.fromBundle(arguments!!).eventId

        viewModelFactory = UpdateCarromGameViewModelFactory(eventId);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpdateCarromGameViewModel::class.java)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.message.observe(viewLifecycleOwner, Observer {
            toastMessage(it.toString())
            updateData()
        })
        viewModel.navigation.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToCarromGamesFragment()
                viewModel.doneNavigating()
            }
        })

        binding.carromUpdateGameBtn.setOnClickListener {
            onUpdateBtnClicked()
        }

        binding.deleteGame.setOnClickListener{
            onDeleteBtnClicked()
        }
        return binding.root
    }

    private fun onDeleteBtnClicked() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete game")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deleteGame();
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

    private fun navigateToCarromGamesFragment() {
        findNavController().navigate(UpdateCarromGameFragmentDirections.actionUpdateCarromGameFragmentToCarromFragment());
    }

    private fun onUpdateBtnClicked() {
        viewModel.playerA = binding.fragAddCarromEnterPlayerAEditText.text.toString()
        viewModel.playerB = binding.fragAddCarromEnterPlayerBEditText.text.toString()
        viewModel.winner = binding.fragAddCarromEnterWinnerEditText.text.toString();
        viewModel.updateCarromGameScores();
    }

    private fun updateData() {
        binding.fragAddCarromEnterPlayerAEditText.setText(viewModel.game.value!!.playerA)
        binding.fragAddCarromEnterPlayerBEditText.setText(viewModel.game.value!!.playerB)
        binding.fragAddCarromEnterWinnerEditText.setText(viewModel.game.value!!.winner)
    }

    private fun toastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}