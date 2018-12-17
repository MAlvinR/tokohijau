package malvinr.me.tokohijau.feature.product

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_product.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.locale.PreferencesManager
import malvinr.me.tokohijau.data.locale.ProductEntity
import malvinr.me.tokohijau.feature.filter.ProductFilterBottomSheetFragment
import malvinr.me.tokohijau.feature.filter.ProductFilterListener
import malvinr.me.tokohijau.utils.getBitmapFromVectorDrawable
import malvinr.me.tokohijau.utils.toast
import org.koin.android.ext.android.inject

class ProductActivity : AppCompatActivity(), ProductView,
    ProductListener, ProductFilterListener {

    private val presenter: ProductPresenter by inject()
    private val preferences: PreferencesManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val keyword = intent.getStringExtra("KEYWORD")
        preferences.setKeyword(keyword)

        presenter.onAttach(this)
        getProduct(preferences.getKeyword(), false, false)
    }

    override fun onShowLoading() {
    }

    override fun onHideLoading() {
    }

    override fun onProductClick(product: ProductEntity) {
        CustomTabsIntent.Builder().apply {
            addDefaultShareMenuItem()
            setToolbarColor(ContextCompat.getColor(applicationContext, R.color.colorPrimary))
            setShowTitle(true)
            val closeButton = getBitmapFromVectorDrawable(R.drawable.ic_close_black)
            if (closeButton != null) {
                setCloseButtonIcon(closeButton)
            }
        }.build().launchUrl(this, Uri.parse(product.uri))
    }

    override fun onShowProduct(product: List<ProductEntity>) {
        list_product.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = ProductAdapter(product, this@ProductActivity)
        }
    }

    override fun onShowErrorMessage(message: String) {
        toast(message)
    }

    override fun onFilterSubmit(params: ProductParam) {
        getProduct(preferences.getKeyword(), preferences.getWholesale(), preferences.getOfficialStore())
    }

    private fun getProduct(keyword: String, isWholesale: Boolean, isOfficial: Boolean) {
        val params = ProductParam(
            keyword = keyword,
            minPrice = 0,
            maxPrice = 100000000,
            isWholesale = isWholesale,
            isOfficial = isOfficial,
            golds = "2"
        )
        presenter.searchProduct(params)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
        preferences.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.action_filter -> {
                val fragment = ProductFilterBottomSheetFragment()
                fragment.show(supportFragmentManager, getString(R.string.filter))
            }
        }

        return true
    }
}
