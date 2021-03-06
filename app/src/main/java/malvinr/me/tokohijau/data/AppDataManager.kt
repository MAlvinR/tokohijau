package malvinr.me.tokohijau.data

import io.reactivex.Single
import malvinr.me.tokohijau.data.locale.ProductEntity
import malvinr.me.tokohijau.feature.product.ProductParam

interface AppDataManager {
    fun searchProduct(params: ProductParam): Single<List<ProductEntity>>
}