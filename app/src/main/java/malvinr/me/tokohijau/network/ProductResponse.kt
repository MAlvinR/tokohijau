package malvinr.me.tokohijau.network

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data") val data: List<Result?>
) {
    data class Result(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("uri") val uri: String?,
        @SerializedName("image_uri") val imageUri: String?,
        @SerializedName("image_uri_700") val largeImageUri: String?,
        @SerializedName("price") val price: String?,
        @SerializedName("shop") val shop: Shop?
    )

    data class Shop(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?
    )
}