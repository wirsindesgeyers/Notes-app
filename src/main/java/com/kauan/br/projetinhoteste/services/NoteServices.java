package com.kauan.br.projetinhoteste.services;

import com.kauan.br.projetinhoteste.exceptions.ResourceNotFoundException;
import com.kauan.br.projetinhoteste.model.note.Note;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteRequestDTO;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteResponseDTO;
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
    public NoteResponseDTO createNote(NoteRequestDTO dto){
        Note newNote = new Note();

        newNote.setTitle(dto.title());
        newNote.setContent(dto.content());

        Note savedNote = noteRepository.save(newNote);

        return new NoteResponseDTO(savedNote);
    }

    //edita a nota
    public NoteResponseDTO editNote(NoteRequestDTO dto, Long id){
        Note noteToUpdate = getNoteById(id);

        noteToUpdate.setTitle(dto.title());
        noteToUpdate.setContent(dto.content());

        Note updatedNote = noteRepository.save(noteToUpdate);

        return new NoteResponseDTO(updatedNote);

    }

}