package malvinr.me.tokohijau.data.remote

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import malvinr.me.tokohijau.feature.product.ProductParam
import malvinr.me.tokohijau.data.locale.ProductEntity
import malvinr.me.tokohijau.data.locale.Shop
import malvinr.me.tokohijau.network.ApiServices

class ProductRepository(private val service: ApiServices) :
    ProductContract {

    override fun searchProduct(params: ProductParam): Single<List<ProductEntity>> {
        return service
            .searchProduct(
                params.keyword, params.minPrice, params.maxPrice, params.isWholesale,
                params.isOfficial, params.golds, params.startingIndex, params.items)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it.data }
            .map {
                val shop = Shop(
                    it.shop?.id,
                    it.shop?.name,
                    it.shop?.location
                )

                ProductEntity(
                    it.id,
                    it.name,
                    it.uri,
                    it.imageUri,
                    it.largeImageUri,
                    it.price,
                    shop
                )
            }
            .toList()
    }
}