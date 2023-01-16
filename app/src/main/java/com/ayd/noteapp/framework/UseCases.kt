package com.ayd.noteapp.framework

import com.ayd.core.usecase.AddNotes
import com.ayd.core.usecase.GetAllNotes
import com.ayd.core.usecase.GetNote
import com.ayd.core.usecase.RemoveNotes

data class UseCases(
    val addNote: AddNotes,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNotes: RemoveNotes
)