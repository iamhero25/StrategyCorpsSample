package com.strategycorps.medlite.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.strategycorps.medlite.data.entities.CategoryEntity
import com.strategycorps.medlite.R
import com.strategycorps.medlite.databinding.ItemCategoryBinding
import com.strategycorps.medlite.models.CategoryModel
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val listener: OnCategorySelectListener): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList: List<CategoryModel> = emptyList()
    var selectedItem: Int = -1

    class CategoryViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {

        companion object{
            fun from(parent: ViewGroup): CategoryAdapter.CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
                return CategoryAdapter.CategoryViewHolder(binding)
            }
        }

        fun bind(category: CategoryModel){
            binding.apply {

                tvCategoryName.text = category.name
                ivCategoryImage.load(category.image){
                    placeholder(R.drawable.ic_circle)
                    error(R.drawable.ic_circle)

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = categoryList[position]

        holder.itemView.setOnClickListener {
            listener.onCategorySelectListener(currentCategory)
            selectedItem = position
            notifyDataSetChanged()
        }

        when (position) {
            selectedItem -> {
                holder.itemView.vw_selected.setBackgroundColor(Color.parseColor("#0288D1"))
            }
            else -> {
                holder.itemView.vw_selected.setBackgroundColor(Color.parseColor("#F4F9FF"))
            }
        }

        holder.bind(currentCategory)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<CategoryEntity>){
        categoryList = categories.map {
            CategoryModel(
                it.id,
                it.name,
                it.image
            )
        }
        notifyDataSetChanged()
    }
}

interface OnCategorySelectListener{
    fun onCategorySelectListener(category: CategoryModel)
}