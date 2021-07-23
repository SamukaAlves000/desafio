package com.original.desafio.service;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.exception.GraphNotFoundException;
import com.original.desafio.model.Graph;
import com.original.desafio.parser.GraphParser;
import com.original.desafio.parser.RouteParser;
import com.original.desafio.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class GraphService {

    @Autowired
    GraphRepository repository;

    public GraphDto findById(Long id){

        Graph graph = repository.findById(id).orElseThrow(() -> new GraphNotFoundException(id));
        return GraphParser.entityToDto(graph);
    }

    public GraphDto save(GraphDto request){

        Graph graph = new Graph();
        graph.setRoutes(request.getData().stream()
                .map(RouteParser::dtoToEntity).
                        collect(Collectors.toCollection(ArrayList::new)));

        Graph saved = repository.save(graph);

        return prepareReturn(saved)  ;
    }

    private GraphDto prepareReturn(Graph graph){

        GraphDto dto = new GraphDto();
        dto.setId(graph.getId());
        dto.setData(graph.getRoutes()
                .stream().map(RouteParser::entityToDto).
                        collect(Collectors.toCollection(ArrayList::new)));
        return dto;
    }
}
