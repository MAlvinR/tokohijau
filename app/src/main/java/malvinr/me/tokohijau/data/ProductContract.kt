package malvinr.me.tokohijau.data

import io.reactivex.Single

interface ProductContract {
    fun searchProduct(params: ProductParam): Single<List<ProductEntity>>
}