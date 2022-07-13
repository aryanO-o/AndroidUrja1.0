package com.aryandadhich.urja10.ui.games.individualGames.carrom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.CarromListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class CarromAdapter  (val carromTeamListener: CarromTeamListener, val updateCarromGameListener: UpdateCarromGameListener): ListAdapter<CarromGame, CarromAdapter.CarromTeamViewHolder>(DiffCallBack) {
    class CarromTeamViewHolder(private var binding: CarromListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(carromGame: CarromGame, carromTeamListener: CarromTeamListener, updateCarromGameListener: UpdateCarromGameListener){
            binding.carrom = carromGame
            binding.carromTeamListener = carromTeamListener
            binding.updateCarromGameListener = updateCarromGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<CarromGame>() {
        override fun areItemsTheSame(oldItem: CarromGame, newItem: CarromGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CarromGame, newItem: CarromGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarromAdapter.CarromTeamViewHolder {
        return CarromAdapter.CarromTeamViewHolder  (
            CarromListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: CarromAdapter.CarromTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, carromTeamListener, updateCarromGameListener)
    }
}

class CarromTeamListener(val clickListner: (carromGameId: String) -> Unit){
    fun onClick(carromGame: CarromGame) = clickListner(carromGame.id)
}

class UpdateCarromGameListener(val clickListner: (carromGame: CarromGame) -> Unit){
    fun onUpdateClicked(carromGame: CarromGame) = clickListner(carromGame);
}