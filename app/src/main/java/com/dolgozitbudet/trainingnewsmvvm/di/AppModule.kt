package com.dolgozitbudet.trainingnewsmvvm.di

import android.app.Application
import com.dolgozitbudet.trainingnewsmvvm.data.manager.LocalUserManagerImpl
import com.dolgozitbudet.trainingnewsmvvm.data.remote.NewsApi
import com.dolgozitbudet.trainingnewsmvvm.data.repository.NewsRepositoryImpl
import com.dolgozitbudet.trainingnewsmvvm.domain.manager.LocalUserManager
import com.dolgozitbudet.trainingnewsmvvm.domain.repository.NewsRepository
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.app_entry.AppEntryUseCases
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.app_entry.ReadAppEntry
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.app_entry.SaveAppEntry
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.news.GetNews
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.news.NewsUseCases
import com.dolgozitbudet.trainingnewsmvvm.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi) = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }



}