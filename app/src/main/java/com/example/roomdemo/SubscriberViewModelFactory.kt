package com.example.roomdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.db.SubscriberRepository

class SubscriberViewModelFactory(
    private val subscriberRepository: SubscriberRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SubscriberViewModel::class.java)) {
            SubscriberViewModel(this.subscriberRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}