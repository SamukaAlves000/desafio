package com.original.desafio.util;

import com.original.desafio.dto.RouteDto;
import com.original.desafio.model.Route;
import com.original.desafio.parser.RouteParser;

import java.util.ArrayList;
import java.util.List;

public class RouteCreator {

    public static List<RouteDto> getRoutesDtos() {
        List<RouteDto> routes = new ArrayList<>();

        Route r1 = Route.builder()
                .source("A")
                .target("B")
                .distance(5L)
                .build();

        Route r2 = Route.builder()
                .source("B")
                .target("C")
                .distance(4L)
                .build();

        Route r3 = Route.builder()
                .source("C")
                .target("D")
                .distance(8L)
                .build();

        Route r4 = Route.builder()
                .source("D")
                .target("C")
                .distance(8L)
                .build();

        Route r5 = Route.builder()
                .source("D")
                .target("E")
                .distance(6L)
                .build();

        Route r6 = Route.builder()
                .source("A")
                .target("D")
                .distance(5L)
                .build();

        Route r7 = Route.builder()
                .source("C")
                .target("E")
                .distance(8L)
                .build();

        Route r8 = Route.builder()
                .source("E")
                .target("B")
                .distance(3L)
                .build();

        Route r9 = Route.builder()
                .source("A")
                .target("E")
                .distance(7L)
                .build();

        routes.add(RouteParser.entityToDto(r1));
        routes.add(RouteParser.entityToDto(r2));
        routes.add(RouteParser.entityToDto(r3));
        routes.add(RouteParser.entityToDto(r4));
        routes.add(RouteParser.entityToDto(r5));
        routes.add(RouteParser.entityToDto(r6));
        routes.add(RouteParser.entityToDto(r7));
        routes.add(RouteParser.entityToDto(r8));
        routes.add(RouteParser.entityToDto(r9));

        return routes;
    }
}
