package com.dolgozitbudet.trainingnewsmvvm.data.remote.dto

import com.dolgozitbudet.trainingnewsmvvm.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)