package com.github.emilg1101.budgeting.transaction.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.core.toCurrency
import com.github.emilg1101.budgeting.transaction.R
import com.github.emilg1101.budgeting.transaction.ui.model.Account
import com.github.emilg1101.budgeting.transaction.ui.model.BaseCategory
import com.github.emilg1101.budgeting.transaction.ui.model.Category
import com.github.emilg1101.budgeting.transaction.ui.model.Income
import kotlinx.android.synthetic.main.item_account.view.*
import kotlinx.android.synthetic.main.item_category.view.*
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<BaseCategory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClick: ((BaseCategory) -> Unit)? = null

    private var enabledPosition: Int? = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            CATEGORY_VIEW_TYPE -> {
                CategoryViewHolder(inflater.inflate(R.layout.item_category, parent, false))
            }
            ACCOUNT_VIEW_TYPE -> {
                AccountViewHolder(inflater.inflate(R.layout.item_account, parent, false))
            }
            else -> {
                IncomeViewHolder(inflater.inflate(R.layout.item_account, parent, false))
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> holder.bind(items[position] as Category, position)
            is AccountViewHolder -> holder.bind(items[position] as Account, position)
            is IncomeViewHolder -> holder.bind(items[position] as Income, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is Category -> CATEGORY_VIEW_TYPE
            is Account -> ACCOUNT_VIEW_TYPE
            else -> INCOME_VIEW_TYPE
        }
    }

    companion object {
        private const val CATEGORY_VIEW_TYPE = 0
        private const val ACCOUNT_VIEW_TYPE = 1
        private const val INCOME_VIEW_TYPE = 2
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Category, position: Int) = with(itemView) {
            isEnabled = model.enabled
            categoryEmoji.text = model.emoji
            categoryName.text = model.name
            setOnClickListener {
                isEnabled = false
                onItemClick?.invoke(model)
                enabledPosition?.let {
                    items[it].enabled = true
                    notifyItemChanged(it)
                }
                model.enabled = false
                enabledPosition = position
            }
        }
    }

    inner class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Account, position: Int) = with(itemView) {
            isEnabled = model.enabled
            accountAmount.text = model.amount.toCurrency()
            accountName.text = model.name
            setOnClickListener {
                isEnabled = false
                onItemClick?.invoke(model)
                enabledPosition?.let {
                    items[it].enabled = true
                    notifyItemChanged(it)
                }
                model.enabled = false
                enabledPosition = position
            }
        }
    }

    inner class IncomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Income, position: Int) = with(itemView) {
            isEnabled = model.enabled
            accountAmount.isVisible = false
            accountName.text = model.name
            setOnClickListener {
                isEnabled = false
                onItemClick?.invoke(model)
                enabledPosition?.let {
                    items[it].enabled = true
                    notifyItemChanged(it)
                }
                model.enabled = false
                enabledPosition = position
            }
        }
    }
}
