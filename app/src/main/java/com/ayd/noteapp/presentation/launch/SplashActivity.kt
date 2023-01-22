package com.ayd.noteapp.presentation.launch

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.ayd.noteapp.R
import com.ayd.noteapp.framework.utils.Constants.Companion.SHARED_PREF_BOARD
import com.ayd.noteapp.framework.utils.Constants.Companion.SHARED_PREF_EDIT
import com.ayd.noteapp.presentation.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoActionBarStyle)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        launch {
            delay(4000)
            withContext(coroutineContext){
                if(onBoardingFinished()){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(applicationContext,LaunchActivity::class.java))
                }
            }
        }


    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = this.getSharedPreferences(SHARED_PREF_BOARD, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(SHARED_PREF_EDIT, false)
    }


}