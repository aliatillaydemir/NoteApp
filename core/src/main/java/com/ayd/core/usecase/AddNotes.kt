package com.ayd.core.usecase

import com.ayd.core.data.Note
import com.ayd.core.repository.NoteRepository

class AddNotes(private val noteRepo: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepo.addNote(note)

}