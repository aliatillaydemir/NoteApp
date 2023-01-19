package com.ayd.noteapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayd.noteapp.R
import com.ayd.noteapp.databinding.FragmentNoteListBinding
import com.ayd.noteapp.framework.ListViewModel


class NoteList : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

    private val noteListAdapter = NoteAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteRecyclerView.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        binding.addButton.setOnClickListener { goToDetails() }

        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.note.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visibility = View.GONE
            binding.noteRecyclerView.visibility = View.VISIBLE
            noteListAdapter.updateNotes(it.sortedByDescending { it.updateTime })
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToDetails(id: Long = 0L){
        val action = NoteListDirections.actionNoteListToNoteFragment(id)
        Navigation.findNavController(binding.noteRecyclerView).navigate(action)
    }



}