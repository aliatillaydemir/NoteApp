package com.ayd.noteapp.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ayd.core.data.Note
import com.ayd.noteapp.framework.utils.Constants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NoteEntity (
    val title: String,
    val content: String,

    @ColumnInfo(name= "creation_date")
    val creationTime: Long,

    @ColumnInfo(name= "update_time")
    val updateTime: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L

    ){
    companion object{
        fun fromNote(note: Note) = NoteEntity(note.title,note.content,note.creationTime,note.updateTime, note.id) //note id is necessary for update id. without id, our updates make new node!.
    }
    fun toNote() = Note(title, content, creationTime, updateTime, id)
}