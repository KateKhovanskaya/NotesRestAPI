package com.example.NotesRestAPI.events;

import com.example.NotesRestAPI.model.MyNote;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class NoteUpdatedEvent extends ApplicationEvent {
    private MyNote note;
    public NoteUpdatedEvent(Object source, MyNote note){
        super(source);
        this.note = note;
    }
}
