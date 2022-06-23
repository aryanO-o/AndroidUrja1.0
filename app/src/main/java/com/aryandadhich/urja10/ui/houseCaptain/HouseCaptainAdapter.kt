package com.aryandadhich.urja10.ui.houseCaptain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.HouseCaptainListItemBinding

class HouseCaptainAdapter(val clickListner: HouseCaptainListner): ListAdapter<HouseCaptain, HouseCaptainAdapter.HouseCaptainViewHolder>(DiffCallBack) {
    class HouseCaptainViewHolder(private var binding: HouseCaptainListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(houseCaptain: HouseCaptain, clickListner: HouseCaptainListner){
            binding.houseCaptain = houseCaptain
            binding.clickListner = clickListner
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<HouseCaptain>() {
        override fun areItemsTheSame(oldItem: HouseCaptain, newItem: HouseCaptain): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HouseCaptain, newItem: HouseCaptain): Boolean {
            return oldItem.loginId == newItem.loginId
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HouseCaptainAdapter.HouseCaptainViewHolder {
        return HouseCaptainViewHolder(HouseCaptainListItemBinding.inflate(LayoutInflater.from(parent.context)));
    }

    override fun onBindViewHolder(holder: HouseCaptainAdapter.HouseCaptainViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player, clickListner)
    }
}

class HouseCaptainListner(val clickListner: (loginId: String) -> Unit){
    fun onClick(houseCaptain: HouseCaptain) = clickListner(houseCaptain.loginId)
}