package malvinr.me.tokohijau.data

data class ProductEntity(
        val id: Int?,
        val name: String?,
        val uri: String?,
        val imageUri: String?,
        val largeImageUri: String?,
        val price: String?,
        val shop: Shop?
)

data class Shop(
        val id: Int?,
        val name: String?
)