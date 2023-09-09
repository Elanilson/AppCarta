package com.apkdoandroid.appcarta.di

import com.apkdoandroid.appcarta.data.api.BaseInterceptor
import com.apkdoandroid.appcarta.data.api.HearthstoneService
import com.apkdoandroid.appcarta.data.repository.CartaRepository
import com.apkdoandroid.appcarta.data.repository.CartaRepositoryImpl
import com.jamiltondamasceno.testeempregopetz.utils.Constantes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun providesRetrofit(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
    @Provides
    fun providesClientHttp() : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(BaseInterceptor())
            .build()
    }

    @Provides
    fun providesHearthstoneService(retrofit: Retrofit) : HearthstoneService {
        return retrofit.create(HearthstoneService::class.java)
    }

    @Provides
    fun providesCartaRepository(hearthstoneService : HearthstoneService) : CartaRepository {
        return CartaRepositoryImpl(hearthstoneService)
    }
}