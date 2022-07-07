package com.aryandadhich.urja10.ui.games.teamGames.snooker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.SnookerListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class SnookerAdapter(val snookerTeamListener: SnookerTeamListener, val updateSnookerGameListener: UpdateSnookerGameListener): ListAdapter<SnookerGame, SnookerAdapter.SnookerTeamViewHolder>(DiffCallBack) {
    class SnookerTeamViewHolder(private var binding: SnookerListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(snookerGame: SnookerGame, snookerTeamListener: SnookerTeamListener, updateSnookerGameListener: UpdateSnookerGameListener){
            binding.snooker = snookerGame
            binding.snookerTeamListener = snookerTeamListener
            binding.updateSnookerListener = updateSnookerGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<SnookerGame>() {
        override fun areItemsTheSame(oldItem: SnookerGame, newItem: SnookerGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SnookerGame, newItem: SnookerGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SnookerAdapter.SnookerTeamViewHolder {
        return SnookerAdapter.SnookerTeamViewHolder  (
            SnookerListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: SnookerAdapter.SnookerTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, snookerTeamListener, updateSnookerGameListener)
    }
}

class SnookerTeamListener(val clickListner: (footGameId: String) -> Unit){
    fun onClick(snookerGame: SnookerGame) = clickListner(snookerGame.id)
}

class UpdateSnookerGameListener(val clickListner: (footGame: SnookerGame) -> Unit){
    fun onUpdateClicked(snookerGame: SnookerGame) = clickListner(snookerGame);
}