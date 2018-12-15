package malvinr.me.tokohijau.data

import io.reactivex.Single

interface AppDataManager {
    fun searchProduct(params: ProductParam): Single<List<ProductEntity>>
}