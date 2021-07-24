package com.original.desafio.service;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.dto.RouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouteService {


    @Autowired
    GraphService service;

    public ArrayList<String> result2(Long graphId, String town1, String town2, Long maxStops) {
        GraphDto dto = service.findById(graphId);
        return this.result(town1, town2, maxStops, dto);
    }

    public ArrayList<String> result(String town1, String town2, Long maxStops, GraphDto dto) {

        ArrayList<String> routes = new ArrayList<>();

        Map<String, Integer> nos = this.getNos(dto);

        Map<String, ArrayList<RouteDto>> mapa = this.getMapa(nos, dto);

        ArrayList<RouteDto> startRoutes = mapa.get(town1);

        if (this.hasDirectConnection(startRoutes, town2)) {
            routes.add(town1 + town2);
            if (maxStops != null && routes.size() == maxStops) return routes;
        }


        for (int i = 0; i < startRoutes.size(); i++) {

            if (maxStops != null && routes.size() == maxStops) return routes;

            int indice = i;
            String chave = startRoutes.get(indice).getTarget();

            if (chave.equals(town2)) {
                continue;
            }

            ArrayList<RouteDto> response = mapa.get(chave);

            if (this.hasDirectConnection(response, town2)) {
                routes.add(town1 + chave + town2);
                if (maxStops != null && routes.size() == maxStops) return routes;
            }

            this.searchRoutes(chave, town2, mapa, routes, town1, maxStops);
        }

        return routes;
    }

    public ArrayList<String> searchRoutes(String town1, String town2, Map<String, ArrayList<RouteDto>> mapa,
                                          ArrayList<String> routes, String anterior, Long maxStops) {


        ArrayList<RouteDto> startRoutes = mapa.get(town1);

        for (int i = 0; i < startRoutes.size(); i++) {

            if (maxStops != null && routes.size() == maxStops) return routes;

            String chave = startRoutes.get(i).getTarget();

            if (chave.equals(town2) || anterior.contains(chave)) {
                continue;
            }

            if (this.hasDirectConnection(mapa.get(chave), town2)) {
                routes.add(anterior + town1 + chave + town2);
                if (maxStops != null && routes.size() == maxStops) return routes;
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
