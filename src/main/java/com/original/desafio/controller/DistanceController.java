package com.original.desafio.controller;

import com.original.desafio.dto.DistanceDto;
import com.original.desafio.response.DistanceResponse;
import com.original.desafio.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    DistanceService service;


    @PostMapping()
    public ResponseEntity<DistanceResponse> desafio5(@RequestBody DistanceDto dto) {
        return new ResponseEntity<>(service.result(dto), HttpStatus.OK);
    }

    @PostMapping("/{graphId}")
    public ResponseEntity<DistanceResponse> desafio6(@PathVariable Long graphId, @RequestBody DistanceDto dto) {
        return new ResponseEntity<>(service.result2(graphId, dto), HttpStatus.OK);
    }
}
