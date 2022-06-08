package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.stuffy.core.databinding.WishlistListBinding
import com.example.stuffy.core.domain.model.Favorite



class FavoriteAdapter(private val favorite: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.ListViewHolder>() {


    var onItemClick: ((Favorite) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = WishlistListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = favorite[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = favorite.size
    inner class ListViewHolder(var binding: WishlistListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: Favorite) {
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
                onItemClick?.invoke(favorite[absoluteAdapterPosition])
            }
        }
    }



}