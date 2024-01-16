package adarsh.core.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import adarsh.core.Constants.BASE_URL
import adarsh.core.data.api.UserService
import adarsh.core.data.repository.UserDetailsRepository
import adarsh.core.data.repository.ListUserRepository


val userModule = module {
    val gson = GsonBuilder().setLenient().create()

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(UserService::class.java) }

    single { ListUserRepository(get()) }
    single { UserDetailsRepository(get()) }
}
