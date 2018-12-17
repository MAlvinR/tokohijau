package malvinr.me.tokohijau.feature.product

data class ProductParam(
    var keyword: String = "",
    var minPrice: Int = 0,
    var maxPrice: Int = 0,
    var isWholesale: Boolean = false,
    var isOfficial: Boolean = false,
    var golds: String = "",
    var startingIndex: Int = 0,
    var items: Int = 0
)