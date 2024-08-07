package com.dolgozitbudet.trainingnewsmvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dolgozitbudet.trainingnewsmvvm.data.remote.NewsApi
import com.dolgozitbudet.trainingnewsmvvm.data.remote.NewsPagingSource
import com.dolgozitbudet.trainingnewsmvvm.domain.model.Article
import com.dolgozitbudet.trainingnewsmvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
       return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}