package com.example.notesappfull

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notes : ArrayList<Note>
    private lateinit var adapter : RVAdapter
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notes = arrayListOf()
        adapter = RVAdapter(this,notes)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        btSave.setOnClickListener {
            if(etNote.text.toString().isNotEmpty()) {



//                val note = etNote.text.toString()
                databaseHelper.addNote(Note(0,etNote.text.toString()))
                Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
//                notes = databaseHelper.readData()
//                adapter.update(notes)
            }
            notes = databaseHelper.viewNotes()
            adapter.update(notes)

        }


    }
    fun raiseDialog(id: Int){
        val dialogBuilder = AlertDialog.Builder(this)
        val updatedNote = EditText(this)
        updatedNote.hint = "Enter new text"
        dialogBuilder
            .setCancelable(false)
            .setPositiveButton("Save", DialogInterface.OnClickListener {
                    _, _ -> editNote(id, updatedNote.text.toString())
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Update Note")
        alert.setView(updatedNote)
        alert.show()
    }



    private fun editNote(noteID: Int, noteText: String){
            databaseHelper.updateNote(Note(noteID, noteText))
        adapter.update(notes)

    }

    fun deleteNote(noteID: Int){
        databaseHelper.deleteNote(Note(noteID, ""))
        adapter.update(notes)

    }
}