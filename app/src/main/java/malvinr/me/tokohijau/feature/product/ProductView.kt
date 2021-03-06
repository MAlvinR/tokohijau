package malvinr.me.tokohijau.feature.product

import malvinr.me.tokohijau.data.locale.ProductEntity

interface ProductView {
    fun onShowLoading()
    fun onHideLoading()
    fun onShowProduct(product: List<ProductEntity>)
    fun onShowErrorMessage(message: String)
}