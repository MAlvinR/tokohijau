package malvinr.me.tokohijau.feature.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_product.view.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.locale.ProductEntity

class ProductAdapter(
    private val products: List<ProductEntity>,
    private val listener: ProductListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
            ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(products[holder.adapterPosition])
    }


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(product: ProductEntity) = with(itemView) {

            Glide.with(itemView.context)
                .load(product.imageUri)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                .into(iv_product_thumb)

            tv_product_title.text = product.name
            tv_product_price.text = product.price
            tv_product_shop_title.text = product.shop?.name
            tv_product_shop_location.text = product.shop?.location

            setOnClickListener { listener.onProductClick(product) }
        }
    }
}