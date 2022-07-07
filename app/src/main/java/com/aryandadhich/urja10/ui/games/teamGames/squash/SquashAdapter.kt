package com.aryandadhich.urja10.ui.games.teamGames.squash

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.SquashListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class SquashAdapter(val squashTeamListener: SquashTeamListener, val updateSquashGameListener: UpdateSquashGameListener): ListAdapter<SquashGame, SquashAdapter.SquashTeamViewHolder>(DiffCallBack) {
    class SquashTeamViewHolder(private var binding: SquashListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(squashGame: SquashGame, squashTeamListener: SquashTeamListener, updateSquashGameListener: UpdateSquashGameListener){
            binding.squash = squashGame
            binding.squashTeamListener = squashTeamListener
            binding.updateSquashListener = updateSquashGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<SquashGame>() {
        override fun areItemsTheSame(oldItem: SquashGame, newItem: SquashGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SquashGame, newItem: SquashGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SquashAdapter.SquashTeamViewHolder {
        return SquashAdapter.SquashTeamViewHolder  (
            SquashListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: SquashAdapter.SquashTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, squashTeamListener, updateSquashGameListener)
    }
}

class SquashTeamListener(val clickListner: (footGameId: String) -> Unit){
    fun onClick(squashGame: SquashGame) = clickListner(squashGame.id)
}

class UpdateSquashGameListener(val clickListner: (footGame: SquashGame) -> Unit){
    fun onUpdateClicked(squashGame: SquashGame) = clickListner(squashGame);
}