package com.github.emilg1101.budgeting.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.onboarding.R
import com.github.emilg1101.budgeting.onboarding.model.CategoryModel
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var items = listOf<CategoryModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
            .let {
                CategoryViewHolder(it)
            }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: CategoryModel) = with(itemView) {
            categoryCheckBox.isChecked = model.selected
            categoryEmoji.text = model.emoji
            categoryName.text = model.name
            categoryCheckBox.setOnCheckedChangeListener { _, isChecked ->
                model.selected = isChecked
            }
            setOnClickListener {
                model.selected = !model.selected
                categoryCheckBox.isChecked = model.selected
            }
        }
    }
}
