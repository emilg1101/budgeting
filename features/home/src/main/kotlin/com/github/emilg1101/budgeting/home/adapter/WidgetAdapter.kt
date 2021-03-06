package com.github.emilg1101.budgeting.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.emilg1101.budgeting.core.view.adapter.LifecycleRecyclerAdapter
import com.github.emilg1101.budgeting.core.view.adapter.LifecycleViewHolder
import com.github.emilg1101.budgeting.widget.core.Widget
import javax.inject.Inject

class WidgetAdapter @Inject constructor() : LifecycleRecyclerAdapter<LifecycleViewHolder>() {

    var widgets = listOf<Widget<*>>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = widgets.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LifecycleViewHolder {
        return LayoutInflater.from(parent.context).inflate(widgets[viewType].widgetLayout, parent, false)
            .let {
                widgets[viewType].viewHolderClass.getConstructor(View::class.java).newInstance(it)
            }
    }

    override fun onBindViewHolder(holder: LifecycleViewHolder, position: Int) {
        //holder.bind()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
