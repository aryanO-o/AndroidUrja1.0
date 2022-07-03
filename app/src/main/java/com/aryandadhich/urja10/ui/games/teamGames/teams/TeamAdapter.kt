package com.aryandadhich.urja10.ui.games.teamGames.teams

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.TeamsListItemBinding
import com.aryandadhich.urja10.utils.stringUtils.Companion.role

class TeamAdapter(val teamListner: TeamListner, val deleteTeamListner: DeleteTeamListner): ListAdapter<Team, TeamAdapter.TeamViewHolder>(DiffCallBack) {
    class TeamViewHolder(private var binding: TeamsListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(team: Team, teamListner: TeamListner, deleteTeamListner: DeleteTeamListner){
            binding.team = team
            binding.teamListener = teamListner
            binding.deleteTeamListener = deleteTeamListner
            if(role == "") binding.deleteTeamBtn.visibility = View.GONE
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.teamId == newItem.teamId
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamAdapter.TeamViewHolder {
        return TeamAdapter.TeamViewHolder(
            TeamsListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        );
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = getItem(position)
        holder.bind(team, teamListner, deleteTeamListner)
    }
}

class TeamListner(val clickListner: (teamId: String) -> Unit){
    fun onClick(team: Team) = clickListner(team.teamId)
}

class DeleteTeamListner(val clickListner: (teamId: String) -> Unit){
    fun onDeleteClicked(team: Team) = clickListner(team.teamId);
}