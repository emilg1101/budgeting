package com.github.emilg1101.budgeting.wallets.accounts.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.core.toCurrency
import com.github.emilg1101.budgeting.domain.entity.Account
import com.github.emilg1101.budgeting.wallets.R
import kotlinx.android.synthetic.main.item_account.view.*
import javax.inject.Inject

class AccountAdapter @Inject constructor() :
    RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    var items = listOf<Account>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_account, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class AccountViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(model: Account) = with(itemView) {
            accountName.text = model.name
            accountAmount.text = model.amount.toCurrency()
        }
    }
}
