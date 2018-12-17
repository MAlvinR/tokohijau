package malvinr.me.tokohijau.data

import io.reactivex.Single
import malvinr.me.tokohijau.data.locale.ProductEntity
import malvinr.me.tokohijau.data.remote.ProductRepository
import malvinr.me.tokohijau.feature.product.ProductParam

class DataManager(
    private val productRepository: ProductRepository
) : AppDataManager {

    override fun searchProduct(params: ProductParam): Single<List<ProductEntity>> {
        return productRepository.searchProduct(params)
    }
}