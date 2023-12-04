package com.example.apitestapp.injection

import android.content.Context
import androidx.room.Room
import com.example.apitestapp.room.AppDataBase
import com.example.apitestapp.room.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Injection {

    @Provides
    fun provideChannelDao(appDatabase: AppDataBase): Dao {
        return appDatabase.getDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            "MyMovies.db"
        ).build()
    }


}