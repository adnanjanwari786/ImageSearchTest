package com.example.workdaytest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workdaytest.R
import com.example.workdaytest.data.ItemData
import com.example.workdaytest.network.ApiState
import com.example.workdaytest.viewmodel.ImageSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ImageListAdapter.ItemClickListener {

    private val viewModel:ImageSearchViewModel by viewModels()
    private lateinit var adapter: ImageListAdapter

    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false
    var query = "mars"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        init()
    }//



    private fun initUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val editText = findViewById<EditText>(R.id.et_search)
        adapter = ImageListAdapter(this)
        recyclerView.adapter = adapter
        // Set up pagination
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // Add scroll listener for pagination
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && !isLastPage && totalItemCount <= lastVisibleItem + 5) {
                    currentPage++
                    loadMoreItems()
                }
            }
        })

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Handle "Done" button click event here
                currentPage = 1
                query = editText.text.toString()
                viewModel.searchImages(query, currentPage, PAGE_SIZE)
                true // Return true to indicate that the event has been handled
            } else {
                false // Return false if the event is not handled
            }
        }

    }

    private fun loadMoreItems() {
        if (currentPage == MAX_PAGES)
            return
        viewModel.searchImages(query, currentPage, PAGE_SIZE)
    }

    private fun navigateToDetail(item:ItemData) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("item",item)
        startActivity(intent)
    }

    private fun init() {
        viewModel.searchImages(query,1,10)

        lifecycleScope.launchWhenStarted {
            viewModel.images.collect {it->
                when(it){
                    is ApiState.Loading->{
                       Toast.makeText(applicationContext,"loading ",Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Failure -> {
                        Toast.makeText(applicationContext,it.msg.message ?: "Error",Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Success->{
                        val mappedItems = it.data.collection.items.map { item ->
                            val mappedData = item.data.map { itemData ->
                                val link = item.links?.firstOrNull()
                                val imageUrl = link?.href ?: ""
                                itemData.copy(imageUrl = imageUrl)
                            }
                            item.copy(data = mappedData)
                        }
                        if (currentPage ==1 ){
                            adapter.refreshItems(mappedItems)
                        }
                        else
                            adapter.addItems(mappedItems)
//                        Toast.makeText(applicationContext,"" + it.data.collection.items.size,Toast.LENGTH_SHORT).show()
                    }
                    is ApiState.Empty->{
                        Toast.makeText(applicationContext,"Empty",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


    }

    override fun onCLick(item: ItemData) {
        navigateToDetail(item)
    }


    companion object {
        private const val PAGE_SIZE = 10
        private const val MAX_PAGES = 100
    }
}