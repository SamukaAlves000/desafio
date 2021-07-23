package com.original.desafio.controller;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graph")
public class GraphController {

    @Autowired
    private GraphService service;

    @PostMapping
    ResponseEntity<GraphDto> desafio1(@RequestBody GraphDto dto) {
        return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<GraphDto> desafio2(@PathVariable Long id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

}
