package com.original.desafio.service;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.dto.RouteDto;
import com.original.desafio.util.RouteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Map;

@ExtendWith(SpringExtension.class)
class RouteServiceTest {

    @InjectMocks
    private RouteService service;

    @Test
    @DisplayName("FindAllRoutesOriginDestination WhenSuccessful")
    void FindAllRoutesOriginDestination_WhenSuccessful() {

        String town1 = "A";
        String town2 = "C";
        Long maxStops = null;
        GraphDto dto = GraphDto
                .builder()
                .data(RouteCreator.getRoutesDtos())
                .build();

        ArrayList<String> routes = this.service.findAllRoutesOriginDestination(town1, town2, maxStops, dto);
        Assertions.assertThat(routes.size()).isEqualTo(4);
        routes = this.service.findAllRoutesOriginDestination(town1, town2, 2L, dto);
        Assertions.assertThat(routes.size()).isEqualTo(2);


    }

    @Test
    @DisplayName("hasDirectConnection WhenSuccessful")
    void hasDirectConnection_WhenSuccessful() {

        ArrayList<RouteDto> routes = new ArrayList<>();

        RouteDto route = RouteDto.builder()
                .distance(10L)
                .source("A")
                .target("B")
                .build();


        String town2 = "B";
        routes.add(route);
        boolean isDirectConnection = service.hasDirectConnection(routes, town2);
        Assertions.assertThat(isDirectConnection).isTrue();


        town2 = "C";
        routes.add(route);
        isDirectConnection = service.hasDirectConnection(routes, town2);
        Assertions.assertThat(isDirectConnection).isFalse();

    }

    @Test
    @DisplayName("Distance WhenSuccessful")
    void distance_WhenSuccessful() {

        ArrayList<RouteDto> routes = new ArrayList<>();

        RouteDto route = RouteDto.builder()
                .distance(10L)
                .source("A")
                .target("B")
                .build();


        String town2 = "B";
        routes.add(route);

        Long distance = service.distance(routes, town2);
        Assertions.assertThat(distance).isEqualTo(10L);

    }

    @Test
    @DisplayName("GetMapa WhenSuccessful")
    void getMapa_WhenSuccessful() {

        GraphDto dto = GraphDto
                .builder()
                .data(RouteCreator.getRoutesDtos())
                .build();


        Map<String, Integer> nos = this.service.getNos(dto);

        Map<String, ArrayList<RouteDto>> mapa = service.getMapa(nos, dto);

        Assertions.assertThat(mapa.get("A").size()).isEqualTo(3);
        Assertions.assertThat(mapa.get("B").size()).isEqualTo(1);
        Assertions.assertThat(mapa.get("C").size()).isEqualTo(2);


    }


}