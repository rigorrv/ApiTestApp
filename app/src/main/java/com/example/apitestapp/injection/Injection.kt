package com.example.apitestapp.injection

import android.content.Context
import androidx.room.Room
import com.example.apitestapp.api.Api
import com.example.apitestapp.repository.Repository
import com.example.apitestapp.room.Dao
import com.example.apitestapp.room.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
        return Retrofit.Builder()
            .baseUrl("https://data.cityofnewyork.us/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    @Singleton
    fun providesRepository(api: Api, dao: Dao): Repository = Repository(api, dao)

    @Provides
    @Singleton
    fun providesDao(dataBase: DataBase): Dao = dataBase.getDao()

    @Provides
    @Singleton
    fun providesDataBase(@ApplicationContext context: Context): DataBase = Room.databaseBuilder(
        context, DataBase::class.java, "SchoolDB"
    ).build()
}