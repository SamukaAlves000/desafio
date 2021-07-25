package com.original.desafio.service;

import com.original.desafio.dto.DistanceDto;
import com.original.desafio.dto.GraphDto;
import com.original.desafio.dto.RouteDto;
import com.original.desafio.response.DistanceMinResponse;
import com.original.desafio.response.DistanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistanceService {

    private final RouteService service;

    private final GraphService graphService;

    public DistanceMinResponse minimumDistanceBetweenTwoPathsToSaved(String town1, String town2, Long graphId) {

        GraphDto graphDto = graphService.findById(graphId);

        DistanceDto distanceDto = DistanceDto
                .builder()
                .data(graphDto.getData())
                .build();

        return minimumDistanceBetweenTwoPaths(town1, town2, distanceDto);

    }

    public DistanceMinResponse minimumDistanceBetweenTwoPaths(String town1, String town2, DistanceDto dto) {

        if (town1.equals(town2)) return DistanceMinResponse
                .builder()
                .distance(0L)
                .path(dto.getPath())
                .build();


        Long maxStops = null;
        GraphDto graphDto = GraphDto.builder().data(dto.getData()).build();

        ArrayList<String> response = service.findAllRoutesOriginDestination(town1, town2, maxStops, graphDto);

        if (response.size() == 0) return DistanceMinResponse
                .builder()
                .distance(-1L)
                .path(dto.getPath())
                .build();

        ArrayList<DistanceMinResponse> distanceMinResponses = new ArrayList<>();

        response.forEach(s -> {

            ArrayList<String> routes = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                routes.add(String.valueOf(s.charAt(i)));
            }


            Long distance = this.distance(routes, graphDto).getDistance();

            DistanceMinResponse minResponse = DistanceMinResponse
                    .builder()
                    .path(routes)
                    .distance(distance)
                    .build();

            distanceMinResponses.add(minResponse);

        });

        return Collections.min(distanceMinResponses, Comparator.comparing(c -> c.getDistance()));
    }

    public DistanceResponse distanceFromSpecificPathToSave(Long graphId, DistanceDto dto) {

        GraphDto graphDto = graphService.findById(graphId);
        dto.setData(graphDto.getData());

        return this.distanceFromSpecificPath(dto);
    }

    public DistanceResponse distanceFromSpecificPath(DistanceDto dto) {

        if (dto.getPath().size() <= 1) return DistanceResponse.builder().distance(0L).build();

        String town1 = dto.getPath().get(0);
        String town2 = dto.getPath().get(dto.getPath().size() - 1);
        Long maxStops = null;
        GraphDto graphDto = GraphDto.builder().data(dto.getData()).build();

        ArrayList<String> response = service.findAllRoutesOriginDestination(town1, town2, maxStops, graphDto);

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
