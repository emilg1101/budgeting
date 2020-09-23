package com.github.emilg1101.budgeting.widget.accounts.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.core.toCurrency
import com.github.emilg1101.budgeting.domain.entity.Account
import com.github.emilg1101.budgeting.widget.accounts.R
import kotlinx.android.synthetic.main.item_account.view.*
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<Account>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onAddClick: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            0 -> AccountViewHolder(inflater.inflate(R.layout.item_account, parent, false))
            else -> AccountAddViewHolder(inflater.inflate(R.layout.item_account_add, parent, false))
        }
    }

    override fun getItemCount(): Int = items.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AccountViewHolder -> holder.bind(items[position])
            is AccountAddViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != items.size)  {
            0
        } else {
            1
        }
    }

    class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Account) = with(itemView) {
            accountName.text = model.name
            accountBalance.text = model.amount.toCurrency()
        }
    }

    inner class AccountAddViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind() = with(itemView) {
            accountName.text = "Add account"
            setOnClickListener {
                onAddClick?.invoke()
            }
        }
    }
}
