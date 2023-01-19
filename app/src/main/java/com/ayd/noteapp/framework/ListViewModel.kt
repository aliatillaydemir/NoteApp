package com.ayd.noteapp.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ayd.core.data.Note
import com.ayd.core.repository.NoteRepository
import com.ayd.core.usecase.AddNotes
import com.ayd.core.usecase.GetAllNotes
import com.ayd.core.usecase.GetNote
import com.ayd.core.usecase.RemoveNotes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repo = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        AddNotes(repo),
        GetAllNotes(repo),
        GetNote(repo),
        RemoveNotes(repo)
    )

    val note = MutableLiveData<List<Note>>()

    fun getNotes(){
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            note.postValue(noteList)
        }
    }

}