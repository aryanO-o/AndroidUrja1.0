package com.aryandadhich.urja10.ui.games.common.players

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.aryandadhich.urja10.databinding.FragmentPlayerBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class PlayerFragment : Fragment() {

    private lateinit var _binding: FragmentPlayerBinding
    val binding get() = _binding!!

    private lateinit var viewModel: PlayerFragmentViewModel
    private lateinit var viewModelFactory: PlayerFragmentViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerBinding.inflate(inflater,container, false)

        val teamId = PlayerFragmentArgs.fromBundle(arguments!!).teamId
        viewModelFactory = PlayerFragmentViewModelFactory(teamId)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerFragmentViewModel::class.java);

        binding.viewModel = viewModel;
        binding.setLifecycleOwner(this);

        binding.gamePlayersRecyclerView.adapter = PlayerListAdapter(DeleteGamePlayerListner {
            playerId ->
                onDeleteBtnClicked(teamId,playerId);
        });

        binding.addGamePlayersFab.setOnClickListener{
            navigateToAddGamePlayerFragment(teamId)
        }

        if(role == "supervisor" || role == "house-captain" || role == "coordinator" || role == "event-coordinator")
            binding.addGamePlayersFab.visibility = View.VISIBLE

        return binding.root;
    }

    private fun navigateToAddGamePlayerFragment(teamId: String) {
        findNavController().navigate(PlayerFragmentDirections.actionPlayerFragmentToAddGamePlayersFragment(teamId))
    }

    private fun onDeleteBtnClicked(teamId: String, playerId: String){
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to delete player?")
            .setTitle("Delete")

        builder.apply {
            setPositiveButton("Cancle") { dialog, id ->
                Toast.makeText(context, "cancled is clicked", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("Remove") { dialog, id ->
                viewModel.deletePlayer(teamId, playerId)
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()
    }

}