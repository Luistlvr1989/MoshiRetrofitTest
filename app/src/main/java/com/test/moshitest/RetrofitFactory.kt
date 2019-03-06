package com.test.moshitest

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.ArrayList
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    val endpoint by lazy {
        val client = providesOkHttpClient(providesInterceptors())
        val moshi = providesMoshi()
        val retrofit = providesRetrofit(client, moshi)
        providesEndpoint(retrofit)
    }

    private fun providesInterceptors(): ArrayList<Interceptor> {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        return ArrayList(listOf(logging))
    }

    private fun providesOkHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(45, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                for (interceptor in interceptors) {
                    addInterceptor(interceptor)
                }
            }
            .build()
    }

    private fun providesMoshi(): Moshi {
        return Moshi.Builder()
            //.add(EnvelopeAdapter.FACTORY)
            //.add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun providesRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .build()
    }

    private fun providesEndpoint(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}