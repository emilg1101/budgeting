package com.github.emilg1101.budgeting.emojipicker.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.emilg1101.budgeting.R
import com.github.emilg1101.budgeting.emojipicker.OnEmojiPickerListener
import com.github.emilg1101.budgeting.emojipicker.model.EmojiModel
import kotlinx.android.synthetic.main.item_emoji.view.*

class EmojiAdapter : RecyclerView.Adapter<EmojiAdapter.EmojiViewHolder>() {

    var items = listOf<EmojiModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onEmojiPickerListener: OnEmojiPickerListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_emoji, parent, false).let {
            EmojiViewHolder(it)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EmojiViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class EmojiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: EmojiModel) = with(itemView) {
            emoji.text = model.emoji
            emoji.setOnClickListener { onEmojiPickerListener?.onEmojiPicked(model) }
        }
    }
}
