package com.example.favoritemovie.Base

import androidx.recyclerview.widget.*

abstract class BaseRecyclerViewAdapter<T, Y: RecyclerView.ViewHolder>: RecyclerView.Adapter<Y>() {

    private val listUpdateCallback = object : ListUpdateCallback {
        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyItemRangeChanged(position, count, payload)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }
    }

    private val itemDiffCallback = object : DiffUtil.ItemCallback<T>(){
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return this@BaseRecyclerViewAdapter.areContentsTheSame(oldItem, newItem)
        }

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return this@BaseRecyclerViewAdapter.areItemsTheSame(oldItem, newItem)
        }
    }

    val data = AsyncListDiffer<T>(listUpdateCallback, AsyncDifferConfig.Builder<T>(itemDiffCallback).build())

    protected abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean
    protected abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean
    open fun updateList(items: List<T>) = data.submitList(items)
    open fun updateList(items: List<T>, callback: Runnable) = data.submitList(items.toMutableList(), callback)
    override fun getItemCount(): Int = data.currentList.size
    fun get(index: Int): T = data.currentList[index]
    val items: List<T> get() = data.currentList
    open fun onLoad(){ }
}
