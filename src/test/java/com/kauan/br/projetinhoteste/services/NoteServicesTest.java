package com.kauan.br.projetinhoteste.services;
import com.kauan.br.projetinhoteste.exceptions.ResourceNotFoundException;
import com.kauan.br.projetinhoteste.model.note.Note;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteRequestDTO;
import com.kauan.br.projetinhoteste.model.note.dtos.NoteResponseDTO;
import com.kauan.br.projetinhoteste.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class NoteServicesTest {

    @InjectMocks
    private NoteServices noteServices;

    @Mock
    private NoteRepository noteRepository;

    private Long existingNoteId = 1L;
    private Long nonExistingNoteId = 99L;
    private Note baseExistingNote;

    @BeforeEach
    void setUp() {
        baseExistingNote = new Note();
        baseExistingNote.setId(existingNoteId);
        baseExistingNote.setTitle("Titulo Antigo Padrao");
        baseExistingNote.setContent("Conteudo Antigo Padrao");
    }


    // testes pro método createNote
    @Test
    @DisplayName("Should create a new note successfully when everything is correct")
    void createNote_success() {
        // arrange
        NoteRequestDTO requestDTO = new NoteRequestDTO("Titulo para Criar", "Conteudo para Criar");

        Note expectedSavedNote = new Note();
        expectedSavedNote.setId(2L);
        expectedSavedNote.setTitle(requestDTO.title());
        expectedSavedNote.setContent(requestDTO.content());

        when(noteRepository.save(any(Note.class))).thenReturn(expectedSavedNote);

        ArgumentCaptor<Note> noteCaptor = ArgumentCaptor.forClass(Note.class);

        // act
        NoteResponseDTO result = noteServices.createNote(requestDTO);

        // assert
        assertNotNull(result);
        assertEquals(expectedSavedNote.getId(), result.id());
        assertEquals(requestDTO.title(), result.title());
        assertEquals(requestDTO.content(), result.content());

        // verify
        verify(noteRepository, times(1)).save(noteCaptor.capture());
        Note notePassedToSave = noteCaptor.getValue();
        assertNull(notePassedToSave.getId());
        assertEquals(requestDTO.title(), notePassedToSave.getTitle());
        assertEquals(requestDTO.content(), notePassedToSave.getContent());
    }

    // testes pro método getAllNotes
    @Test
    @DisplayName("Should return a list of all notes")
    void getAllNotes_success() {
        // arrange
        Note note1 = new Note(1L, "Título 1", "Conteúdo 1");
        Note note2 = new Note(2L, "Título 2", "Conteúdo 2");
        List<Note> expectedNotes = Arrays.asList(note1, note2);

        when(noteRepository.findAll()).thenReturn(expectedNotes);

        // act
        List<Note> result = noteServices.getAllNotes();

        // assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedNotes, result);

        // verify
        verify(noteRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return an empty list if no notes exist")
    void getAllNotes_emptyList() {
        // arrange
        when(noteRepository.findAll()).thenReturn(List.of());

        // act
        List<Note> result = noteServices.getAllNotes();

        // assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.size());

        // verify
        verify(noteRepository, times(1)).findAll();
    }


    // testes pro método getNoteById
    @Test
    @DisplayName("Should return a note when a valid ID is provided")
    void getNoteById_success() {
        // arrange
        when(noteRepository.findById(existingNoteId)).thenReturn(Optional.of(baseExistingNote));

        // act
        Note result = noteServices.getNoteById(existingNoteId);

        // assert
        assertNotNull(result);
        assertEquals(baseExistingNote.getId(), result.getId());
        assertEquals(baseExistingNote.getTitle(), result.getTitle());
        assertEquals(baseExistingNote.getContent(), result.getContent());

        //verify
        verify(noteRepository, times(1)).findById(existingNoteId);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when ID does not exist")
    void getNoteById_notFound() {
        // arrange
        when(noteRepository.findById(nonExistingNoteId)).thenReturn(Optional.empty());

        // act e assert
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            noteServices.getNoteById(nonExistingNoteId);
        });
        assertTrue(thrown.getMessage().contains("não foi encontrada."));

        // verify
        verify(noteRepository, times(1)).findById(nonExistingNoteId);
    }

    //testes pro metodo editNote
    @Test
    @DisplayName("Should update an existing note successfully")
    void editNote_success() {
        // arrange
        NoteRequestDTO updateRequestDTO = new NoteRequestDTO("Titulo Novo", "Conteudo Novo");

        Note noteInDbBeforeUpdate = baseExistingNote;

        when(noteRepository.findById(existingNoteId)).thenReturn(Optional.of(noteInDbBeforeUpdate));

        when(noteRepository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // act
        NoteResponseDTO result = noteServices.editNote(updateRequestDTO, existingNoteId);

        // assert
        assertNotNull(result);
        assertEquals(existingNoteId, result.id());
        assertEquals(updateRequestDTO.title(), result.title());
        assertEquals(updateRequestDTO.content(), result.content());

        // verify
        verify(noteRepository, times(1)).findById(existingNoteId);
        verify(noteRepository, times(1)).save(noteInDbBeforeUpdate);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when editing a non-existing note")
    void editNote_notFound() {
        // arrange
        NoteRequestDTO updateRequestDTO = new NoteRequestDTO("Titulo Novo", "Conteudo Novo");

        when(noteRepository.findById(nonExistingNoteId)).thenReturn(Optional.empty());

        // act e assert
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            noteServices.editNote(updateRequestDTO, nonExistingNoteId);
        });
        assertTrue(thrown.getMessage().contains("não foi encontrada."));

        // verify
        verify(noteRepository, times(1)).findById(nonExistingNoteId);
        verify(noteRepository, never()).save(any(Note.class));
    }


    //testes pro deleteNote
    @Test
    @DisplayName("Should delete an existing note successfully")
    void deleteNote_success() {
        //arrange
        when(noteRepository.existsById(existingNoteId)).thenReturn(true);
        doNothing().when(noteRepository).deleteById(existingNoteId);

        // act
        assertDoesNotThrow(() -> noteServices.deleteNote(existingNoteId));

        // verify
        verify(noteRepository, times(1)).existsById(existingNoteId);
        verify(noteRepository, times(1)).deleteById(existingNoteId);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when deleting a non-existing note")
    void deleteNote_notFound() {
        // arrange
        when(noteRepository.existsById(nonExistingNoteId)).thenReturn(false);

        // act e assert
        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class, () -> {
            noteServices.deleteNote(nonExistingNoteId);
        });
        assertTrue(thrown.getMessage().contains("não foi encontrada."));

        // verify
        verify(noteRepository, times(1)).existsById(nonExistingNoteId);
        verify(noteRepository, never()).deleteById(anyLong());
    }

}