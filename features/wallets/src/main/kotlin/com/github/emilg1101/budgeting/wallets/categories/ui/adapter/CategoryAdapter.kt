package com.github.emilg1101.budgeting.wallets.categories.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.domain.entity.Category
import com.github.emilg1101.budgeting.wallets.R
import kotlinx.android.synthetic.main.item_category.view.*
import javax.inject.Inject

class CategoryAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var items = listOf<Category>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Category) = with(itemView) {
            categoryName.text = model.name
            categoryEmoji.text = model.emoji
        }
    }
}
