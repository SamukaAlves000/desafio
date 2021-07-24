package com.original.desafio.controller;

import com.original.desafio.dto.DistanceDto;
import com.original.desafio.response.DistanceMinResponse;
import com.original.desafio.response.DistanceResponse;
import com.original.desafio.service.DistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/distance")
public class DistanceController {

    @Autowired
    DistanceService service;


    @PostMapping()
    public ResponseEntity<DistanceResponse> desafio5(@RequestBody DistanceDto dto) {
        return new ResponseEntity<>(service.distanceFromSpecificPath(dto), HttpStatus.OK);
    }

    @PostMapping("/{graphId}")
    public ResponseEntity<DistanceResponse> desafio6(@PathVariable Long graphId, @RequestBody DistanceDto dto) {
        return new ResponseEntity<>(service.distanceFromSpecificPathToSave(graphId, dto), HttpStatus.OK);
    }

    @PostMapping("/from/{town1}/to/{town2}")
    public ResponseEntity<DistanceMinResponse> desafio7(@PathVariable String town1, @PathVariable String town2, @RequestBody DistanceDto dto) {
        return new ResponseEntity<>(service.minimumDistanceBetweenTwoPaths(town1,town2,dto), HttpStatus.OK);
    }

    @PostMapping("/{graphId}/from/{town1}/to/{town2}")
    public ResponseEntity<DistanceMinResponse> desafio8(@PathVariable String town1, @PathVariable String town2, @PathVariable Long graphId) {
        return new ResponseEntity<>(service.minimumDistanceBetweenTwoPathsToSaved(town1,town2, graphId), HttpStatus.OK);
    }
}
