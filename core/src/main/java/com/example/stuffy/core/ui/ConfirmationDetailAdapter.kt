package com.example.stuffy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stuffy.core.databinding.ConfirmationTakerListBinding
import com.example.stuffy.core.domain.model.ConfirmationTaker

import com.example.stuffy.core.utils.ConfirmationDetailDiffCallback


class ConfirmationDetailAdapter  :
    RecyclerView.Adapter<ConfirmationDetailAdapter.ListViewHolder>() {

    private var filter= ArrayList<ConfirmationTaker>()
    var onItemClick: ((ConfirmationTaker) -> Unit)? = null
    var onButtonRejectClick: ((ConfirmationTaker) -> Unit)? = null
    var onButtonAcceptClick: ((ConfirmationTaker) -> Unit)? = null
    fun setData(newListData: List<ConfirmationTaker>) {
        val diffCallback = ConfirmationDetailDiffCallback(filter, newListData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        filter.clear()
        filter.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }
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
        fun bind(filter: ConfirmationTaker) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(filter.image)
                    .circleCrop()
                    .into(imageView5)
                textView10.text = filter.name
                textView11.text = filter.note
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(filter[absoluteAdapterPosition])
            }
            binding.reject.setOnClickListener {
                onButtonRejectClick?.invoke(filter[absoluteAdapterPosition])
            }
            binding.more.setOnClickListener {
                onButtonAcceptClick?.invoke(filter[absoluteAdapterPosition])
            }
        }
    }



}