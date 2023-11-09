package com.example.roomdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubscriberViewModel(
    private val subscriberRepository: SubscriberRepository
): ViewModel() {

    val subscriberList = subscriberRepository.subscribers

    val name = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    val saveOrUpdateText = MutableLiveData<String>()
    val clearAllOrDeleteText = MutableLiveData<String>()

    init {
        saveOrUpdateText.value = "Save"
        clearAllOrDeleteText.value = "Clear All"
    }

    fun saveOrUpdate() {
        val s_name = name.value!!
        val s_email = email.value!!
        insert(Subscriber(1, s_name, s_email))
    }

    fun clearAllOrDelete() {
        deleteAll()
    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
            subscriberRepository.insert(subscriber)
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        subscriberRepository.update(subscriber)
    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        subscriberRepository.delete(subscriber)
    }

    private fun deleteAll() = viewModelScope.launch {
        subscriberRepository.deleteAll()
    }

}