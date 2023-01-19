package com.ayd.noteapp.presentation

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayd.core.data.Note
import com.ayd.noteapp.R
import com.ayd.noteapp.databinding.FragmentNoteListBinding
import com.ayd.noteapp.databinding.ItemNoteLayoutBinding
import java.text.SimpleDateFormat

class NoteAdapter(var notes: ArrayList<Note>): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>){
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNoteLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val layout = binding.noteLayout
        val noteTitle = binding.title
        val noteContent = binding.content
        val noteDate = binding.date
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.noteTitle.text = note.title
        holder.noteContent.text = note.content

        val time = SimpleDateFormat("MMM dd, HH:mm:ss")
        val resultDate =(note.updateTime)
        holder.noteDate.text = "Last update: ${time.format(resultDate)}"
    }

}