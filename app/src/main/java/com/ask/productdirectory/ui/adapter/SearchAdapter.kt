package com.ask.productdirectory.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ask.productdirectory.R
import com.ask.productdirectory.databinding.ItemResultSearchBinding

import com.ask.productdirectory.domain.entity.Ingredient
import kotlin.random.Random

class SearchAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<SearchViewHolder>() {

    private var listItem = emptyList<Ingredient>()

    fun settingAdapter(list: List<Ingredient>) {
        this.listItem = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(ItemResultSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount() = listItem.size
}

class SearchViewHolder(private val vb: ItemResultSearchBinding,private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(vb.root) {

    fun bind(item: Ingredient) {
        vb.itemContainer.apply {
        setCardBackgroundColor(getRandomColor())
        }
        vb.nameResultSearch.text = item.name
        val kcal = itemView.context.resources.getString(R.string.kcal)
        vb.kcalItem.text = item.component[0].value.toString() + " " + kcal
        itemView.setOnClickListener {
            onClick(item.id)
        }
    }

    private fun getRandomColor(): Int {
       val color = when (Random.nextInt(0,5)){
            0 -> R.color.item_color_1
            1 -> R.color.item_color_2
            2 -> R.color.item_color_3
            3 -> R.color.item_color_4
            else -> R.color.item_color_5
        }
        return vb.root.context.getColor(color)
    }
}