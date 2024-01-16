package adarsh.xpayback

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import adarsh.core.di.userModule
import adarsh.xpayback.di.appModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule,userModule))
        }
    }
}
