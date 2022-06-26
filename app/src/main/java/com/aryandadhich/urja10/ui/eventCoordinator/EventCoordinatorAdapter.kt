package com.aryandadhich.urja10.ui.eventCoordinator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.EventCoordinatorListItemBinding


class EventCoordinatorAdapter  (val clickListner: EventCoordinatorListner): ListAdapter<EventCoordinator, EventCoordinatorAdapter.EventCoordinatorViewHolder>(DiffCallBack) {
    class EventCoordinatorViewHolder(private var binding: EventCoordinatorListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(eventCoordinator: EventCoordinator, clickListner: EventCoordinatorListner){
            binding.eventCoordinator = eventCoordinator
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<EventCoordinator>() {
        override fun areItemsTheSame(oldItem: EventCoordinator, newItem: EventCoordinator): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EventCoordinator, newItem: EventCoordinator): Boolean {
            return oldItem.loginId == newItem.loginId
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventCoordinatorAdapter.EventCoordinatorViewHolder {
        return EventCoordinatorAdapter.EventCoordinatorViewHolder(
            EventCoordinatorListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        );
    }

    override fun onBindViewHolder(holder: EventCoordinatorAdapter.EventCoordinatorViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player, clickListner)
    }
}

class EventCoordinatorListner(val clickListner: (loginId: String) -> Unit){
    fun onClick(eventCoordinator: EventCoordinator) = clickListner(eventCoordinator.loginId)
}