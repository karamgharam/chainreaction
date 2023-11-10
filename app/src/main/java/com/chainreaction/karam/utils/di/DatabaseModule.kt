package com.chainreaction.karam.utils.di

import android.content.Context
import com.chainreaction.karam.data.repository.SharedPreferencesRepositoryImp
import com.chainreaction.karam.data.source.local.SharedPreferenceManager
import com.chainreaction.karam.domain.repository.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getUserSession(@ApplicationContext context: Context): SharedPreferenceManager {
        return SharedPreferenceManager(context)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesRepository(
        sessionManager: SharedPreferenceManager
    ): SharedPreferencesRepository {
        return SharedPreferencesRepositoryImp(sessionManager)
    }


}
