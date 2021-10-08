package com.example.marvelhub.presentation.HomeScreen.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.marvelhub.databinding.HeroItemBinding
import com.example.marvelhub.domain.model.Character
import com.example.marvelhub.utils.setImage
import com.example.marvelhub.utils.startBlur

class CharacterAdapter(private val characters: ArrayList<Character>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val binding = HeroItemBinding.inflate(inflater,parent,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CharacterViewHolder -> {
                holder.bind(characters.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    fun submitList(list: List<com.example.marvelhub.domain.model.Character>) {
            val diffCallback = CharacterCallback(characters, list)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            characters.addAll(list)
            diffResult.dispatchUpdatesTo(this)
    }


    class CharacterViewHolder
    constructor(
        private val binding : HeroItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: com.example.marvelhub.domain.model.Character) {
            binding.heroImg.setImage(character.imagePath)
            binding.heroName.text = character.name
        }
    }



}
class CharacterCallback(private val oldList: List<Character>, private val newList: List<Character>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id=== newList.get(newItemPosition).id
    }
    override fun areContentsTheSame(oldCourse: Int, newPosition: Int): Boolean {
        return oldList[oldCourse].name == newList[newPosition].name && oldList[oldCourse].id == newList[newPosition].id
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}