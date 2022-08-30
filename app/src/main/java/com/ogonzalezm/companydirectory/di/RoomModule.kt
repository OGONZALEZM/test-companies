package com.ogonzalezm.companydirectory.di

import android.content.Context
import androidx.room.Room
import com.ogonzalezm.companydirectory.database.Database
import com.ogonzalezm.companydirectory.database.dao.CompanyDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room
            .databaseBuilder(
                context,
                Database::class.java,
                Database.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCompanyDao(database: Database): CompanyDAO {
        return database.companyDAO()
    }

}