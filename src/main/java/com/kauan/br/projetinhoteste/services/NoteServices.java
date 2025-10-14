package com.kauan.br.projetinhoteste.services;

import com.kauan.br.projetinhoteste.exceptions.ResourceNotFoundException;
import com.kauan.br.projetinhoteste.model.note.Note;
import com.kauan.br.projetinhoteste.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServices {

    private final NoteRepository noteRepository;


    public NoteServices(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    //retorna todas as notas
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    //retorna a nota pelo id
    public Note getNoteById(Long id){
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anotação com o ID " + id + " não foi encontrada."));
    }

    //deleta a nota
    public void deleteNote(Long id){
        if(!noteRepository.existsById(id)){
            throw new ResourceNotFoundException("Impossível deletar. Anotação com o ID " + id + " não foi encontrada.");
        }
        noteRepository.deleteById(id);
    }

    //cria a nota
    public Note createNote(Note unsavedNote){
        return noteRepository.save(unsavedNote);
    }

    //edita a nota
    public Note editNote(Note noteWithNewData, Long id){
        Note noteToUpdate = getNoteById(id);

        noteToUpdate.setTitle(noteWithNewData.getTitle());
        noteToUpdate.setContent(noteWithNewData.getContent());

        return noteRepository.save(noteToUpdate);
    }
}