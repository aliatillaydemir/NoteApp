package com.ayd.noteapp.framework.usecase

import com.ayd.core.usecase.*

data class UseCases(
    val addNote: AddNotes,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNotes: RemoveNotes,
    val getWordCount: GetWordCount
)