package com.khayrul.cryptocurrencyapp.di

import com.khayrul.cryptocurrencyapp.common.Constants
import com.khayrul.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.khayrul.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.khayrul.cryptocurrencyapp.domain.repository.CoinRepository
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
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepositoryApi(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api);
    }
}