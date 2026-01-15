package com.example.quotevault.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quotevault.R
import com.example.quotevault.databinding.CategoryItemBinding
import com.example.quotevault.model.Category

class CategoryAdapter(
    private val onClick: (Category) -> Unit
) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DIFF) {

    private var selectedCategoryId: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvCategory = itemView.findViewById<TextView>(R.id.categoryText)

        fun bind(category: Category) {
            tvCategory.text = category.name

            val isSelected = category.id == selectedCategoryId

            tvCategory.background = ContextCompat.getDrawable(
                itemView.context,
                if (isSelected)
                    R.drawable.bg_category_selected
                else
                    R.drawable.bg_category_unselected
            )

            tvCategory.setTextColor(
                if (isSelected) Color.WHITE else Color.DKGRAY
            )

            itemView.setOnClickListener {
                selectedCategoryId = category.id
                notifyDataSetChanged()
                onClick(category)
            }
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(old: Category, new: Category) =
                old.id == new.id

            override fun areContentsTheSame(old: Category, new: Category) =
                old == new
        }
    }
}
