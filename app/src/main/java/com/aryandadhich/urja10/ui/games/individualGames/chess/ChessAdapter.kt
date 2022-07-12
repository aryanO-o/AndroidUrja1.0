package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.ChessListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class ChessAdapter (val chessTeamListener: ChessTeamListener, val updateChessGameListener: UpdateChessGameListener): ListAdapter<ChessGame, ChessAdapter.ChessTeamViewHolder>(DiffCallBack) {
    class ChessTeamViewHolder(private var binding: ChessListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chessGame: ChessGame, chessTeamListener: ChessTeamListener, updateChessGameListener: UpdateChessGameListener){
            binding.chess = chessGame
            binding.chessTeamListener = chessTeamListener
            binding.updateChessGameListener = updateChessGameListener
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<ChessGame>() {
        override fun areItemsTheSame(oldItem: ChessGame, newItem: ChessGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ChessGame, newItem: ChessGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChessAdapter.ChessTeamViewHolder {
        return ChessAdapter.ChessTeamViewHolder  (
            ChessListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: ChessAdapter.ChessTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, chessTeamListener, updateChessGameListener)
    }
}

class ChessTeamListener(val clickListner: (chessGameId: String) -> Unit){
    fun onClick(chessGame: ChessGame) = clickListner(chessGame.id)
}

class UpdateChessGameListener(val clickListner: (chessGame: ChessGame) -> Unit){
    fun onUpdateClicked(chessGame: ChessGame) = clickListner(chessGame);
}