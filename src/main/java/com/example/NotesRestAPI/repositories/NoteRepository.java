package com.example.NotesRestAPI.repositories;

import com.example.NotesRestAPI.model.MyNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface NoteRepository extends JpaRepository<MyNote, Long> {
    /**
     * Поиск заметки по id
     * @param id id заметки
     * @return optional, содержащий заметку, если она найдена
     */
    Optional<MyNote> findById(Long id);
}
