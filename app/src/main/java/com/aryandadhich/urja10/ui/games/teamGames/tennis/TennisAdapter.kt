package com.aryandadhich.urja10.ui.games.teamGames.tennis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.TennisListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class TennisAdapter (val tennisTeamListner: TennisTeamListener, val updateTennisGameListener: UpdateTennisGameListener): ListAdapter<TennisGame, TennisAdapter.TennisTeamViewHolder>(DiffCallBack) {
    class TennisTeamViewHolder(private var binding: TennisListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tennisGame: TennisGame, tennisTeamListner: TennisTeamListener, updateTennisGameListener: UpdateTennisGameListener){
            binding.tennis = tennisGame
            binding.tennisTeamListener = tennisTeamListner
            binding.updateTennisListener = updateTennisGameListener;
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<TennisGame>() {
        override fun areItemsTheSame(oldItem: TennisGame, newItem: TennisGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TennisGame, newItem: TennisGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TennisAdapter.TennisTeamViewHolder {
        return TennisAdapter.TennisTeamViewHolder (
            TennisListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: TennisAdapter.TennisTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, tennisTeamListner, updateTennisGameListener)
    }
}

class TennisTeamListener(val clickListner: (tennisGameId: String) -> Unit){
    fun onClick(tennisGame: TennisGame) = clickListner(tennisGame.id)
}

class UpdateTennisGameListener(val clickListner: (tennisGame: TennisGame) -> Unit){
    fun onUpdateClicked(tennisGame: TennisGame) = clickListner(tennisGame);
}