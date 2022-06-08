package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.R
import com.example.stuffy.core.databinding.ShareListBinding


import com.example.stuffy.core.domain.model.Share
import com.example.stuffy.core.domain.model.Take

import com.example.stuffy.core.utils.ShareDiffCallback

class ShareAdapter :
    RecyclerView.Adapter<ShareAdapter.ListViewHolder>() {

    private var favorite= ArrayList<Share>()
    var onItemClick: ((Share) -> Unit)? = null
    var onButtonAmbilClick: ((Share) -> Unit)? = null
    fun setData(newListData: List<Share>) {
        val diffCallback = ShareDiffCallback(favorite, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        favorite.clear()
        favorite.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ShareListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = favorite[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = favorite.size
    inner class ListViewHolder(var binding: ShareListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: Share) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .into(imageView2)
                textView2.text = filter.name
                textView3.text =if(filter.taker?.filter {
                        it.status == "Selesai"
                    }?.joinToString { it.name }.equals(""))  "" else StringBuilder().append("Diberikan kepada ").append(filter.taker?.filter {
                    it.status == "Selesai"
                }?.joinToString { it.name })
                textView4.text = filter.status
                textView5.text = filter.location
                if (filter.status == "Akan diambil"){
                  ambil.visibility = View.VISIBLE
                }
                when (filter.status) {
                    "Menunggu" -> {
                        textView4.setBackgroundResource(R.drawable.bg_status_warning)
                    }
                    "Selesai" -> {
                        textView4.setBackgroundResource(R.drawable.bg_status_success)
                    }
                    "Ditolak" -> {
                        textView4.setBackgroundResource(R.drawable.bg_status_danger)
                    }
                    "Akan diambil" -> {
                        textView4.setBackgroundResource(R.drawable.bg_status_info)
                    }
                }

            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(favorite[absoluteAdapterPosition])
            }
            binding.ambil.setOnClickListener {
                onButtonAmbilClick?.invoke(favorite[absoluteAdapterPosition])
            }
        }
    }



}