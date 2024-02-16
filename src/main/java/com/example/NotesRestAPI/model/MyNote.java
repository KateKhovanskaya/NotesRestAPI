package com.example.NotesRestAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "notes")
public class MyNote {
    /**
     * Id заметки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Заголовок заметки
     */
    @Column(nullable = false)
    private String title;
    /**
     * Содержимое заметки
     */
    @Column(nullable = false)
    private String description;
    /**
     * Дата и время создания заметки
     */
    private LocalDateTime creatingData = LocalDateTime.now();

}
