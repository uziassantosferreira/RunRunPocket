package com.uzias.runrunpocket.core.application

import android.app.Application
import com.uzias.runrunpocket.core.di.AppComponent
import com.uzias.runrunpocket.core.di.AppModule
import com.uzias.runrunpocket.core.di.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

class RunRunPocketApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(config)
    }
}