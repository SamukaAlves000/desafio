package com.original.desafio.service;

import com.original.desafio.dto.DistanceDto;
import com.original.desafio.dto.GraphDto;
import com.original.desafio.dto.RouteDto;
import com.original.desafio.response.DistanceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DistanceService {

    @Autowired
    RouteService service;

    public DistanceResponse result(DistanceDto dto) {

        if (dto.getPath().size() <= 1) return DistanceResponse.builder().distance(0L).build();

        String town1 = dto.getPath().get(0);
        String town2 = dto.getPath().get(dto.getPath().size() - 1);
        Long maxStops = null;
        GraphDto graphDto = GraphDto.builder().data(dto.getData()).build();

        ArrayList<String> response = service.result(town1, town2, maxStops, graphDto);

        String route = dto.getPath().stream().map(Objects::toString).collect(Collectors.joining(""));

        if (!response.contains(route)) return DistanceResponse.builder().distance(-1L).build();

        return distance(dto.getPath(), graphDto);
    }

    private DistanceResponse distance(ArrayList<String> routes, GraphDto dto) {

        Long distance = 0L;

        Map<String, Integer> nos = service.getNos(dto);
        Map<String, ArrayList<RouteDto>> mapa = service.getMapa(nos, dto);


        for (int i = 1; i < routes.size(); i++) {

            String town1 = routes.get(i - 1);
            String town2 = routes.get(i);

            ArrayList<RouteDto> response = mapa.get(town1);
            distance = distance + service.distance(response, town2);

        }

        return DistanceResponse.builder().distance(distance).build();
    }
}
