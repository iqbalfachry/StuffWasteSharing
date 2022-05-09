package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.databinding.FilterColBinding


class FilterAdapter(private val filter: ArrayList<Filter>) :
RecyclerView.Adapter<FilterAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = FilterColBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val ( image,filterName) = filter[position]
        Glide.with(holder.itemView.context)
            .load(image)
            .circleCrop()
            .into(holder.binding.imageView)
        holder.binding.textView.text = filterName
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(filter[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = filter.size
    class ListViewHolder(var binding: FilterColBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Filter)
    }


}