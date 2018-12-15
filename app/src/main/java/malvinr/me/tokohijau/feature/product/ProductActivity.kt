package malvinr.me.tokohijau.feature.product

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.ProductEntity
import malvinr.me.tokohijau.data.ProductParam
import org.koin.android.ext.android.inject

class ProductActivity : AppCompatActivity(), ProductView {

    private val presenter: ProductPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val params = ProductParam(
                keyword = "baju",
                minPrice = 0,
                maxPrice = 100000000,
                isWholesale = false,
                isOfficial = false,
                golds = "2")

        presenter.onAttach(this)
        presenter.searchProduct(params)
    }

    override fun onShowLoading() {
    }

    override fun onHideLoading() {
    }

    override fun onShowProduct(product: List<ProductEntity>) {
        Log.d("TOKOHIJAU", product.toString())
    }

    override fun onShowErrorMessage(message: String) {
        Log.d("TOKOHIJAU", message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}