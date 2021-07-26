package com.original.desafio.controller;

import com.original.desafio.service.RouteService;
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

@ExtendWith(SpringExtension.class)
class RouteControllerTest {

    @InjectMocks
    private RouteController controller;
    @Mock
    private RouteService routeServiceMock;

    @BeforeEach
    void setUp() {

        ArrayList<String> list = new ArrayList<>();
        list.add("ABD");
        list.add("ACB");
        list.add("AD");

        BDDMockito.when(routeServiceMock.findAllRoutesOriginDestination(ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyLong(), ArgumentMatchers.any()))
                .thenReturn(list);

        BDDMockito.when(routeServiceMock.findAllRoutesOriginDestinationToSaved(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyLong()))
                .thenReturn(list);
    }

    @Test
    @DisplayName("Find All Routes Origin Destination WhenSuccessful")
    void findAllRoutesOriginDestination_WhenSuccessful() {

        ArrayList<String> list = controller.desafio3V1("A", "D", 1L, null).getBody();

        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list.size()).isEqualTo(3);
        Assertions.assertThat(list.get(0)).isEqualTo("ABD");
    }

    @Test
    @DisplayName("Find All Routes Origin Destination to save WhenSuccessful")
    void findAllRoutesOriginDestinationToSave_WhenSuccessful() {

        ArrayList<String> list = controller.desafio4V1(1L, "A", "D", 1L).getBody();

        Assertions.assertThat(list).isNotEmpty();
        Assertions.assertThat(list.size()).isEqualTo(3);
        Assertions.assertThat(list.get(0)).isEqualTo("ABD");
    }
}