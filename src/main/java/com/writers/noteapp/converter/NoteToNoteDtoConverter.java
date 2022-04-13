package com.writers.noteapp.converter;

import com.writers.noteapp.domain.Note;
import com.writers.noteapp.dto.NoteDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteToNoteDtoConverter implements Converter<Note, NoteDto> {

    @Override
    public NoteDto convert(Note source) {
        NoteDto noteDto = NoteDto.builder()
                .id(source.getId())
                .content(source.getContent())
                .userId(source.getUserId())
                .build();
        return noteDto;
    }
}
