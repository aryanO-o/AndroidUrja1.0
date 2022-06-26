package com.aryandadhich.urja10.ui.coordinator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.CoordinatorListItemBinding


class CoordinatorAdapter (val clickListner: CoordinatorListner): ListAdapter<Coordinator, CoordinatorAdapter.CoordinatorViewHolder>(DiffCallBack) {
    class CoordinatorViewHolder(private var binding: CoordinatorListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(coordinator: Coordinator, clickListner: CoordinatorListner){
            binding.coordinator = coordinator
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Coordinator>() {
        override fun areItemsTheSame(oldItem: Coordinator, newItem: Coordinator): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Coordinator, newItem: Coordinator): Boolean {
            return oldItem.loginId == newItem.loginId
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoordinatorAdapter.CoordinatorViewHolder {
        return CoordinatorAdapter.CoordinatorViewHolder(
            CoordinatorListItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        );
    }

    override fun onBindViewHolder(holder: CoordinatorAdapter.CoordinatorViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player, clickListner)
    }
}


class CoordinatorListner(val clickListner: (loginId: String) -> Unit){
    fun onClick(coordinator: Coordinator) = clickListner(coordinator.loginId)
}