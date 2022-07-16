package com.aryandadhich.urja10.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.databinding.ChessListItemBinding
import com.aryandadhich.urja10.databinding.NoticeListItemBinding
import com.aryandadhich.urja10.ui.games.individualGames.chess.ChessAdapter
import kotlinx.coroutines.NonDisposableHandle.parent

class NoticeAdapter(val updateNoticeListener: UpdateNoticeListener): ListAdapter<Notice, NoticeAdapter.NoticeViewHolder>(DiffCallBack) {
    class NoticeViewHolder(private var binding: NoticeListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notice: Notice, updateNoticeListener: UpdateNoticeListener){
            binding.notice = notice
            binding.updateNoticeListener = updateNoticeListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<Notice>() {
        override fun areItemsTheSame(oldItem: Notice, newItem: Notice): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Notice, newItem: Notice): Boolean {
            return oldItem.id == newItem.id
        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoticeAdapter.NoticeViewHolder {
        return NoticeAdapter.NoticeViewHolder  (
            NoticeListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        );
    }

    override fun onBindViewHolder(holder: NoticeAdapter.NoticeViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player, updateNoticeListener)
    }
}

class UpdateNoticeListener(val updateNoticeListener: (id: String) -> Unit){
    fun onClick(notice: Notice) = updateNoticeListener(notice.id)
}