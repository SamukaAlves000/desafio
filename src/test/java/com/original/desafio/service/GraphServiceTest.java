package com.original.desafio.service;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.exception.GraphNotFoundException;
import com.original.desafio.model.Graph;
import com.original.desafio.model.Route;
import com.original.desafio.parser.GraphParser;
import com.original.desafio.repository.GraphRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
class GraphServiceTest {

    @InjectMocks
    private GraphService service;
    @Mock
    private GraphRepository graphRepositoryMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(graphRepositoryMock.save(ArgumentMatchers.any()))
                .thenReturn(this.createGraph());

        BDDMockito.when(graphRepositoryMock.findById(ArgumentMatchers.any()))
                .thenReturn(Optional.of(this.savedGraph()));

    }

    @Test
    @DisplayName("Save create Graph WhenSuccessful")
    void save_CreateGraph_WhenSuccessful() {

        GraphDto dto = GraphParser.entityToDto(this.createGraph());

        GraphDto graphDto = service.save(dto);
        Assertions.assertThat(graphDto.getData().get(0)).isNotNull();
        Assertions.assertThat(graphDto.getData().get(0).getDistance()).isEqualTo(10L);
        Assertions.assertThat(graphDto.getData().get(0).getSource()).isEqualTo("A");
        Assertions.assertThat(graphDto.getData().get(0).getTarget()).isEqualTo("B");

    }

    @Test
    @DisplayName("FindById Graph WhenSuccessful")
    void save_FindByIdGraph_WhenSuccessful() {

        GraphDto graphDto = service.findById(1L);
        Assertions.assertThat(graphDto.getData().get(0)).isNotNull();
        Assertions.assertThat(graphDto.getData().get(0).getDistance()).isEqualTo(10L);
        Assertions.assertThat(graphDto.getData().get(0).getSource()).isEqualTo("A");
        Assertions.assertThat(graphDto.getData().get(0).getTarget()).isEqualTo("B");

    }

    @Test
    @DisplayName("FindById throw GraphNotFoundException not find Graph")
    void FindById_ThrowGraphNotFoundException_NotFindGraph() {

        BDDMockito.when(graphRepositoryMock.findById(ArgumentMatchers.any()))
                .thenThrow(new GraphNotFoundException(1L));

        Assertions.assertThatExceptionOfType(GraphNotFoundException.class).isThrownBy(() ->
                this.service.findById(1l)).withMessageContaining("Could not find graph " + 1);

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

    private Graph savedGraph() {

        List<Route> routes = new ArrayList<>();

        routes.add(Route
                .builder()
                .distance(10L)
                .source("A")
                .target("B")
                .build());


        return Graph
                .builder()
                .id(1L)
                .routes(routes)
                .build();
    }

}