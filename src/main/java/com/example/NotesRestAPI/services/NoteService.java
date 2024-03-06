package com.example.NotesRestAPI.services;

import com.example.NotesRestAPI.events.NoteUpdatedEvent;
import com.example.NotesRestAPI.model.MyNote;
import com.example.NotesRestAPI.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    /**
     * Репозиторий для связи с БД
     */
    private final NoteRepository repository;
    /**
     * Публикатор событий, которые слушаются с помощью ApplicationListener
     */
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * Добавление заметки в репозиторий
     * @param note заметка
     * @return добавленная заметка
     */
    public MyNote addNote(MyNote note){
        return repository.save(note);
    }

    /**
     * Получение всех заметок
     * @return список заметок
     */
    public List<MyNote> getAllNotes(){
        return repository.findAll();
    }

    /**
     * Получение заметки по id
     * @param id id заметки
     * @return найденная заметка или null
     */
    public MyNote getNoteById(Long id){
        return repository.findById(id).orElseThrow(null);
    }

    /**
     * Обновление заметки
     * @param id id заметки
     * @param note обновленная заметка
     * @return обновленная заметка
     */
    public MyNote updateNote(Long id, MyNote note){
        MyNote updatingNote = getNoteById(id);
        updatingNote.setTitle(note.getTitle());
        updatingNote.setDescription(note.getDescription());
        publisher.publishEvent(new NoteUpdatedEvent(this, updatingNote));
        return repository.save(updatingNote);
    }

    /**
     * Удаление заметки по id
     * @param id id заметки
     */
    public void deleteNote(Long id){
        repository.deleteById(id);
    }
}
