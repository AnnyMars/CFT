package com.example.cfttest.model

data class CardInfo(
    val bank: Bank,
    val brand: String? = null,
    val country: Country? = null,
    val number: Number? = null,
    var prepaid: Boolean? = null,
    val scheme: String? = null,
    val type: String? = null
)