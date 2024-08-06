package com.dolgozitbudet.trainingnewsmvvm.di

import android.app.Application
import com.dolgozitbudet.trainingnewsmvvm.data.manager.LocalUserManagerImpl
import com.dolgozitbudet.trainingnewsmvvm.domain.manager.LocalUserManager
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.AppEntryUseCases
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.ReadAppEntry
import com.dolgozitbudet.trainingnewsmvvm.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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



}