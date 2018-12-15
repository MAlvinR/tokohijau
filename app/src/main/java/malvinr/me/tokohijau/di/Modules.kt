package malvinr.me.tokohijau.di

import malvinr.me.tokohijau.data.DataManager
import malvinr.me.tokohijau.data.ProductRepository
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
}

val presenterModule = module {
    single { ProductPresenter(get()) }
}

val modules = listOf(
    networkModule,
    repositoryModule,
    presenterModule
)