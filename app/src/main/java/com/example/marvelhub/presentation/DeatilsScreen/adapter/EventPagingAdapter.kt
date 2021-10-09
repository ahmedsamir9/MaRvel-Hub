package com.example.marvelhub.presentation.DeatilsScreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelhub.databinding.CharacterEventItemBinding
import com.example.marvelhub.domain.model.EventData
import com.example.marvelhub.utils.setImage

class EventPagingAdapter(): PagingDataAdapter<EventData, EventPagingAdapter.EventPagingViewHolder>(
    EVENTDiffUtils
) {
    class EventPagingViewHolder(private val binding :CharacterEventItemBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item: EventData){
                binding.eventImg.setImage(item.EventImagePath)
            binding.eventName.text = item.eventName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventPagingViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val binding = CharacterEventItemBinding.inflate(inflater,parent,false)
        return EventPagingViewHolder(binding)
    }
    companion object{
        val EVENTDiffUtils = object : DiffUtil.ItemCallback<EventData>(){
            override fun areItemsTheSame(oldItem: EventData, newItem: EventData): Boolean {
                return  oldItem.eventName == newItem.eventName
            }

            override fun areContentsTheSame(
                oldItem: EventData,
                newItem: EventData
            ): Boolean {
                return  oldItem == newItem
            }

        }

    }

    override fun onBindViewHolder(holder: EventPagingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }


}