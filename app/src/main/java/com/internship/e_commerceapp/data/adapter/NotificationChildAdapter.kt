package com.internship.e_commerceapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.internship.e_commerceapp.data.model.NotificationChild
import com.internship.e_commerceapp.databinding.NotificationChildItemBinding

class NotificationChildAdapter() :
    RecyclerView.Adapter<NotificationChildAdapter.NotificationChildViewHolder>() {
    private var notificationChildList: MutableList<NotificationChild> = mutableListOf()

    inner class NotificationChildViewHolder(val binding: NotificationChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationChildViewHolder {
        val binding =
            NotificationChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationChildViewHolder(binding)
    }

    override fun getItemCount() = notificationChildList.size

    override fun onBindViewHolder(holder: NotificationChildViewHolder, position: Int) {
        holder.binding.apply {
            val item = notificationChildList[position]
            ivNotificationImage.setImageResource(item.notificationImg)
            tvNotificationTitle.text = item.notificationTitle
            tvNotificationDescription.text = item.notificationDescription
        }
    }

    fun updateUi(notificationChildList: MutableList<NotificationChild>) {
        this.notificationChildList = notificationChildList
        notifyDataSetChanged()
    }
}