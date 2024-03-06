package com.example.NotesRestAPI.services;

import com.example.NotesRestAPI.model.MyNote;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "messageChannelInput")
public interface FileGateWay {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, ResponseEntity<MyNote> data);
}
