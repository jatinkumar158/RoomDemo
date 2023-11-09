package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.SubscriberDAO
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SubscriberViewModel
    private lateinit var viewModelFactory: SubscriberViewModelFactory
    private lateinit var repository: SubscriberRepository
    private lateinit var dao: SubscriberDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dao = SubscriberDatabase.getDatabase(this).subscriberDAO
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        repository = SubscriberRepository(dao)
        viewModelFactory = SubscriberViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SubscriberViewModel::class.java)

        binding.myViewModel = viewModel
        binding.lifecycleOwner = this

        saveSubscriberListtoDB()

    }

    private fun saveSubscriberListtoDB() {
        viewModel.subscriberList.observe(this, Observer {
            Log.d("jatin", it.toString())
        })
    }

}