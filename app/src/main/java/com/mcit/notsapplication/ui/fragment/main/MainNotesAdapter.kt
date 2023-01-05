package com.mcit.notsapplication.ui.fragment.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mcit.notsapplication.R
import com.mcit.notsapplication.databinding.NotesBinding
import com.mcit.notsapplication.global.BaseBindingViewHolder
import com.mcit.notsapplication.models.NotesModel


class MainNotesAdapter(
    private val context: Context,
    private val onclickListener: ((NotesModel, Int) -> Unit),
    private val onclickListenerUpdate: ((NotesModel, Int) -> Unit)
) : RecyclerView.Adapter<MainNotesAdapter.ViewHolder>() {

    private var list = mutableListOf<NotesModel>()

    fun getNotes(): MutableList<NotesModel> = list

    fun setNotes(items: MutableList<NotesModel>) {
        list = items
        notifyDataSetChanged()
    }

    fun addNote(item: NotesModel) {
        list.add(item)
        notifyDataSetChanged()
    }

    fun updateNote(item: NotesModel, position: Int) {
        list[position] = item
        notifyDataSetChanged()
    }

    fun deleteNote(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = NotesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, list[position])
    }


    inner class ViewHolder(val binding: NotesBinding) :
        BaseBindingViewHolder<NotesModel>(binding) {

        override fun bind(position: Int, item: NotesModel?) {
            binding.note = item
            binding.root.setOnClickListener {
                item?.let { it1 -> onclickListener(it1, position) }
            }

            binding.notesIcon.setOnClickListener {
                item?.let { it1 -> onclickListenerUpdate(it1, position) }
            }
        }
    }


}