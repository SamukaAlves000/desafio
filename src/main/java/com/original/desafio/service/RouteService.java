package com.original.desafio.service;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.dto.RouteDto;
import com.original.desafio.response.RouteStopResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final GraphService service;

    public ArrayList<RouteStopResponse> findAllRoutesOriginDestinationV2(String town1, String town2, Long maxStops, GraphDto dto){

        ArrayList<String> list = this.findAllRoutesOriginDestination(town1, town2, maxStops, dto);
        ArrayList<String>  response = new ArrayList<>();


        ArrayList<RouteStopResponse> routeStopResponses = new ArrayList<>();

        if(maxStops != null){
              response = list.stream().filter(s -> s.length() <= maxStops + 1).collect(Collectors.toCollection(ArrayList::new));
        }else{
            response = list;
        }

        response.forEach(s -> {
            RouteStopResponse routeStopResponse = RouteStopResponse.builder().route(s).stops(s.length() -1L).build();
            routeStopResponses.add(routeStopResponse);
        });

        return routeStopResponses;
    }


    public ArrayList<RouteStopResponse> findAllRoutesOriginDestinationToSaved2(Long graphId, String town1, String town2, Long maxStops) {
        GraphDto dto = service.findById(graphId);
        return this.findAllRoutesOriginDestinationV2(town1, town2, maxStops, dto);
    }

    public ArrayList<String> findAllRoutesOriginDestinationToSaved(Long graphId, String town1, String town2, Long maxStops) {
        GraphDto dto = service.findById(graphId);
        return this.findAllRoutesOriginDestination(town1, town2, maxStops, dto);
    }

    public ArrayList<String> findAllRoutesOriginDestination(String town1, String town2, Long maxStops, GraphDto dto) {

        ArrayList<String> routes = new ArrayList<>();

        Map<String, Integer> nos = this.getNos(dto);

        Map<String, ArrayList<RouteDto>> mapa = this.getMapa(nos, dto);

        ArrayList<RouteDto> startRoutes = mapa.get(town1);

        if (this.hasDirectConnection(startRoutes, town2)) {
            routes.add(town1 + town2);
        }


        for (int i = 0; i < startRoutes.size(); i++) {

            int indice = i;
            String chave = startRoutes.get(indice).getTarget();

            if (chave.equals(town2)) {
                continue;
            }

            ArrayList<RouteDto> response = mapa.get(chave);

            if (this.hasDirectConnection(response, town2)) {
                routes.add(town1 + chave + town2);
            }

            this.searchRoutes(chave, town2, mapa, routes, town1, maxStops);
        }

        return routes;
    }

    public ArrayList<String> searchRoutes(String town1, String town2, Map<String, ArrayList<RouteDto>> mapa,
                                          ArrayList<String> routes, String anterior, Long maxStops) {


        ArrayList<RouteDto> startRoutes = mapa.get(town1);

        for (int i = 0; i < startRoutes.size(); i++) {

            String chave = startRoutes.get(i).getTarget();

            if (chave.equals(town2) || anterior.contains(chave)) {
                continue;
            }

            if (this.hasDirectConnection(mapa.get(chave), town2)) {
                routes.add(anterior + town1 + chave + town2);
            }

            this.searchRoutes(chave, town2, mapa, routes, anterior + town1, maxStops);

        }

        return routes;
    }

    public Map<String, Integer> getNos(GraphDto dto) {

        Map<String, Integer> nos = new HashMap<>();

        dto.getData().stream().forEach(routeDto -> {
            if (!nos.containsKey(routeDto.getSource())) {
                nos.put(routeDto.getSource(), nos.size());
            }
            if (!nos.containsKey(routeDto.getTarget())) {
                nos.put(routeDto.getTarget(), nos.size());
            }
        });

        return nos;
    }

    public Map<String, ArrayList<RouteDto>> getMapa(Map<String, Integer> nos, GraphDto dto) {

        Map<String, ArrayList<RouteDto>> mapa = new HashMap<>();

        for (int i = 0; i < nos.size(); i++) {

            int indice = i;
            Object chave = nos.keySet().toArray()[indice];

            ArrayList<RouteDto> rotas = dto.getData()
                    .stream().
                            filter(routeDto -> routeDto.getSource().
                                    equals((chave))).
                            collect(Collectors.toCollection(ArrayList::new));

            mapa.put(chave.toString(), rotas);

        }

        return mapa;
    }

    public boolean hasDirectConnection(ArrayList<RouteDto> routeDtos, String town2) {
        int cont = routeDtos.stream().filter(routeDto -> routeDto.getTarget().equals(town2)).collect(Collectors.toCollection(ArrayList::new)).size();
        return cont != 0;
    }

    public Long distance(ArrayList<RouteDto> routeDtos, String town2) {
        Long distance = routeDtos.stream()
                .filter(routeDto -> routeDto.getTarget().equals(town2))
                .collect(Collectors.toCollection(ArrayList::new))
                .get(0).getDistance();

        return distance;
    }


}
