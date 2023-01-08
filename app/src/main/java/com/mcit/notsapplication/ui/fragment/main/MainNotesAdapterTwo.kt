package com.mcit.notsapplication.ui.fragment.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mcit.notsapplication.databinding.NotesBinding
import com.mcit.notsapplication.models.NotesModel

class MainNotesAdapterTwo(
    private val onclickListener: ((NotesModel) -> Unit),
    private val onclickListenerUpdate: ((NotesModel) -> Unit)
) : ListAdapter<NotesModel, MainNotesAdapterTwo.ViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<NotesModel>() {
            override fun areItemsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NotesModel, newItem: NotesModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    fun getItemIdByPosition(position: Int): Int  = getItem(position).id

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = NotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(val binding: NotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NotesModel) {
            binding.note = item
            binding.root.setOnClickListener {
                onclickListener(item)
            }

            binding.notesIcon.setOnClickListener {
                onclickListenerUpdate(item)
            }
        }
    }
}