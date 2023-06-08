package com.alenin.discussion.Controller;

import com.alenin.discussion.DTO.RelationDTO;
import com.alenin.discussion.Entity.RelationEntity;
import com.alenin.discussion.Exception.ResourceNotFoundException;
import com.alenin.discussion.Service.RelationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relation")
public class RelationController {


    private final RelationService service;


    public RelationController(RelationService service) {
        this.service = service;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public RelationEntity add(@Valid @RequestBody RelationDTO relation){
        return service.add(relation);
    }

    @PatchMapping("/update")
    public RelationEntity update(@Valid @RequestBody RelationEntity relation){
        return service.update(relation);
    }

    @GetMapping("/{id}")
    public RelationEntity getById(@PathVariable("id") Integer id){
        RelationEntity relation = service.findById(id);
        if(relation == null) throw new ResourceNotFoundException("relation", id);
        return relation;
    }

    @GetMapping("/all")
    public Iterable<RelationEntity> findAll(){
        return service.findAll();
    }

    @DeleteMapping("/delete")
    public void delete(@Valid @RequestBody RelationEntity relation){
        service.delete(relation);
    }
}
