package malvinr.me.tokohijau.data

data class ProductParam(
    var keyword: String, var minPrice: Int, var maxPrice: Int, var isWholesale: Boolean, var isOfficial: Boolean,
    var golds: String,var startingIndex: Int = 0,var items: Int = 0
)