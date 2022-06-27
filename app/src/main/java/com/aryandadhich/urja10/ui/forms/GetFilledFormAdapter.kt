package com.aryandadhich.urja10.ui.forms

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.FilledFormsListItemsBinding

class GetFilledFormAdapter: ListAdapter<FillForm, GetFilledFormAdapter.GetFilledFormViewHolder>(DiffCallBack) {
    class GetFilledFormViewHolder(private var binding: FilledFormsListItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(fillForm: FillForm){
            binding.fillForm = fillForm
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<FillForm>() {
        override fun areItemsTheSame(oldItem: FillForm, newItem: FillForm): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FillForm, newItem: FillForm): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GetFilledFormAdapter.GetFilledFormViewHolder {
        return GetFilledFormAdapter.GetFilledFormViewHolder(
            FilledFormsListItemsBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        );
    }

    override fun onBindViewHolder(holder: GetFilledFormAdapter.GetFilledFormViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player)
    }
}
