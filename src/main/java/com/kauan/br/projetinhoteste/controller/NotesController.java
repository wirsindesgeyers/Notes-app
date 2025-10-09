package com.kauan.br.projetinhoteste.controller;


import com.kauan.br.projetinhoteste.model.Note;
import com.kauan.br.projetinhoteste.services.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/notes")
public class NotesController {

    @Autowired
    private NoteServices noteServices;

    //cria a anotação
    @PostMapping
    public ResponseEntity CreateNote(@RequestBody Note newNote){

        Note savedNote = noteServices.createNote(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }



    //pega a anotação pelo id
    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable Long id) {
        Optional<Note> note = noteServices.getNoteById(id);

        if (note.isPresent()) {
            return ResponseEntity.ok(note.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id){
        Optional<Note> note;
        if(noteServices.getNoteById(id).isPresent()){
            noteServices.deleteNote(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //pegar todas as notas
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> note = noteServices.getAllNotes();
        
        return ResponseEntity.ok().body(note);
    }


}


