package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.AnswerDTO;
import com.alenin.discussion.Entity.AnswerEntity;
import com.alenin.discussion.Exception.ResourceNotFoundException;
import com.alenin.discussion.Service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService service;


    public AnswerController(AnswerService service) {
        this.service = service;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerEntity add(@Valid @RequestBody AnswerDTO answer){
        return service.add(answer);
    }

    @PatchMapping("/update")
    public AnswerEntity update(@Valid @RequestBody AnswerEntity answer){
        return service.update(answer);
    }

    @GetMapping("/{id}")
    public AnswerEntity getById(@PathVariable("id") Integer id){
        AnswerEntity answer = service.findById(id);
        if(answer == null) throw new ResourceNotFoundException("answer", id);
        return answer;
    }

    @GetMapping("/all")
    public Iterable<AnswerEntity> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete")
    public void delete(@Valid @RequestBody AnswerEntity answer){
        service.delete(answer);
    }


}
