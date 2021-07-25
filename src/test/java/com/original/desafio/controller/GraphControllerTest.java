package com.original.desafio.controller;

import com.original.desafio.dto.GraphDto;
import com.original.desafio.exception.GraphNotFoundException;
import com.original.desafio.model.Graph;
import com.original.desafio.model.Route;
import com.original.desafio.parser.GraphParser;
import com.original.desafio.service.GraphService;
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

@ExtendWith(SpringExtension.class)
class GraphControllerTest {

    @InjectMocks
    private GraphController controller;
    @Mock
    private GraphService graphServiceMock;

    @BeforeEach
    void setUp() {
        GraphDto dto = GraphParser.entityToDto(this.createGraph());
        dto.setId(1L);

        BDDMockito.when(graphServiceMock.findById(ArgumentMatchers.any()))
                .thenReturn(dto);

        BDDMockito.when(graphServiceMock.save(ArgumentMatchers.any()))
                .thenReturn(dto);
    }

    @Test
    @DisplayName("Save create Graph WhenSuccessful")
    void save_CreateGraph_WhenSuccessful() {

        GraphDto graphDto = controller.desafio1(null).getBody();
        Assertions.assertThat(graphDto).isNotNull();
        Assertions.assertThat(graphDto.getId()).isEqualTo(1L);

    }

    @Test
    @DisplayName("FindById Graph WhenSuccessful")
    void save_FindByIdGraph_WhenSuccessful() {

        GraphDto graphDto = controller.desafio2(null).getBody();
        Assertions.assertThat(graphDto).isNotNull();
        Assertions.assertThat(graphDto.getId()).isEqualTo(1L);

    }

    @Test
    @DisplayName("FindById throw GraphNotFoundException not find Graph")
    void FindById_ThrowGraphNotFoundException_NotFindGraph() {

        BDDMockito.when(graphServiceMock.findById(ArgumentMatchers.any()))
                .thenThrow(new GraphNotFoundException(1L));

        Assertions.assertThatExceptionOfType(GraphNotFoundException.class).isThrownBy(() ->
                this.controller.desafio2(1l)).withMessageContaining("Could not find graph " + 1);

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