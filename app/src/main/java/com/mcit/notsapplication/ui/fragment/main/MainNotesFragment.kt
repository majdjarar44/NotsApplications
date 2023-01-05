package com.mcit.notsapplication.ui.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mcit.notsapplication.R
import com.mcit.notsapplication.databinding.FragmentMainNotesBinding
import com.mcit.notsapplication.global.BaseFragment
import com.mcit.notsapplication.global.StatusFragment
import com.mcit.notsapplication.global.SwipeToDeleteCallback
import com.mcit.notsapplication.models.NotesModel
import com.mcit.notsapplication.ui.fragment.write.WriteNotesFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class MainNotesFragment : BaseFragment() {

    lateinit var binding: FragmentMainNotesBinding
    lateinit var adapter: MainNotesAdapter

    val viewModel: MainViewModel by activityViewModels()

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentMainNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialRecyclerView()
        initialAddNotesListener()
    }

    private fun initialAddNotesListener() {
        binding.fabAdd.setOnClickListener {
            viewModel.notes = NotesModel(0, "", "")
            findNavController().navigate(
                R.id.action_mainNotesFragment_to_writeNotesFragment,
                bundleOf(Pair(WriteNotesFragment.STATUS, StatusFragment.ADD))
            )
        }
    }


    private fun initialRecyclerView() {
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainNotesAdapter(requireContext(), { model, position ->
            viewNote(model)
        }, { model, position ->
            updateNote(model)
        })
        binding.rvNotes.adapter = adapter
        deleteItem()
        viewModel.getsUserNotes.observe(viewLifecycleOwner, {
            adapter.setNotes((it.data as List<NotesModel>).toMutableList())
            if (it.data.isNotEmpty()){
                binding.emptyList.root.visibility = View.GONE
            }else{
                binding.emptyList.root.visibility = View.VISIBLE
            }
        })
    }

    private fun deleteItem() {
        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteNote(adapter.getNotes()[viewHolder.adapterPosition].id)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun viewNote(note: NotesModel) {
        viewModel.notes = note
        findNavController().navigate(
            R.id.action_mainNotesFragment_to_writeNotesFragment,
            bundleOf(Pair(WriteNotesFragment.STATUS, StatusFragment.SHOW))
        )
    }

    private fun updateNote(note: NotesModel) {
        viewModel.notes = note
        findNavController().navigate(
            R.id.action_mainNotesFragment_to_writeNotesFragment,
            bundleOf(Pair(WriteNotesFragment.STATUS, StatusFragment.UPDATE))
        )
    }

}