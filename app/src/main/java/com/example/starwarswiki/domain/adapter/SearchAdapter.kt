package com.example.starwarswiki.domain.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarswiki.R
import com.example.starwarswiki.data.jsonData.SearchResult

class SearchAdapter(private val dataList: MutableList<SearchResult>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // ViewHolder classes for each type of view
    inner class PeopleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SearchResult.People) {
            val searchTitle: TextView = itemView.findViewById(R.id.search_title)
            val searchGender: TextView = itemView.findViewById(R.id.search_gender)
            var searchStarships: TextView = itemView.findViewById(R.id.search_starships)

            searchTitle.text = item.data.name
            searchGender.text = item.data.gender
            searchStarships.text = item.data.starships.size.toString()
        }
    }
    inner class StarshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SearchResult.Starships) {
            val searchTitle: TextView = itemView.findViewById(R.id.search_title)
            val searchModel: TextView = itemView.findViewById(R.id.search_model)
            val searchManufacturer: TextView = itemView.findViewById(R.id.search_manufacturer)
            val searchPassengers: TextView = itemView.findViewById(R.id.search_passengers)

            searchTitle.text = item.data.name
            searchModel.text = item.data.model
            searchManufacturer.text = item.data.manufacturer
            searchPassengers.text = item.data.passengers

        }
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is SearchResult.People -> SEARCH_RESULT_PEOPLE
            is SearchResult.Starships -> SEARCH_RESULT_STARSHIPS
            else -> throw IllegalArgumentException("Unknown viewType at position: $position")
        }
    }
    companion object {
        private const val SEARCH_RESULT_PEOPLE = 1
        private const val SEARCH_RESULT_STARSHIPS = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SEARCH_RESULT_PEOPLE -> PeopleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_item_people, parent, false)
            )
            SEARCH_RESULT_STARSHIPS -> StarshipViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.search_item_starships, parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataList[position]) {
            is SearchResult.People -> (holder as PeopleViewHolder).bind(item)
            is SearchResult.Starships -> (holder as StarshipViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}