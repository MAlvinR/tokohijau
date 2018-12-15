package malvinr.me.tokohijau.feature.product

import malvinr.me.tokohijau.data.ProductEntity

interface ProductListener {
    fun onProductClick(product: ProductEntity)
}