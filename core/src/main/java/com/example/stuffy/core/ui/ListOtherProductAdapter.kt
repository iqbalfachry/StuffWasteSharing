package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.databinding.ItemOtherUserBinding

import com.example.stuffy.core.domain.model.Product



class ListOtherProductAdapter(private val listUser: ArrayList<Product>) :
    RecyclerView.Adapter<ListOtherProductAdapter.ListViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemOtherUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }

    override fun getItemCount(): Int = listUser.size
    inner class ListViewHolder(var binding: ItemOtherUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user:Product) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(user.avatar)

                    .into(imgItemPhoto)
                tvItemName.text = user.name
                tvItemDescription.text = user.location
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listUser[absoluteAdapterPosition])
            }
        }
    }



}

