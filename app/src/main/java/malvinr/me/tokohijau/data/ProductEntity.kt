package malvinr.me.tokohijau.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductEntity(
        val id: Int?,
        val name: String?,
        val uri: String?,
        val imageUri: String?,
        val largeImageUri: String?,
        val price: String?,
        val shop: Shop?
) : Parcelable

@Parcelize
data class Shop(
        val id: Int?,
        val name: String?
) : Parcelable