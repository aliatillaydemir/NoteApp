package com.ayd.noteapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayd.core.data.Note
import com.ayd.noteapp.databinding.ItemNoteLayoutBinding
import java.text.SimpleDateFormat


class NoteAdapter(var notes: ArrayList<Note>, private val action: ListAction): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>){
        /*notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()*/

        /** we're using diffUtil instead of notifyDataSetChanged*/

        val diffUtil = com.ayd.noteapp.framework.utils.DiffUtil(notes, newNotes)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        //notes = newNotes as ArrayList<Note>
        diffUtilResult.dispatchUpdatesTo(this)

        notes.clear()
        notes.addAll(newNotes)
    }

    inner class NoteViewHolder(binding: ItemNoteLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val layout = binding.noteLayout
        val noteTitle = binding.title
        val noteContent = binding.content
        val noteDate = binding.date
        val noteWords = binding.wordCount
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

        holder.layout.setOnClickListener{action.onClick(note.id)}

        holder.noteWords.text = "Words: ${note.wordCount}"
    }

}