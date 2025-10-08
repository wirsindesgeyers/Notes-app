package com.kauan.br.projetinhoteste.repository;
import com.kauan.br.projetinhoteste.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {

}
