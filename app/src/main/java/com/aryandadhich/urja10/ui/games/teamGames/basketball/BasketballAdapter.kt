package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.BasketballListItemBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class BasketballAdapter (val basketballTeamListner: BasketballTeamListner, val updateBasketballGameListener: UpdateBasketballGameListener): ListAdapter<BasketballGame, BasketballAdapter.BasketballTeamViewHolder>(DiffCallBack) {
    class BasketballTeamViewHolder(private var binding: BasketballListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(basketballGame: BasketballGame, basketballTeamListner: BasketballTeamListner, updateBasketballGameListener: UpdateBasketballGameListener){
            binding.basketball = basketballGame
            binding.basketballTeamListener = basketballTeamListner
            binding.updateBasketballListener = updateBasketballGameListener;
            if(role == ""){
                binding.updateBtn.visibility = View.GONE
            }
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
        holder.bind(team, basketballTeamListner, updateBasketballGameListener)
    }
}

class BasketballTeamListner(val clickListner: (basketballGameId: String) -> Unit){
    fun onClick(basketballGame: BasketballGame) = clickListner(basketballGame.id)
}

class UpdateBasketballGameListener(val clickListner: (basketballGame: BasketballGame) -> Unit){
    fun onUpdateClicked(basketballGame: BasketballGame) = clickListner(basketballGame);
}