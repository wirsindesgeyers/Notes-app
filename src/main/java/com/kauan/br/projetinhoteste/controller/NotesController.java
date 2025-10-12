package com.kauan.br.projetinhoteste.controller;

import com.kauan.br.projetinhoteste.model.Note;
import com.kauan.br.projetinhoteste.services.NoteServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/notes")
public class NotesController {


    private final NoteServices noteServices;

    public NotesController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }


    // criar notas
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note newNote){
        Note savedNote = noteServices.createNote(newNote);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNote);
    }

    //procurar nota
    @GetMapping("/{id}")
    public ResponseEntity<Note> findNoteById(@PathVariable Long id) {
        Note note = noteServices.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    //deletar nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){
        noteServices.deleteNote(id);
        return ResponseEntity.noContent().build();
    }


    //pegar todas as notas
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(){
        List<Note> notes = noteServices.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    //editar nota
    @PutMapping ("/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note noteContent){
        Note updatedNote = noteServices.editNote(noteContent, id);
        return ResponseEntity.ok(updatedNote);
    }
}