package com.writers.noteapp.controller;

import com.writers.noteapp.dto.NoteDto;
import com.writers.noteapp.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/notes")
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> create(@RequestBody NoteDto noteDto) {
        log.info("Saving note");
        noteDto = noteService.save(noteDto);
        return new ResponseEntity<>(noteDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        log.info("Deleting note with id: {}", id);
        noteService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> update(@PathVariable(name = "id") Long id,
                                          @RequestBody NoteDto noteDto) {
        log.info("Updating note with id: {}", id);
        noteDto = noteService.save(noteDto);
        return new ResponseEntity<>(noteDto, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<NoteDto>> findAll(@RequestParam(defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                 @RequestParam(defaultValue = "id", required = false) String sortBy,
                                 @RequestParam(defaultValue = "ASC", required = false) String sortDirection) {
        log.info("Finding all notes start");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));
        Page<NoteDto> noteDtos = noteService.findAll(pageable);
        return new ResponseEntity<>(noteDtos, HttpStatus.OK);
    }
}
