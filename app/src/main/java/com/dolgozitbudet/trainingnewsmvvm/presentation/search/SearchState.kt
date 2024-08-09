package com.dolgozitbudet.trainingnewsmvvm.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.dolgozitbudet.trainingnewsmvvm.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)