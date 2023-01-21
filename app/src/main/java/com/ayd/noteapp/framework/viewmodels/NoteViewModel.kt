package com.ayd.noteapp.framework.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ayd.core.data.Note
import com.ayd.core.repository.NoteRepository
import com.ayd.core.usecase.*
import com.ayd.noteapp.framework.repository.RoomNoteDataSource
import com.ayd.noteapp.framework.usecase.UseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repo = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        AddNotes(repo),
        GetAllNotes(repo),
        GetNote(repo),
        RemoveNotes(repo),
        GetWordCount()
    )

    val stored = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()


    fun storeNote(note: Note){
        coroutineScope.launch {
            useCases.addNote(note)
            stored.postValue(true)
        }
    }

    fun getNote(id: Long){
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note){
        coroutineScope.launch {
            useCases.removeNotes(note)
            stored.postValue(true)
        }
    }

}