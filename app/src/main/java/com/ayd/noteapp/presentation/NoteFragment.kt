package com.ayd.noteapp.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ayd.core.data.Note
import com.ayd.noteapp.databinding.FragmentNoteBinding
import com.ayd.noteapp.framework.NoteViewModel


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this)[NoteViewModel::class.java]

        binding.approveButton.setOnClickListener {

            if (binding.titleText.text.toString() != "" || binding.contentText.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = binding.titleText.text.toString()
                currentNote.content = binding.contentText.text.toString()
                currentNote.updateTime = time
                if(currentNote.id == 0L){
                    currentNote.creationTime = time
                }
                viewModel.storeNote(currentNote)
            }else{
                Navigation.findNavController(it).popBackStack()
            }
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.stored.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(context,"Yesss",Toast.LENGTH_SHORT).show()
                hideKeyBoard()
                Navigation.findNavController(binding.titleText).popBackStack()
            }else{
                Toast.makeText(context,"Noooooooo, Something went wrong :(",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun hideKeyBoard(){
        val keyboardMode = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        keyboardMode.hideSoftInputFromWindow(binding.titleText.windowToken, 0)
    }

}

