package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.databinding.ConfirmationTakerListBinding
import com.example.stuffy.core.domain.model.ConfirmationTransaction

class ConfirmationDetailAdapter (private val filter: ArrayList<ConfirmationTransaction>) :
    RecyclerView.Adapter<ConfirmationDetailAdapter.ListViewHolder>() {


    var onItemClick: ((ConfirmationTransaction) -> Unit)? = null
    var onButtonClick: ((ConfirmationTransaction) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ConfirmationTakerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = filter[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = filter.size
    inner class ListViewHolder(var binding: ConfirmationTakerListBinding) : RecyclerView.ViewHolder(binding.root){
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