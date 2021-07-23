package com.original.desafio.controller;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    RouteService service;

    @PostMapping("/from/{town1}/to/{town2}")
    public void desafio3(@PathVariable String town1, @PathVariable String town2, @RequestParam(required = false) Long maxStops, @RequestBody GraphDto dto){

        service.result(town1, town2, maxStops, dto);
    }
}
