package com.internship.e_commerceapp.data.model

data class NotificationParent(
    val notificationTime: String,
    val notificationChild: MutableList<NotificationChild>
)
