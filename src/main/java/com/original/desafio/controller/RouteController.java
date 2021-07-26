package com.original.desafio.controller;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.response.RouteStopResponse;
import com.original.desafio.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService service;

    @PostMapping("/from/{town1}/to/{town2}")
    public ResponseEntity<ArrayList<RouteStopResponse>> desafio3(@PathVariable String town1,
                                                                 @PathVariable String town2,
                                                                 @RequestParam(required = false) Long maxStops,
                                                                 @RequestBody GraphDto dto) {
        return new ResponseEntity<>(service.findAllRoutesOriginDestinationV2(town1, town2, maxStops, dto), HttpStatus.OK);
    }

    @PostMapping("/{graphId}/from/{town1}/to/{town2}")
    public ResponseEntity<ArrayList<RouteStopResponse>> desafio4(@PathVariable Long graphId,
                                                        @PathVariable String town1,
                                                        @PathVariable String town2,
                                                        @RequestParam(required = false) Long maxStops) {
        return new ResponseEntity<>(service.findAllRoutesOriginDestinationToSaved2(graphId,
                town1, town2, maxStops), HttpStatus.OK);
    }

    @PostMapping("/from2/{town1}/to/{town2}")
    public ResponseEntity<ArrayList<String>> desafio3V1(@PathVariable String town1,
                                                      @PathVariable String town2,
                                                      @RequestParam(required = false) Long maxStops,
                                                      @RequestBody GraphDto dto) {
        return new ResponseEntity<>(service.findAllRoutesOriginDestination(town1, town2, maxStops, dto), HttpStatus.OK);
    }



    @PostMapping("/{graphId}/from2/{town1}/to/{town2}")
    public ResponseEntity<ArrayList<String>> desafio4V1(@PathVariable Long graphId,
                                                      @PathVariable String town1,
                                                      @PathVariable String town2,
                                                      @RequestParam(required = false) Long maxStops) {
        return new ResponseEntity<>(service.findAllRoutesOriginDestinationToSaved(graphId,
                town1, town2, maxStops), HttpStatus.OK);
    }
}
