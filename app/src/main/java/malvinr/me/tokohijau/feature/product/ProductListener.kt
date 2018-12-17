package malvinr.me.tokohijau.feature.product

import malvinr.me.tokohijau.data.locale.ProductEntity

interface ProductListener {
    fun onProductClick(product: ProductEntity)
}