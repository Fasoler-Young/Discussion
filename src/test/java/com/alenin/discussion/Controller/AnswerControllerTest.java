package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.AnswerDTO;
import com.alenin.discussion.Entity.AnswerEntity;
import com.alenin.discussion.Service.AnswerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AnswerController.class)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnswerService service;


    /**
     * This method generate 100 random answers and then testing response
     */
    @Test
    public void findAllV2() throws Exception{
        Random random = new Random();
        List<AnswerEntity> answers = IntStream.range(0, 100)
                .mapToObj(i -> {
                    AnswerEntity entity = new AnswerEntity();
                    entity.setId(i + 1);
                    entity.setThesis(random.nextInt(Integer.MAX_VALUE - 1) + 1);
                    entity.setConfidence(-1.0F + 2.0F * random.nextFloat());
                    return entity;
                }).toList();

        when(service.findAll()).thenReturn(answers);

        mockMvc.perform(get("/answer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(answers.size()))
                .andDo(result -> {
                    ObjectMapper mapper = new ObjectMapper();
                    List<AnswerEntity> returnedAnswers = mapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertThat(returnedAnswers)
                            .hasSize(answers.size())
                            .containsExactlyInAnyOrderElementsOf(answers);
                });


    }

    @Test
    public void findAll() throws Exception{

        AnswerEntity answer1 = new AnswerEntity(1, 1, 0.75F);
        AnswerEntity answer2 = new AnswerEntity(2,2, -0.75F);
        List<AnswerEntity> answers = Arrays.asList(answer1, answer2);

        when(service.findAll()).thenReturn(answers);

        mockMvc.perform(get("/answer/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(answers.size()))
                .andExpect(jsonPath("$[0].id").value(answer1.getId()))
                .andExpect(jsonPath("$[0].thesis").value(answer1.getThesis()))
                .andExpect(jsonPath("$[0].confidence").value(answer1.getConfidence()))
                .andExpect(jsonPath("$[1].id").value(answer2.getId()))
                .andExpect(jsonPath("$[1].thesis").value(answer2.getId()))
                .andExpect(jsonPath("$[1].confidence").value(answer2.getConfidence()));

    }

    @Test
    public void findById() throws Exception{
        Integer id = 1;
        AnswerEntity answer = new AnswerEntity(id, 1, 0.75F);

        when(service.findById(id)).thenReturn(answer);
        mockMvc.perform(get("/answer/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(answer.getId()))
                .andExpect(jsonPath("$.thesis").value(answer.getThesis()))
                .andExpect(jsonPath("$.confidence").value(answer.getConfidence()));

    }

    @Test
    public void notFoundById() throws Exception{
        Integer id = 1;
        when(service.findById(id)).thenReturn(null);

        mockMvc.perform(get("/answer/{id}", id))
                .andExpect(status().isNotFound());

    }

    @Test
    public void add() throws Exception {
        Integer id = 1;
        AnswerEntity answer = new AnswerEntity(id, 1, 0.75F);

        when(service.add(any(AnswerDTO.class))).thenReturn(answer);

        mockMvc.perform(post("/answer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"thesis\": 1, \"confidence\": 0.75}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(answer.getId()))
                .andExpect(jsonPath("$.thesis").value(answer.getThesis()))
                .andExpect(jsonPath("$.confidence").value(answer.getConfidence()));

    }

    @Test
    public void invalidAdd() throws  Exception{
        mockMvc.perform(post("/answer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"thesis\": \"null\", \"confidence\": 0.75}"))
                .andExpect(status().isBadRequest());

    }

    //TODO Сделать тесты для метода update, но не уверен, что стоит

}