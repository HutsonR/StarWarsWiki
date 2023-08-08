package com.example.starwarswiki.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarswiki.data.ApiInterface
import com.example.starwarswiki.data.jsonData.SearchResult
import com.example.starwarswiki.databinding.ActivityMainBinding
import com.example.starwarswiki.domain.adapter.SearchAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val TAG = "debugTag"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SearchAdapter
    private var dataList: MutableList<SearchResult> = mutableListOf()
    private var searchJob: Job? = null
    private val apiRequestDelayMillis: Long = 500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecycler()

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Cancel any ongoing search job to avoid unnecessary API requests
                searchJob?.cancel()

                if (count >= 2) {
                    // Clear the dataList before making a new API request
                    dataList.clear()

                    // Start a coroutine to handle the delayed API request
                    searchJob = CoroutineScope(Dispatchers.Main).launch {
                        // Add a delay before making the API request
                        delay(apiRequestDelayMillis)

                        // Make a single API request for both people and starships
                        val apiInterface = ApiInterface.create()

                        try {
                            val peopleResponse = withContext(Dispatchers.IO) {
                                apiInterface.getPeople(s.toString()).execute()
                            }
                            if (peopleResponse.isSuccessful) {
                                val peopleList = peopleResponse.body()?.results
                                peopleList?.let { it ->
                                    dataList.addAll(it.map { SearchResult.People(it) })
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                                Log.d(TAG, "OnResponse Error: ${peopleResponse.errorBody()?.string()}")
                            }

                            val starshipsResponse = withContext(Dispatchers.IO) {
                                apiInterface.getStarships(s.toString()).execute()
                            }
                            if (starshipsResponse.isSuccessful) {
                                val starshipList = starshipsResponse.body()?.results
                                starshipList?.let { it ->
                                    dataList.addAll(it.map { SearchResult.Starships(it) })
                                    adapter.notifyDataSetChanged()
                                }
                            } else {
                                Log.d(TAG, "OnResponse Error: ${starshipsResponse.errorBody()?.string()}")
                            }
                            // Update the adapter after the API request is done
                            adapter.notifyDataSetChanged()
                        } catch (e: Exception) {
                            Log.d(TAG, "Exception: ${e.message}")

                            // Update the adapter even if the API request fails
                            adapter.notifyDataSetChanged()
                        }
                    }
                } else { // If the input field is empty
                    dataList.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun setRecycler() {
        val recyclerView = binding.recycleSearch
        recyclerView.layoutManager = LinearLayoutManager(MainActivity())

        adapter = SearchAdapter(dataList)
        recyclerView.adapter = adapter
    }
}