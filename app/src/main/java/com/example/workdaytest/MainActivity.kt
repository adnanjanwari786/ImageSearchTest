package com.example.workdaytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.workdaytest.network.ApiState
import com.example.workdaytest.viewmodel.ImageSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:ImageSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        viewModel.searchImages("mars",1,10)

        lifecycleScope.launchWhenStarted {
            viewModel.images.collect {it->
                when(it){
                    is ApiState.Loading->{
                       Toast.makeText(applicationContext,"loading " + ApiState.Loading,Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Failure -> {
                        Toast.makeText(applicationContext,it.msg.message ?: "Error",Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Success->{
                        Toast.makeText(applicationContext,it.data.collection.version,Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Empty->{
                        Toast.makeText(applicationContext,"Empty",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}