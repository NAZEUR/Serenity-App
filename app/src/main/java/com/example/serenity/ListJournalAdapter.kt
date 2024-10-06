package com.example.serenity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListJournalAdapter(
    private val listJournal: ArrayList<Journal>,
    private val onItemClick: (Journal) -> Unit
) : RecyclerView.Adapter<ListJournalAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val articleImage: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val articleTitle: TextView = itemView.findViewById(R.id.tv_item_name)
        private val articleOverview: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(journal: Journal, onItemClick: (Journal) -> Unit) {
            articleImage.setImageResource(journal.photo)
            articleTitle.text = journal.name
            articleOverview.text = journal.description

            itemView.setOnClickListener { onItemClick(journal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_journal_layout, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = listJournal[position]
        holder.bind(currentItem, onItemClick)
    }

    override fun getItemCount(): Int = listJournal.size
}

