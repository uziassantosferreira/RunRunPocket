package com.uzias.runrunpocket.core.networking.di

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Named
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.uzias.runrunpocket.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory
import com.uzias.runrunpocket.core.networking.http.HttpHeadersInterceptor
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetworkModule {

    companion object {

        private val API_URL : String = "https://secure.runrun.it/api/v1.0/"

    }

    @Provides
    @Named("baseUrl")
    fun providesBaseUrl(): String = API_URL

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun provideGsonConverter(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)


    @Provides
    fun providesOkHttpInterceptors(
            @Named("httpHeadersInterceptor") httpHeadersInterceptor: Interceptor,
            @Named("loggingInterceptor") loggingInterceptor: Interceptor): Set<Interceptor> {
        val interceptors = mutableListOf<Interceptor>()
        interceptors.add(httpHeadersInterceptor)
        if (BuildConfig.DEBUG) {
            interceptors.add(loggingInterceptor)
        }
        return interceptors.toSet()
    }

    @Provides
    @Named("httpHeadersInterceptor")
    fun providesHttpHeadersInterceptor(): Interceptor {
        return HttpHeadersInterceptor()
    }

    @Provides
    @Named("loggingInterceptor")
    fun providesLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @JvmSuppressWildcards

    fun provideOkHttpClient(interceptors: Set<Interceptor>): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        for (interceptor in interceptors) {
            httpClientBuilder.addInterceptor(interceptor)
        }
        return httpClientBuilder
                .build()
    }

    @Provides
    fun provideRetrofit(converterFactory: GsonConverterFactory,
                        okHttpClient: OkHttpClient,
                        @Named("baseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .build()
    }

    @Provides
    fun providesRxJava2CallAdapter(): RxJava2CallAdapterFactory
            = RxJava2CallAdapterFactory.create()

}