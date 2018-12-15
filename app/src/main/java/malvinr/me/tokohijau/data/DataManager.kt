package malvinr.me.tokohijau.data

import io.reactivex.Single

class DataManager(
    private val productRepository: ProductRepository
) : AppDataManager {

    override fun searchProduct(params: ProductParam): Single<List<ProductEntity>> {
        return productRepository.searchProduct(params)
    }
}