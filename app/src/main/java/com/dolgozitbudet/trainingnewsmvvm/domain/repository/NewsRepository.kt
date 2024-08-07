package com.dolgozitbudet.trainingnewsmvvm.domain.repository

import androidx.paging.PagingData
import com.dolgozitbudet.trainingnewsmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}