package com.dolgozitbudet.trainingnewsmvvm.domain.usecases.news

import androidx.paging.PagingData
import com.dolgozitbudet.trainingnewsmvvm.data.remote.dto.Article
import com.dolgozitbudet.trainingnewsmvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}