package malvinr.me.tokohijau.di

import android.content.Context
import malvinr.me.tokohijau.data.DataManager
import malvinr.me.tokohijau.data.locale.PreferencesManager
import malvinr.me.tokohijau.data.remote.ProductRepository
import malvinr.me.tokohijau.feature.product.ProductPresenter
import malvinr.me.tokohijau.network.ApiClient
import malvinr.me.tokohijau.network.ApiServices
import org.koin.dsl.module.module

val networkModule = module {
    single { ApiClient.builder().create(ApiServices::class.java) } bind ApiServices::class
}

val repositoryModule = module {
    single { ProductRepository(get()) }
    single { DataManager(get()) }
    single { PreferencesManager(get()) }
}

val presenterModule = module {
    single { ProductPresenter(get()) }
}

val modules = listOf(
    networkModule,
    repositoryModule,
    presenterModule
)