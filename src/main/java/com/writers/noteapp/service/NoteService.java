package com.writers.noteapp.service;

import com.writers.noteapp.domain.Note;
import com.writers.noteapp.dto.NoteDto;
import com.writers.noteapp.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NoteService {

    private NoteRepository noteRepository;
    private ConversionService conversionService;

    @Autowired
    public NoteService(NoteRepository noteRepository, ConversionService conversionService) {
        this.noteRepository = noteRepository;
        this.conversionService = conversionService;
    }

    public NoteDto save(NoteDto noteDto) {
        log.info("Save note start");
        Note note = conversionService.convert(noteDto, Note.class);
        note = noteRepository.save(note);
        noteDto = conversionService.convert(note, NoteDto.class);
        log.info("Save note end");
        return noteDto;
    }

    public void deleteById(Long id) {
        log.info("Delete note with id: {} start", id);
        noteRepository.deleteById(id);
        log.info("Delete note with id: {} end", id);
    }
}
