package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.RelationDTO;
import com.alenin.discussion.Entity.RelationEntity;
import com.alenin.discussion.Service.RelationService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(RelationController.class)
class RelationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private RelationService service;


    @Test
    void add() throws Exception {
        Integer id = 1;
        Integer thesis = 1;
        Integer argument = 2;
        Float influence = 0.75F;
        RelationEntity relation = new RelationEntity(id, thesis, argument, influence);

        when(service.add(any(RelationDTO.class))).thenReturn(relation);

        mockMvc.perform(post("/relation/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format(
                                "{\"thesis\": %d, \"argument\": %d, \"influence\": 0.75}",
                                thesis, argument)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(relation.getId()))
                .andExpect(jsonPath("$.thesis").value(relation.getThesis()))
                .andExpect(jsonPath("$.argument").value(relation.getArgument()))
                .andExpect(jsonPath("$.influence").value(relation.getInfluence()));
    }

    @Test
    void getById() throws Exception {
        Integer id = 1;
        Integer thesis = 1;
        Integer argument = 2;
        Float influence = 0.75F;
        RelationEntity relation = new RelationEntity(id, thesis, argument, influence);

        when(service.findById(id)).thenReturn(relation);

        mockMvc.perform(get("/relation/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(relation.getId()))
                .andExpect(jsonPath("$.thesis").value(relation.getThesis()))
                .andExpect(jsonPath("$.argument").value(relation.getArgument()))
                .andExpect(jsonPath("$.influence").value(relation.getInfluence()));
    }

    @Test
    void findAll() throws Exception {
        Random random = new Random();
        List<RelationEntity> relations = IntStream.range(0, 100)
                .mapToObj(i -> {
                    RelationEntity relation = new RelationEntity();
                    relation.setId(i+1);
                    relation.setThesis(random.nextInt());
                    relation.setArgument(random.nextInt());
                    relation.setInfluence(-1.0F + 2.0F * random.nextFloat());
                    return relation;
                }).toList();

        when(service.findAll()).thenReturn(relations);

        mockMvc.perform(get("/relation/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(relations.size()))
                .andDo(result -> {
                    ObjectMapper mapper = new ObjectMapper();
                    List<RelationEntity> returnedRelations = mapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            }
                    );
                    assertThat(returnedRelations)
                            .hasSize(relations.size())
                            .containsExactlyInAnyOrderElementsOf(relations);
                });

    }
}