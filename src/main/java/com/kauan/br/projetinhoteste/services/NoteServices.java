package com.kauan.br.projetinhoteste.services;

import com.kauan.br.projetinhoteste.model.Note;
import com.kauan.br.projetinhoteste.repository.NotesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServices {

    private final NotesRepository notesRepository;

    public NoteServices(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    //buscar todas as notas
    public List<Note> getAllNotes() {
        return notesRepository.findAll();
    }

    //procurar nota por id
    public Optional<Note> getNoteById(Long id){
            return notesRepository.findById(id);

    }

    public List<Note> getAllNotes(Long id){
        return notesRepository.findAll();
    }


    //deletar nota por id
    public void deleteNote(Long id){
        if(notesRepository.findById(id).isPresent()){
            notesRepository.deleteById(id);
        }
    }
    
    //Criar a nota
    public Note createNote(Note unsavedNote){
        return notesRepository.save(unsavedNote);
    }



}
