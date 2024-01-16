package adarsh.xpayback.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import adarsh.xpayback.listusers.viewmodel.ListUserViewModel
import adarsh.xpayback.userdeatils.viewmodel.UserDetailsViewModel

val appModule = module {
    viewModel { ListUserViewModel(get()) }
    viewModel { UserDetailsViewModel(get()) }
}
