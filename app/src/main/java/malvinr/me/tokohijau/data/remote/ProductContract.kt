package malvinr.me.tokohijau.data.remote

import io.reactivex.Single
import malvinr.me.tokohijau.data.locale.ProductEntity
import malvinr.me.tokohijau.feature.product.ProductParam

interface ProductContract {
    fun searchProduct(params: ProductParam): Single<List<ProductEntity>>
}