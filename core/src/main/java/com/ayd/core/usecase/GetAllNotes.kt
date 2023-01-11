package com.ayd.core.usecase

import com.ayd.core.repository.NoteRepository

class GetAllNotes(private val noteRepo: NoteRepository ) {
    suspend operator fun invoke() = noteRepo.getAllNotes()
}