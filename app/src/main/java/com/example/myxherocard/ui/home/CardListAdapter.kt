package com.example.myxherocard.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myxherocard.R
import com.example.myxherocard.databinding.CardItemBinding
import com.example.myxherocard.model.Card

class CardListAdapter(val cardClickListener: AdapterClickListener,
                      val cardFavoriteClickListener: AdapterClickListener)
    : ListAdapter<Card, CardListAdapter.CardViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

    }
    inner class CardViewHolder(val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

    class AdapterClickListener (val clickListener: (card: Card) -> Unit ) {
        fun onClick(card: Card) = clickListener(card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent,
            false)

        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val currentCard = getItem(position)
        holder.binding.card = currentCard
        holder.binding.cardHeader.setOnClickListener {
            cardClickListener.onClick(currentCard)
        }

        holder.binding.cardFavorite.setOnClickListener {
            cardFavoriteClickListener.onClick(currentCard)
        }

        val drawableId = if(currentCard.isFavorite == 1) R.drawable.ic_star_on else R.drawable.ic_start_off
        holder.binding.cardFavorite.setImageDrawable(
            AppCompatResources.getDrawable(holder.itemView.context, drawableId)
        )


        Glide.with(holder.itemView.context)
            .load(currentCard.cropImage)
            .error(R.drawable.ic_launcher_foreground)
            .apply(RequestOptions().centerCrop())
            .into(holder.binding.cardHeader)
    }

}