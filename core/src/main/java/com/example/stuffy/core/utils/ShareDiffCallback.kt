package com.example.stuffy.core.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.stuffy.core.domain.model.ConfirmationTaker
import com.example.stuffy.core.domain.model.ConfirmationTransaction

import com.example.stuffy.core.domain.model.Product
import com.example.stuffy.core.domain.model.Share

class ShareDiffCallback(private val mOldList: ArrayList<Share>, private val mNewList: List<Share>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldList.size
    }
    override fun getNewListSize(): Int {
        return mNewList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].id == mNewList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            mOldList[oldItemPosition].id != mNewList[newItemPosition].id->{
                false
            }
            mOldList[oldItemPosition].status != mNewList[newItemPosition].status->{
                false
            }
            else->true
        }
    }
}