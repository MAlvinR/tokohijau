package malvinr.me.tokohijau.feature.product

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_product.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.ProductEntity
import malvinr.me.tokohijau.data.ProductParam
import org.koin.android.ext.android.inject

class ProductActivity : AppCompatActivity(), ProductView {

    private val presenter: ProductPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val params = ProductParam(
                keyword = "baju",
                minPrice = 0,
                maxPrice = 100000000,
                isWholesale = false,
                isOfficial = true,
                golds = "2")

        presenter.onAttach(this)
        presenter.searchProduct(params)
    }

    override fun onShowLoading() {
    }

    override fun onHideLoading() {
    }

    override fun onShowProduct(product: List<ProductEntity>) {
        list_product.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ProductAdapter(product)
        }
    }

    override fun onShowErrorMessage(message: String) {
        Log.d("TOKOHIJAU", message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_filter -> {
                Toast.makeText(this, getString(R.string.filter), Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }
}
