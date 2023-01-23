package com.ayd.noteapp.presentation.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ayd.noteapp.R


class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBarStyle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

    }

}