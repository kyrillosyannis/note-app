package com.writers.noteapp.converter;

import com.writers.noteapp.domain.Note;
import com.writers.noteapp.dto.NoteDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteDtoToNoteConverter implements Converter<NoteDto, Note> {

    @Override
    public Note convert(NoteDto source) {
        Note note = Note.builder()
                .id(source.getId())
                .content(source.getContent())
                .userId(source.getUserId())
                .build();
        return note;
    }
}
