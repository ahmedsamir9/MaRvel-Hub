package com.example.marvelhub.presentation.HomeScreen.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelhub.databinding.HeroItemBinding
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.utils.setImage

class CharacterPagingAdapter(private val onClickListener:OnClickListenerOnCharacterItem): PagingDataAdapter<Character, CharacterPagingAdapter.CharacterPagingViewHolder>(
DiffUtils
) {
    class CharacterPagingViewHolder(private val binding :HeroItemBinding,private val onClickListener:OnClickListenerOnCharacterItem) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item: Character){
            binding.heroImg.setImage(item.imagePath)
            binding.heroName.text = item.name
            binding.root.setOnClickListener {
                onClickListener.onClickOnCharacter(item.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterPagingViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val binding = HeroItemBinding.inflate(inflater,parent,false)
        return CharacterPagingViewHolder(binding,onClickListener)
    }
    companion object{
        val DiffUtils = object : DiffUtil.ItemCallback<Character>(){
            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                return  oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return  oldItem == newItem
            }

        }

    }

    override fun onBindViewHolder(holder: CharacterPagingViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }
    interface OnClickListenerOnCharacterItem{
        fun onClickOnCharacter(characterId:Int)
    }

}