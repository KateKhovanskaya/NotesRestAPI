package com.example.NotesRestAPI.controllers;

import com.example.NotesRestAPI.model.MyNote;
import com.example.NotesRestAPI.services.FileGateWay;
import com.example.NotesRestAPI.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    /**
     * Сервис для управления заметками
     */
    private final NoteService noteService;
    /**
     * Сервис (интерфейс) интеграции для записи заметок в файл
     */
    private final FileGateWay fileGateWay;

    /**
     * Добавление заметки
     * @param note заметка
     * @return http-ответ, содержащий статус и добавленную заметку
     */
    @PostMapping
    public ResponseEntity<MyNote> addNote(@RequestBody MyNote note){
        ResponseEntity<MyNote> resultEntity = new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
        fileGateWay.writeToFile("notes.txt", resultEntity);
        return resultEntity;
//        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.CREATED);
    }

    /**
     * Получение всех заметок
     * @return http-ответ, содержащий статус и список заметок
     */
    @GetMapping
    public ResponseEntity<List<MyNote>> getAll(){
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Получение заметки по id
     * @param id id заметки
     * @return http-ответ, содержащий статус и найденную заметку, если статус ОК
     */
    @GetMapping("/{id}")
    public ResponseEntity<MyNote> getNoteById(@PathVariable("id") Long id){
        MyNote note;
        try{
            note = noteService.getNoteById(id);
        }catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MyNote());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * Обновление заметки
     * @param id id заметки
     * @param note заметка
     * @return http-ответ, содержащий статус и обновленную заметку
     */
    @PutMapping("/{id}")
    public ResponseEntity<MyNote> updateNote(@PathVariable("id") Long id, @RequestBody MyNote note){
        return new ResponseEntity<>(noteService.updateNote(id, note), HttpStatus.OK);
    }

    /**
     * Удаление заметки по id
     * @param id id заметки
     * @return http-ответ, содержащий статус выполение операции
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
