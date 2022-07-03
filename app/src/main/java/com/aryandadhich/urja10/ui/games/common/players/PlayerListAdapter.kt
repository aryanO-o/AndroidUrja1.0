package com.aryandadhich.urja10.ui.games.common.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.GamePlayersListItemBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class PlayerListAdapter(val deleteGamePlayerListner: DeleteGamePlayerListner): ListAdapter<Player, PlayerListAdapter.PlayerViewHolder>(DiffCallBack) {
    class PlayerViewHolder(private var binding: GamePlayersListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(player: Player, deleteGamePlayerListner: DeleteGamePlayerListner){
            binding.player = player
            binding.deleteClickListener = deleteGamePlayerListner;
            if(role == "supervisor" || role == "house-captain" || role == "coordinator" || role == "event-coordinator")
                binding.gamePlayersDeleteBtn.visibility = View.VISIBLE
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerListAdapter.PlayerViewHolder {
        return PlayerViewHolder(GamePlayersListItemBinding.inflate(LayoutInflater.from(parent.context)));
    }

    override fun onBindViewHolder(holder: PlayerListAdapter.PlayerViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player, deleteGamePlayerListner)
    }
}

class DeleteGamePlayerListner(val clickListener: (playerId: String) -> Unit ){
    fun onDeleteBtnClicked(player: Player) =clickListener(player.id)
}