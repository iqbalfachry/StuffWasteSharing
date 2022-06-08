package com.example.stuffy.core.ui


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.R
import com.example.stuffy.core.databinding.TakeListBinding

import com.example.stuffy.core.domain.model.Take

import com.example.stuffy.core.utils.TakeDiffCallback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class TakeAdapter :
    RecyclerView.Adapter<TakeAdapter.ListViewHolder>() {

    private var favorite= ArrayList<Take>()

    var onItemClick: ((Take) -> Unit)? = null
    var onButtonRatingClick: ((Take) -> Unit)? = null
    var onButtonAmbilClick: ((Take) -> Unit)? = null
    fun setData(newListData: List<Take>) {
        val diffCallback = TakeDiffCallback(favorite, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        favorite.clear()
        favorite.addAll(newListData)

        diffResult.dispatchUpdatesTo(this)
    }
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
        private  var auth: FirebaseAuth= Firebase.auth

        fun bind(filter: Take) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .into(imageView2)
                textView2.text = filter.name
                textView3.text = filter.location
                textView4.text = filter.status?.filter { it.email==auth.currentUser?.email }?.joinToString {
                    it.status
                }
                filter.status?.map {
                    if (it.status == "Selesai") {
                        ambil.visibility = View.GONE
                    rating.visibility = View.VISIBLE

                } else if(it.status == "Terkonfirmasi"){
                        rating.visibility = View.GONE
                        ambil.visibility = View.VISIBLE

                    }
                    when (it.status) {
                        "Menunggu" -> {
                            textView4.setBackgroundResource(R.drawable.bg_status_warning)
                        }
                        "Selesai" -> {
                            textView4.setBackgroundResource(R.drawable.bg_status_success)
                        }
                        "Ditolak" -> {
                            textView4.setBackgroundResource(R.drawable.bg_status_danger)
                        }
                        "Terkonfirmasi" -> {
                            textView4.setBackgroundResource(R.drawable.bg_status_info)
                        }
                    }
                }
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(favorite[absoluteAdapterPosition])
            }
            binding.rating.setOnClickListener {
                onButtonRatingClick?.invoke(favorite[absoluteAdapterPosition])
            }
            binding.ambil.setOnClickListener {
                onButtonAmbilClick?.invoke(favorite[absoluteAdapterPosition])
            }
        }
    }



}