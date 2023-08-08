package com.example.starwarswiki.data.jsonData

sealed class SearchResult {
    data class People(val data: PeopleData) : SearchResult()
    data class Starships(val data: StarshipsData) : SearchResult()
}
