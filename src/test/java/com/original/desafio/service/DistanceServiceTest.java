package com.original.desafio.service;

import com.original.desafio.dto.DistanceDto;
import com.original.desafio.dto.RouteDto;
import com.original.desafio.util.RouteCreator;
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
class DistanceServiceTest {

    @InjectMocks
    private DistanceService service;

    @Mock
    private RouteService routeServiceMock;


    @BeforeEach
    void setUp() {
        ArrayList<String> list = new ArrayList<>();
        list.add("ABC");
        list.add("ADC");
        BDDMockito.when(routeServiceMock.findAllRoutesOriginDestination(
                ArgumentMatchers.any(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.any()))
                .thenReturn(list);

    }

    @Test
    @DisplayName("Minimum Distance Between Two Paths WhenSuccessful")
    void minimumDistanceBetweenTwoPaths_WhenSuccessful() {

        String town1 = "A";
        String town2 = "C";

        ArrayList<String> path = new ArrayList<>();
        path.add("A");
        path.add("B");
        path.add("C");

        List<RouteDto> data = RouteCreator.getRoutesDtos();

        DistanceDto dto = DistanceDto
                .builder()
                .path(path)
                .data(data)
                .build();
        service.minimumDistanceBetweenTwoPaths(town1, town2, dto);
    }

    @Test
    @DisplayName("Distance From Specific Path To Save WhenSuccessful")
    void distanceFromSpecificPathToSave_WhenSuccessful() {

        String town1 = "A";
        String town2 = "C";

        ArrayList<String> path = new ArrayList<>();
        path.add("A");
        path.add("B");
        path.add("C");

        List<RouteDto> data = RouteCreator.getRoutesDtos();

        DistanceDto dto = DistanceDto
                .builder()
                .path(path)
                .data(data)
                .build();
        service.distanceFromSpecificPath(dto);
    }

}