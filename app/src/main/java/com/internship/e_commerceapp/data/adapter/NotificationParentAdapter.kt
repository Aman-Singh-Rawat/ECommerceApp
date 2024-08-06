package com.internship.e_commerceapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.e_commerceapp.data.model.NotificationParent
import com.internship.e_commerceapp.databinding.NotificationParentItemBinding

class NotificationParentAdapter :
    RecyclerView.Adapter<NotificationParentAdapter.NotificationParentViewHolder>() {

    private var notificationItem: MutableList<NotificationParent> = mutableListOf()

    inner class NotificationParentViewHolder(val binding: NotificationParentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val notificationChildAdapter = NotificationChildAdapter()

        init {
            binding.rvNotificationChild.adapter = notificationChildAdapter
        }

        fun bindViews(notificationParent: NotificationParent) {
            binding.apply {
                notificationChildAdapter.updateUi(notificationParent.notificationChild)
                tvNotificationTime.text = notificationParent.notificationTime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationParentViewHolder {
        val binding = NotificationParentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NotificationParentViewHolder(binding)
    }

    override fun getItemCount(): Int = notificationItem.size

    override fun onBindViewHolder(holder: NotificationParentViewHolder, position: Int) {
        holder.bindViews(notificationItem[position])
    }

    fun updateUi(notificationItem: MutableList<NotificationParent>) {
        this.notificationItem = notificationItem
        notifyDataSetChanged()
    }
}
