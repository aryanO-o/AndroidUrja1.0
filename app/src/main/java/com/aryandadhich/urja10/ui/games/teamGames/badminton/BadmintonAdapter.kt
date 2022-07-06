package com.aryandadhich.urja10.ui.games.teamGames.badminton

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.BadmintonListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class BadmintonAdapter (val badmintonTeamListner: BadmintonTeamListener, val updateBadmintonGameListener: UpdateBadmintonGameListener): ListAdapter<BadmintonGame, BadmintonAdapter.BadmintonTeamViewHolder>(DiffCallBack) {
    class BadmintonTeamViewHolder(private var binding: BadmintonListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(badmintonGame: BadmintonGame, badmintonTeamListner: BadmintonTeamListener, updateBadmintonGameListener: UpdateBadmintonGameListener){
            binding.badminton = badmintonGame
            binding.badmintonTeamListener = badmintonTeamListner
            binding.updateBadmintonListener = updateBadmintonGameListener;
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<BadmintonGame>() {
        override fun areItemsTheSame(oldItem: BadmintonGame, newItem: BadmintonGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BadmintonGame, newItem: BadmintonGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BadmintonAdapter.BadmintonTeamViewHolder {
        return BadmintonAdapter.BadmintonTeamViewHolder (
            BadmintonListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: BadmintonAdapter.BadmintonTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, badmintonTeamListner, updateBadmintonGameListener)
    }
}

class BadmintonTeamListener(val clickListner: (badmintonGameId: String) -> Unit){
    fun onClick(badmintonGame: BadmintonGame) = clickListner(badmintonGame.id)
}

class UpdateBadmintonGameListener(val clickListner: (badmintonGame: BadmintonGame) -> Unit){
    fun onUpdateClicked(badmintonGame: BadmintonGame) = clickListner(badmintonGame);
}