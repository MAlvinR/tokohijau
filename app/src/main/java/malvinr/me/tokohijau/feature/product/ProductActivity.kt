package malvinr.me.tokohijau.feature.product

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.content.res.AppCompatResources
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

class ProductActivity : AppCompatActivity(), ProductView, ProductListener {

    private val presenter: ProductPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val keyword = intent.getStringExtra("KEYWORD")

        val params = ProductParam(
            keyword = keyword,
            minPrice = 0,
            maxPrice = 100000000,
            isWholesale = false,
            isOfficial = true,
            golds = "2"
        )

        presenter.onAttach(this)
        presenter.searchProduct(params)
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
            android.R.id.home -> finish()
            R.id.action_filter -> {
                Toast.makeText(this, getString(R.string.filter), Toast.LENGTH_SHORT).show()
            }
        }

        return true
    }

    private fun getBitmapFromVectorDrawable(@DrawableRes drawableId: Int): Bitmap? {
        var drawable = AppCompatResources.getDrawable(this, drawableId) ?: return null
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}
