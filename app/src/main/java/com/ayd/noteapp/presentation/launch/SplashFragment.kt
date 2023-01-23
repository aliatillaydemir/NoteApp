package com.ayd.noteapp.presentation.launch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ayd.noteapp.R
import com.ayd.noteapp.framework.utils.Constants
import com.ayd.noteapp.framework.utils.Constants.Companion.SHARED_PREF_BOARD
import com.ayd.noteapp.framework.utils.Constants.Companion.SHARED_PREF_EDIT
import com.ayd.noteapp.presentation.MainActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        launch {
            delay(2000)
            withContext(coroutineContext){
                if(onBoardingFinished()){
                    findNavController().navigate(R.id.action_splashFragment_to_mainActivity)
                    //startActivity(Intent(requireContext(),MainActivity::class.java))
                    activity?.finish()
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                }
            }
        }

    }


    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences(SHARED_PREF_BOARD, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(SHARED_PREF_EDIT, false)
    }


}