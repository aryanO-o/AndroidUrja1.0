package com.aryandadhich.urja10.ui.games.teamGames.football

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.FootballListItemBinding
import com.aryandadhich.urja10.ui.games.common.gameInfo.GetGameInfo
import com.aryandadhich.urja10.utils.stringUtils

class FootballAdapter (val footballTeamListener: FootballTeamListener, val updateFootballGameListener: UpdateFootballGameListener): ListAdapter<FootballGame, FootballAdapter.FootballTeamViewHolder>(DiffCallBack) {
    class FootballTeamViewHolder(private var binding: FootballListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(footballGame: FootballGame, footballTeamListener: FootballTeamListener, updateFootballGameListener: UpdateFootballGameListener){
            binding.football = footballGame
            binding.footballTeamListener = footballTeamListener
            binding.updateFootballGameListener = updateFootballGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<FootballGame>() {
        override fun areItemsTheSame(oldItem: FootballGame, newItem: FootballGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FootballGame, newItem: FootballGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FootballAdapter.FootballTeamViewHolder {
        return FootballAdapter.FootballTeamViewHolder  (
            FootballListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: FootballAdapter.FootballTeamViewHolder , position: Int) {
        val team = getItem(position)
        holder.bind(team, footballTeamListener, updateFootballGameListener)
    }
}

class FootballTeamListener(val clickListner: (footGameId: String) -> Unit){
    fun onClick(footballGame: FootballGame) = clickListner(footballGame.id)
}

class UpdateFootballGameListener(val clickListner: (footGame: FootballGame) -> Unit){
    fun onUpdateClicked(footballGame: FootballGame) = clickListner(footballGame);
}