package com.writers.noteapp.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@EqualsAndHashCode
public class NoteDto {

    private Long id;
    private String content;
    private Long userId;
}
