package malvinr.me.tokohijau.feature.filter

import malvinr.me.tokohijau.feature.product.ProductParam

interface ProductFilterListener {
    fun onFilterSubmit(params: ProductParam)
}