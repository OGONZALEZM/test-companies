package com.ogonzalezm.companydirectory.di

import android.app.Application
import android.content.Context
import com.ogonzalezm.companydirectory.database.Database
import com.ogonzalezm.companydirectory.database.dao.CompanyDAO
import com.ogonzalezm.companydirectory.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideCompanyRepository (
        companyDAO: CompanyDAO
    ): CompanyRepository {
        return CompanyRepository(companyDAO)
    }

}