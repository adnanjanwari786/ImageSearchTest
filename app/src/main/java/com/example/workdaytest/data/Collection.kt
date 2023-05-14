package com.example.workdaytest.data

import com.example.workdaytest.Link

data class Collection(
    val items: List<Item>,
    val links: List<Link>,
    val version: String
)