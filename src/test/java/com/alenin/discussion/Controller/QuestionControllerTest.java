package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.QuestionDTO;
import com.alenin.discussion.Entity.QuestionEntity;
import com.alenin.discussion.Service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;





@WebMvcTest(QuestionController.class)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService service;

    @Test
    void add() throws Exception {
        Integer id = 1;
        String title = "Test is good";
        String comment = "Backend better when create tests";
        QuestionEntity question = new QuestionEntity(id, title, comment);

        when(service.add(any(QuestionDTO.class))).thenReturn(question);

        mockMvc.perform(post("/question/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"%s\", \"comment\": \"%s\"}".formatted(title, comment)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(question.getId()))
                .andExpect(jsonPath("$.title").value(question.getTitle()))
                .andExpect(jsonPath("$.comment").value(question.getComment()));
    }

    @Test
    void getById() throws Exception {
        Integer id = 1;
        String title = "Test is good";
        String comment = "Backend better when create tests";
        QuestionEntity question = new QuestionEntity(id, title, comment);

        when(service.findById(id)).thenReturn(question);

        mockMvc.perform(get("/question/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(question.getId()))
                .andExpect(jsonPath("$.title").value(question.getTitle()))
                .andExpect(jsonPath("$.comment").value(question.getComment()));
    }

    @Test
    void findAll() throws Exception {
        Integer id = 1;
        String title = "Test is good";
        String comment = "Backend better when create tests";
        QuestionEntity question = new QuestionEntity(id, title, comment);
        Integer id2 = 2;
        String title2 = "Tests are expensive";
        String comment2 = "I spent a lot of time to write them";
        QuestionEntity question2 = new QuestionEntity(id2, title2, comment2);
        List<QuestionEntity> questions = Arrays.asList(question, question2);

        when(service.findAll()).thenReturn(questions);

        mockMvc.perform(get("/question/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(question.getId()))
                .andExpect(jsonPath("$[0].title").value(question.getTitle()))
                .andExpect(jsonPath("$[0].comment").value(question.getComment()))
                .andExpect(jsonPath("$[1].id").value(question2.getId()))
                .andExpect(jsonPath("$[1].title").value(question2.getTitle()))
                .andExpect(jsonPath("$[1].comment").value(question2.getComment()));


    }

    @Test
    public void notFoundById() throws Exception{
        Integer id = 1;
        when(service.findById(id)).thenReturn(null);

        mockMvc.perform(get("/question/{id}", id))
                .andExpect(status().isNotFound());

    }

    @Test
    public void invalidAdd() throws  Exception{
        String comment = "Backend better when create tests";
        mockMvc.perform(post("/question/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"null\", \"comment\": \"%s\"}".formatted(comment)))
                .andExpect(status().isBadRequest());

    }
}