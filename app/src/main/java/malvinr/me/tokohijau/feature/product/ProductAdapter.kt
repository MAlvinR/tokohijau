package malvinr.me.tokohijau.feature.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_product.view.*
import malvinr.me.tokohijau.R
import malvinr.me.tokohijau.data.ProductEntity

class ProductAdapter(
        private val products: List<ProductEntity>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
            ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false))

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindView(products[holder.adapterPosition])
    }


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(product: ProductEntity) = with(itemView) {
            tv_product_title.text = product.name
            tv_product_seller.text = product.shop?.name
            tv_product_price.text = product.price
        }
    }
}