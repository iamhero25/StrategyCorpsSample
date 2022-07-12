package com.strategycorps.medlite.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.strategycorps.medlite.data.entities.ProductEntity
import com.strategycorps.medlite.R
import com.strategycorps.medlite.databinding.ItemProductBinding
import com.strategycorps.medlite.models.Product

class ProductListAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private var productList = emptyList<Product>()

    class ProductListViewHolder(private val binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product){

            binding.apply {
                tvProductName.text = product.title
                tvProductUnit.text = product.unit

                ivProductImage.load(product.image)
                {
                    placeholder(R.drawable.ic_circle)
                    error(R.drawable.no_image)
                }

                if (product.price == "$0"){
                    tvProductPrice.text = product.originalPrice
                    llOldPrice.isVisible = false
                } else {
                    llOldPrice.isVisible = true
                    tvOldPrice.text = product.originalPrice
                    tvProductPrice.text = product.price
                    tvProductPrice.setTextColor(Color.parseColor("#0288D1"))
                }

                llAddToCart.setOnClickListener {
                    llAddToCart.visibility = View.INVISIBLE
                    llUpdateCart.visibility = View.VISIBLE
                }
            }
        }

        companion object{
            fun from(parent: ViewGroup): ProductListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(layoutInflater, parent, false)
                return ProductListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentItem = productList[position]
        holder.bind(currentItem)

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(position, currentItem)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(productEntityList: List<ProductEntity>){

        val products = productEntityList.map {
            Product(
                it.id,
                it.title,
                it.image,
                "$${it.originalPrice}",
                "$${it.price}",
                it.unit,
                it.description,
                it.category
            )
        }

//      val productDiffUtil = ProductListDiffUtil(productList, products)
//      val diffUtilResult = DiffUtil.calculateDiff(productDiffUtil)
        productList = products
//      diffUtilResult.dispatchUpdatesTo(this)

        notifyDataSetChanged()
    }
}

interface OnItemClickListener {
    fun onItemClickListener(position: Int, product: Product)
}