package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.databinding.TakeListBinding
import com.example.stuffy.core.databinding.WishlistListBinding
import com.example.stuffy.core.domain.model.ConfirmationTransaction
import com.example.stuffy.core.domain.model.Share
import com.example.stuffy.core.domain.model.Take

class TakeAdapter  (private val favorite: ArrayList<Take>) :
    RecyclerView.Adapter<TakeAdapter.ListViewHolder>() {


    var onItemClick: ((Take) -> Unit)? = null
    var onButtonClick: ((Take) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = TakeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val filter = favorite[position]
        holder.bind(filter)

    }

    override fun getItemCount(): Int = favorite.size
    inner class ListViewHolder(var binding: TakeListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(filter: Take) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .into(imageView2)
                textView2.text = filter.name
                textView3.text = filter.location
                textView4.text = filter.status
                if(filter.status.equals("Selesai")) {
                  rating.visibility = View.VISIBLE
                    }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(favorite[absoluteAdapterPosition])
            }
            binding.rating.setOnClickListener {
                onButtonClick?.invoke(favorite[absoluteAdapterPosition])
            }
        }
    }



}