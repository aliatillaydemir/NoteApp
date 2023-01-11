package com.ayd.core.usecase

import com.ayd.core.repository.NoteRepository

class GetNote(private val noteRepo: NoteRepository) {

    suspend operator fun invoke(id: Long) = noteRepo.getNote(id)

}