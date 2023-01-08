package com.mcit.notsapplication.ui.fragment.write

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mcit.notsapplication.R
import com.mcit.notsapplication.databinding.FragmentWriteNotesBinding
import com.mcit.notsapplication.global.BaseFragment
import com.mcit.notsapplication.global.StatusFragment
import com.mcit.notsapplication.models.NotesModel
import com.mcit.notsapplication.ui.fragment.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class WriteNotesFragment : BaseFragment() {
    val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentWriteNotesBinding
    var title: String = ""
    var note: String = ""

    companion object {
        const val STATUS = "using"
    }

    override fun layoutResource(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentWriteNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.note = viewModel

        var usingType = arguments?.get(STATUS) as StatusFragment
        when (usingType) {
            StatusFragment.SHOW -> {
                binding.saveBtn.visibility = View.GONE
            }
            StatusFragment.ADD -> {
                initialSaveBtn()
            }
            StatusFragment.UPDATE -> {
                initialUpdateBtn()
            }
        }


    }

    private fun initialUpdateBtn() {
        binding.saveBtn.text = getString(R.string.update)
        binding.saveBtn.setOnClickListener {
            viewModel.update(noteObjectAdd())
            doSuccessAction(getString(R.string.message_success_update_note))

        }
    }

    private fun doSuccessAction(message: String) {
        showSuccessMessage(message)
        findNavController().popBackStack()
    }

    fun checkAvailability(): Boolean {
        getData()
        if (title.isNotEmpty()) {
            if (note.isNotEmpty()) {
                return true
            } else {
                showFailedMessage(getString(R.string.message_must_input_note))
                return false
            }
        } else {
            showFailedMessage(getString(R.string.message_must_input_title))
            return false
        }
    }

    fun getData() {
        title = binding.editTextTextPersonName.text.toString()
        note = binding.editTextTextPersonName2.text.toString()
    }

    private fun initialSaveBtn() {
        binding.saveBtn.setOnClickListener {
            if (checkAvailability()) {
                viewModel.addUserNotes(
                    noteObjectAdd()
                )
                doSuccessAction(getString(R.string.message_success_insert_note))
            }
        }
    }

    fun noteObjectAdd(): NotesModel {
        return NotesModel(
            viewModel.notes.id,
            binding.editTextTextPersonName.text.toString(),
            binding.editTextTextPersonName2.text.toString()
        )
    }
}


