package com.example.workdaytest.data

data class ItemData(
    val center: String,
    val date_created: String,
    val description: String,
    val keywords: List<String>,
    val location: String,
    val media_type: String,
    val nasa_id: String,
    val photographer: String,
    val title: String
)