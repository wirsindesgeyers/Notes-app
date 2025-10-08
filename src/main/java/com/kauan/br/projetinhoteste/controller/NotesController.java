package com.kauan.br.projetinhoteste.controller;


import com.kauan.br.projetinhoteste.services.NoteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/notes")
public class NotesController {

    @Autowired
    private NoteServices noteServices;

    //pega a anotação pelo id
    @GetMapping("/{id}")
    public ResponseEntity findNoteById(Long id){
        noteServices.getNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Notas encontradas com sucesso.");
    }

}
