package com.example.quotevault.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quotevault.databinding.QuoteItemBinding
import com.example.quotevault.model.Quote

class QuoteAdapter(
    private var favouriteIds: Set<Int>,
    private val onFavClick: (Int) -> Unit
) : ListAdapter<Quote, QuoteAdapter.QuoteViewHolder>(DiffCallback) {

    fun updateFavourites(newFavs: Set<Int>) {
        favouriteIds = newFavs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = QuoteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class QuoteViewHolder(
        private val binding: QuoteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: Quote) {
            binding.tvQuote.text = quote.text
            binding.tvAuthor.text = "- ${quote.author}"


            binding.imgFav.visibility =
                if (favouriteIds.contains(quote.id)) View.GONE else View.VISIBLE

            binding.imgFav.setOnClickListener {
                onFavClick(quote.id)
            }


            binding.imgShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "\"${quote.text}\"\n- ${quote.author}"
                    )
                }
                binding.root.context.startActivity(
                    Intent.createChooser(intent, "Share Quote")
                )
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(old: Quote, new: Quote) = old.id == new.id
            override fun areContentsTheSame(old: Quote, new: Quote) = old == new
        }
    }
}
