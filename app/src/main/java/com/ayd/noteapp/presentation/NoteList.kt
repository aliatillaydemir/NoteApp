package com.ayd.noteapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ayd.noteapp.R
import com.ayd.noteapp.databinding.FragmentNoteListBinding


class NoteList : Fragment() {

    private var _binding: FragmentNoteListBinding? = null
    private val binding get() = _binding!!

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

        binding.addButton.setOnClickListener { goToDetails() }
    }

    private fun goToDetails(id: Long = 0L){
        val action = NoteListDirections.actionNoteListToNoteFragment(id)
        Navigation.findNavController(binding.noteRecyclerView).navigate(action)
    }



}