package com.kauan.br.projetinhoteste.controller;

import com.kauan.br.projetinhoteste.model.note.Note;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteRequestDTO;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteResponseDTO;
import com.kauan.br.projetinhoteste.services.NoteServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/notes")
public class NotesController {


    private final NoteServices noteServices;

    public NotesController(NoteServices noteServices) {
        this.noteServices = noteServices;
    }


    // criar notas
    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteRequestDTO requestDTO){
        Note newNote = new Note();

        newNote.setContent(requestDTO.content());
        newNote.setTitle(requestDTO.title());

        Note savedNote = noteServices.createNote(newNote);

        return ResponseEntity.status(HttpStatus.CREATED).body(new NoteResponseDTO(savedNote));
    }

    //procurar nota
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> findNoteById(@PathVariable Long id) {

        Note note = noteServices.getNoteById(id);

        return ResponseEntity.ok(new NoteResponseDTO(note));
    }

    //deletar nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){
        noteServices.deleteNote(id);
        return ResponseEntity.noContent().build();
    }


    //pegar todas as notas
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAllNotes(){
        List<Note> notes = noteServices.getAllNotes();
        List<NoteResponseDTO> notesResponseDto = notes.stream()
                .map(NoteResponseDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(notesResponseDto);
    }

    //editar nota
    @PutMapping ("/{id}")
    public ResponseEntity<NoteResponseDTO> editNote(@PathVariable Long id, @RequestBody NoteRequestDTO noteRequestDTO){
        Note noteContent = new Note();

        noteContent.setTitle(noteRequestDTO.title());

        noteContent.setContent(noteRequestDTO.content());

        Note updatedNote = noteServices.editNote(noteContent, id);
        return ResponseEntity.ok(new NoteResponseDTO(updatedNote));
    }
}