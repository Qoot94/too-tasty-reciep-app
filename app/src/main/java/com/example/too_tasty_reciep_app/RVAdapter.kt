package com.example.too_tasty_reciep_app

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.too_tasty_reciep_app.databinding.ItemRowBindingBinding


class RecipeCard(title: String, author: String, ing: String, inst: String) {
    val title = title
    val author = author
    val ingredients = ing
    val instructions = inst
}
class RVAdapter(private val container: ArrayList<RecipeCard>
) :
RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBindingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBindingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = container[position]
        holder.binding.apply {
            tvAuthor.text = item.author
            tvTitle.text = item.title
            tvIng.text = item.ingredients
            tvInst.text = item.instructions

            holder.itemView.setOnClickListener {
            }
        }


    }

    override fun getItemCount() = container.size
}