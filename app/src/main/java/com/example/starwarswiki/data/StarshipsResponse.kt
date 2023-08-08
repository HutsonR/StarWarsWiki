package com.example.starwarswiki.data

import com.example.starwarswiki.data.jsonData.StarshipsData

data class StarshipsResponse(
    val results: List<StarshipsData>
)
