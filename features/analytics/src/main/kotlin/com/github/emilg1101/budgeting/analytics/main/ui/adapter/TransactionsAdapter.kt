package com.github.emilg1101.budgeting.analytics.main.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.analytics.R
import com.github.emilg1101.budgeting.analytics.main.model.TransactionItem
import com.github.emilg1101.budgeting.core.format
import com.github.emilg1101.budgeting.core.toCurrency
import kotlinx.android.synthetic.main.item_transaction.view.*
import kotlinx.android.synthetic.main.item_transaction_header.view.*
import org.threeten.bp.format.DateTimeFormatter

class TransactionsAdapter(private val formatter: DateTimeFormatter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<TransactionItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TRANSACTION_MODEL -> LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
                .let {
                    TransactionViewHolder(it)
                }
            else -> LayoutInflater.from(parent.context).inflate(R.layout.item_transaction_header, parent, false)
                .let {
                    TransactionHeaderViewHolder(it)
                }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TransactionViewHolder -> holder.bind(items[position] as TransactionItem.TransactionModel)
            is TransactionHeaderViewHolder -> holder.bind(items[position] as TransactionItem.TransactionHeader)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is TransactionItem.TransactionModel -> TRANSACTION_MODEL
            is TransactionItem.TransactionHeader -> TRANSACTION_HEADER
        }

    inner class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: TransactionItem.TransactionModel) = with(itemView) {
            transactionEmoji.text = model.toEmoji
            transactionFrom.text = model.from
            transactionTo.text = model.to
            transactionAmount.text = model.amount.toCurrency()
        }
    }


    inner class TransactionHeaderViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        fun bind(header: TransactionItem.TransactionHeader) = with(itemView) {
            transactionHeaderDate.text = formatter.format(header.date)
        }
    }

    companion object {
        private const val TRANSACTION_MODEL = 0
        private const val TRANSACTION_HEADER = 1
    }
}
