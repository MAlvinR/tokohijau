package malvinr.me.tokohijau.network

import io.reactivex.Flowable
import malvinr.me.tokohijau.BuildConfig
import malvinr.me.tokohijau.data.remote.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("search/${BuildConfig.API_VERSION}/product")
    fun searchProduct(
        @Query("q") keyword: String,
        @Query("pmin") minPrice: Int,
        @Query("pmax") maxPrice: Int,
        @Query("isWholesale") isWholesale: Boolean,
        @Query("official") isOfficial: Boolean,
        @Query("fshop") golds: Int,
        @Query("start") startingIndex: Int,
        @Query("rows") items: Int
    ) : Flowable<ProductResponse>
}