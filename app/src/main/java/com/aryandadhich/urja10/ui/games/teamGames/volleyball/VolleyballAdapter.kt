package com.aryandadhich.urja10.ui.games.teamGames.volleyball

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.VolleyballListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class VolleyballAdapter(val volleyballTeamListener: VolleyballTeamListener, val updateVolleyballGameListener: UpdateVolleyballGameListener): ListAdapter<VolleyballGame, VolleyballAdapter.VolleyballTeamViewHolder>(DiffCallBack) {
    class VolleyballTeamViewHolder(private var binding: VolleyballListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(volleyballGame: VolleyballGame, volleyballTeamListener: VolleyballTeamListener, updateVolleyballGameListener: UpdateVolleyballGameListener){
            binding.volleyball = volleyballGame
            binding.volleyballTeamListener = volleyballTeamListener
            binding.updateVolleyballListener = updateVolleyballGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<VolleyballGame>() {
        override fun areItemsTheSame(oldItem: VolleyballGame, newItem: VolleyballGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: VolleyballGame, newItem: VolleyballGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VolleyballAdapter.VolleyballTeamViewHolder {
        return VolleyballAdapter.VolleyballTeamViewHolder  (
            VolleyballListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: VolleyballAdapter.VolleyballTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, volleyballTeamListener, updateVolleyballGameListener)
    }
}

class VolleyballTeamListener(val clickListner: (footGameId: String) -> Unit){
    fun onClick(volleyballGame: VolleyballGame) = clickListner(volleyballGame.id)
}

class UpdateVolleyballGameListener(val clickListner: (footGame: VolleyballGame) -> Unit){
    fun onUpdateClicked(volleyballGame: VolleyballGame) = clickListner(volleyballGame);
}