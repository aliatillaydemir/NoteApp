package com.ayd.noteapp.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ayd.core.data.Note
import com.ayd.noteapp.R
import com.ayd.noteapp.databinding.FragmentNoteBinding
import com.ayd.noteapp.framework.viewmodels.NoteViewModel


class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private var noteId = 0L
    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("","",0L,0L)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }


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

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if(noteId != 0L){
            viewModel.getNote(noteId)
        }

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

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
           it?.let{
               currentNote = it
               binding.titleText.setText(it.title, TextView.BufferType.EDITABLE)
               binding.contentText.setText(it.content, TextView.BufferType.EDITABLE)
           }
        })
    }

    private fun hideKeyBoard(){
        val keyboardMode = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        keyboardMode.hideSoftInputFromWindow(binding.titleText.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteNote ->{
                if(context != null && noteId != 0L){
                    AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are u sure dude?")
                        .setPositiveButton("Yes baby"){ _, _ ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("No, Cancel"){ _, _ -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }

}

