package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.BasketballListItemBinding

class BasketballAdapter (val basketballTeamListner: BasketballTeamListner, val deleteBasketballTeamListner: DeleteBasketballTeamListner): ListAdapter<BasketballGame, BasketballAdapter.BasketballTeamViewHolder>(DiffCallBack) {
    class BasketballTeamViewHolder(private var binding: BasketballListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(basketballGame: BasketballGame, basketballTeamListner: BasketballTeamListner, deleteBasketballTeamListner: DeleteBasketballTeamListner){
            binding.basketball = basketballGame
            binding.basketballTeamListener = basketballTeamListner
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<BasketballGame>() {
        override fun areItemsTheSame(oldItem: BasketballGame, newItem: BasketballGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: BasketballGame, newItem: BasketballGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BasketballAdapter.BasketballTeamViewHolder {
        return BasketballAdapter.BasketballTeamViewHolder (
            BasketballListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: BasketballAdapter.BasketballTeamViewHolder , position: Int) {
        val team = getItem(position)
        holder.bind(team, basketballTeamListner, deleteBasketballTeamListner)
    }
}

class BasketballTeamListner(val clickListner: (basketballGameId: String) -> Unit){
    fun onClick(basketballGame: BasketballGame) = clickListner(basketballGame.id)
}

class DeleteBasketballTeamListner(val clickListner: (basketballGameId: String) -> Unit){
    fun onDeleteClicked(basketballGame: BasketballGame) = clickListner(basketballGame.id);
}