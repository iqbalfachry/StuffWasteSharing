package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.domain.model.Filter
import com.example.stuffy.core.databinding.FilterColBinding


class FilterAdapter(private val filter: ArrayList<Filter>) :
RecyclerView.Adapter<FilterAdapter.ListViewHolder>() {


    var onItemClick: ((Filter) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = FilterColBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = filter[position]
holder.bind(filter)

    }

    override fun getItemCount(): Int = filter.size
    inner class ListViewHolder(var binding: FilterColBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter:Filter) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .circleCrop()
                    .into(imageView)
                textView.text = filter.filterName

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(filter[absoluteAdapterPosition])
            }
        }
    }



}