package com.writers.noteapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.writers.noteapp.domain.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        this.mockMvc.perform(get("/notes"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void create() throws Exception {
        Note note = Note.builder().content("note content").userId(1L).build();
        this.mockMvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(note)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
