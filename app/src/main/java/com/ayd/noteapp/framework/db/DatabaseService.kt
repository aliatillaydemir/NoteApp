package com.ayd.noteapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ayd.noteapp.framework.utils.Constants.Companion.DATABASE_NAME

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class DatabaseService: RoomDatabase() {

    companion object {

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun noteDao(): NoteDao

}