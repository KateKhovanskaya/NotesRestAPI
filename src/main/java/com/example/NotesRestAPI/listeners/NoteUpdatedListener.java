package com.example.NotesRestAPI.listeners;

import com.example.NotesRestAPI.events.NoteUpdatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NoteUpdatedListener implements ApplicationListener<NoteUpdatedEvent> {
    @Override
    public void onApplicationEvent(NoteUpdatedEvent event){
        System.out.println("Обновилась заметка:\n" + event.getNote());
    }

}
