package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.databinding.ItemRowUserBinding
import com.example.stuffy.core.utils.ListProductDiffCallback


class ListProductAdapter :
    RecyclerView.Adapter<ListProductAdapter.ListViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null
    private var listUser = ArrayList<Product>()

    fun setData(newListData: List<Product>) {
        val diffCallback = ListProductDiffCallback(listUser, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listUser.clear()
        listUser.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)

    }

    override fun getItemCount(): Int = listUser.size
    inner class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
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

