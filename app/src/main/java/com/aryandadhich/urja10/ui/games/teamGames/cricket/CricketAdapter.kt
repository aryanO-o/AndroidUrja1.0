package com.aryandadhich.urja10.ui.games.teamGames.cricket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.CricketListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class CricketAdapter(val cricketTeamListener: CricketTeamListener, val updateCricketGameListener: UpdateCricketGameListener): ListAdapter<CricketGame, CricketAdapter.CricketTeamViewHolder>(DiffCallBack) {
    class CricketTeamViewHolder(private var binding: CricketListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cricketGame: CricketGame, cricketTeamListener: CricketTeamListener, updateCricketGameListener: UpdateCricketGameListener){
            binding.cricket = cricketGame
            binding.cricketTeamListener = cricketTeamListener
            binding.updateCricketGameListener = updateCricketGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<CricketGame>() {
        override fun areItemsTheSame(oldItem: CricketGame, newItem: CricketGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CricketGame, newItem: CricketGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CricketAdapter.CricketTeamViewHolder {
        return CricketAdapter.CricketTeamViewHolder  (
            CricketListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: CricketAdapter.CricketTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, cricketTeamListener, updateCricketGameListener)
    }
}

class CricketTeamListener(val clickListner: (footGameId: String) -> Unit){
    fun onClick(cricketGame: CricketGame) = clickListner(cricketGame.id)
}

class UpdateCricketGameListener(val clickListner: (footGame: CricketGame) -> Unit){
    fun onUpdateClicked(cricketGame: CricketGame) = clickListner(cricketGame);
}