package com.original.desafio.parser;

import com.original.desafio.dto.RouteDto;
import com.original.desafio.model.Route;

public class RouteParser {

    public static RouteParser get() {
        return new RouteParser();
    }

    public static Route dtoToEntity(RouteDto dto) {

        Route route = new Route();

        route.setSource(dto.getSource());
        route.setTarget(dto.getTarget());
        route.setDistance(dto.getDistance());

        return route;
    }

    public static RouteDto entityToDto(Route route) {

        RouteDto dto = RouteDto.builder()
                .distance(route.getDistance())
                .source(route.getSource())
                .target(route.getTarget())
                .build();

        return dto;
    }

}
