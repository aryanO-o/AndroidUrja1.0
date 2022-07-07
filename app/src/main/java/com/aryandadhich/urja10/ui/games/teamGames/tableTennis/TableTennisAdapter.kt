package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.TableTennisListItemBinding
import com.aryandadhich.urja10.utils.stringUtils

class TableTennisAdapter(val tableTennisTeamListner: TableTennisTeamListener, val updateTableTennisGameListener: UpdateTableTennisGameListener): ListAdapter<TableTennisGame, TableTennisAdapter.TableTennisTeamViewHolder>(DiffCallBack) {
    class TableTennisTeamViewHolder(private var binding: TableTennisListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(tableTennisGame: TableTennisGame, tableTennisTeamListner: TableTennisTeamListener, updateTableTennisGameListener: UpdateTableTennisGameListener){
            binding.tableTennis = tableTennisGame
            binding.tableTennisTeamListener = tableTennisTeamListner
            binding.updateTableTennisListener = updateTableTennisGameListener;
            if(stringUtils.role == ""){
                binding.updateBtn.visibility = View.GONE
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<TableTennisGame>() {
        override fun areItemsTheSame(oldItem: TableTennisGame, newItem: TableTennisGame): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TableTennisGame, newItem: TableTennisGame): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TableTennisAdapter.TableTennisTeamViewHolder {
        return TableTennisAdapter.TableTennisTeamViewHolder (
            TableTennisListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: TableTennisAdapter.TableTennisTeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, tableTennisTeamListner, updateTableTennisGameListener)
    }
}

class TableTennisTeamListener(val clickListner: (tableTennisGameId: String) -> Unit){
    fun onClick(tableTennisGame: TableTennisGame) = clickListner(tableTennisGame.id)
}

class UpdateTableTennisGameListener(val clickListner: (tableTennisGame: TableTennisGame) -> Unit){
    fun onUpdateClicked(tableTennisGame: TableTennisGame) = clickListner(tableTennisGame);
}