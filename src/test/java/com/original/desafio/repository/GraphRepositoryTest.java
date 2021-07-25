package com.original.desafio.repository;

import com.original.desafio.model.Graph;
import com.original.desafio.model.Route;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Graph Repository")
class GraphRepositoryTest {

    @Autowired
    private GraphRepository repository;

    @Test
    @DisplayName("Save create Graph WhenSuccessful")
    void save_CreateGraph_WhenSuccessful() {

        Graph graph = this.createGraph();

        Graph graphSaved = this.repository.save(graph);

        Assertions.assertThat(graphSaved).isNotNull();

    }

    @Test
    @DisplayName("Save update Graph WhenSuccessful")
    void save_UpdateGraph_WhenSuccessful() {

        Graph graph = this.createGraph();
        Graph graphSaved = this.repository.save(graph);

        graphSaved.getRoutes().get(0).setDistance(20L);

        Graph graphUpdate = this.repository.save(graphSaved);

        Assertions.assertThat(graphUpdate).isNotNull();
        Assertions.assertThat(graphUpdate.getRoutes().get(0).getDistance()).isEqualTo(20L);

    }

    @Test
    @DisplayName("Save delete Graph WhenSuccessful")
    void save_DeleteGraph_WhenSuccessful() {

        Graph graph = this.createGraph();
        Graph graphSaved = this.repository.save(graph);

        this.repository.delete(graphSaved);

        Optional<Graph> graphOptional = this.repository.findById(graph.getId());
        Assertions.assertThat(graphOptional).isEmpty();


    }

    private Graph createGraph() {

        List<Route> routes = new ArrayList<>();

        routes.add(Route
                .builder()
                .distance(10L)
                .source("A")
                .target("B")
                .build());


        return Graph
                .builder()
                .routes(routes)
                .build();
    }

}