package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.databinding.CartListBinding


import com.example.stuffy.core.domain.model.Cart


class CartAdapter(private val filter: ArrayList<Cart>) :
    RecyclerView.Adapter<CartAdapter.ListViewHolder>() {


    var onItemClick: ((Cart) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = CartListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = filter[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = filter.size
    inner class ListViewHolder(var binding: CartListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: Cart) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .into(imageView2)
                textView2.text = filter.name
                textView3.text = filter.location
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(filter[absoluteAdapterPosition])
            }
        }
    }



}