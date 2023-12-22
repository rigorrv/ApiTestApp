package com.example.apitestapp.injection

import android.content.Context
import androidx.room.Room
import com.example.apitestapp.api.Api
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.room.AppDataBase
import com.example.apitestapp.room.Dao
import com.example.apitestapp.utilities.ApplicationConstants.baseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Injection {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun providesRepository(api: Api, dao: Dao) = Repository(api, dao)

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
            "MovieDB.db"
        ).build()
    }
}