package com.uzias.runrunpocket.core.di

import com.uzias.runrunpocket.core.networking.di.NetworkModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
@Singleton
interface AppComponent {

    fun retrofit(): Retrofit
}