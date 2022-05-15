package com.example.stuffy.core.ui

import android.content.Intent
import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.R
import com.example.stuffy.core.databinding.CartListBinding
import com.example.stuffy.core.databinding.ConfirmationListBinding
import com.example.stuffy.core.domain.model.Cart
import com.example.stuffy.core.domain.model.ConfirmationTransaction

class ConfirmationAdapter(private val filter: ArrayList<ConfirmationTransaction>) :
    RecyclerView.Adapter<ConfirmationAdapter.ListViewHolder>() {


    var onItemClick: ((ConfirmationTransaction) -> Unit)? = null
    var onButtonClick: ((ConfirmationTransaction) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ConfirmationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = filter[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = filter.size
    inner class ListViewHolder(var binding: ConfirmationListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: ConfirmationTransaction) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .into(imageView5)
                textView10.text = filter.name
                textView11.text = StringBuilder().append(filter.size).append(" orang ingin ambil barang ini")
                    filter.size

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(filter[absoluteAdapterPosition])
            }
            binding.more.setOnClickListener {
                onButtonClick?.invoke(filter[absoluteAdapterPosition])
            }
        }
    }



}