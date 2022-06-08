package com.example.stuffy.core.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.stuffy.core.databinding.ConfirmationListBinding

import com.example.stuffy.core.domain.model.ConfirmationTransaction

import com.example.stuffy.core.utils.ConfirmationDiffCallback


class ConfirmationAdapter :
    RecyclerView.Adapter<ConfirmationAdapter.ListViewHolder>() {

    private var filter = ArrayList<ConfirmationTransaction>()
    var onItemClick: ((ConfirmationTransaction) -> Unit)? = null
    var onButtonClick: ((ConfirmationTransaction) -> Unit)? = null
    fun setData(newListData: List<ConfirmationTransaction>) {
        val diffCallback = ConfirmationDiffCallback(filter, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        filter.clear()
        filter.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }
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

                more.visibility= if(      filter.size.toInt()==0) View.GONE else View.VISIBLE

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