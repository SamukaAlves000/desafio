package com.original.desafio.parser;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.model.Graph;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GraphParser {

    public static GraphParser get(){
        return new GraphParser();
    }

    public static GraphDto entityToDto(Graph entity){
        GraphDto dto = new GraphDto();
        dto.setId(entity.getId());
        dto.setData(entity.getRoutes().stream().map(RouteParser::entityToDto).collect(Collectors.toCollection(ArrayList::new)));
        return dto;
    }
}
