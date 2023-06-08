package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.QuestionDTO;
import com.alenin.discussion.Entity.QuestionEntity;
import com.alenin.discussion.Exception.ResourceNotFoundException;
import com.alenin.discussion.Service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService service;


    public QuestionController(QuestionService service) {
        this.service = service;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionEntity add(@Valid @RequestBody QuestionDTO question){
        return service.add(question);
    }

    @PatchMapping("/update")
    public QuestionEntity update(@Valid @RequestBody QuestionEntity question){
        return service.update(question);
    }

    @GetMapping("/{id}")
    public QuestionEntity getById(@PathVariable("id") Integer id){
        QuestionEntity question = service.findById(id);
        if(question == null) throw new ResourceNotFoundException("question", id);
        return question;
    }

    @GetMapping("/all")
    public Iterable<QuestionEntity> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete")
    public void delete(@Valid @RequestBody QuestionEntity question){
        service.delete(question);
    }
}
