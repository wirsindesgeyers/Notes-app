package com.kauan.br.projetinhoteste.services;

import com.kauan.br.projetinhoteste.model.note.Note;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteRequestDTO;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteResponseDTO;
import com.kauan.br.projetinhoteste.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class NoteServicesTest {

    @InjectMocks
    private NoteServices noteServices;

    @Mock
    private NoteRepository noteRepository;


    private NoteRequestDTO noteRequestDTO;
    private Note unsavedNote, savedNote;


    @Mock
    private NoteResponseDTO noteResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }


    @Test
    @DisplayName("Should create a new note succesfully when everything is correct")
    void createNote() {

    }

    @Test
    @DisplayName("Should throw an exception when trying to create a note with empty title")
    void createNoteCase2() {

    }

    @Test
    @DisplayName("Should throw an exception when trying to create a note with null content")
    void createNoteCase3() {

    }


}