package com.kauan.br.projetinhoteste.model.note.dtos;

import com.kauan.br.projetinhoteste.model.note.Note;

public record NoteResponseDTO (
        Long id,
        String title,
        String content



){
    public NoteResponseDTO(Note note){
        this(note.getId(), note.getTitle(), note.getContent());
    }


}
