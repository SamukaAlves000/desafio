package com.original.desafio.controller;

import com.original.desafio.response.DistanceMinResponse;
import com.original.desafio.response.DistanceResponse;
import com.original.desafio.service.DistanceService;
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
class DistanceControllerTest {

    @InjectMocks
    private DistanceController controller;
    @Mock
    private DistanceService distanceServiceMock;

    @BeforeEach
    void setUp() {

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        DistanceResponse distanceResponse = DistanceResponse.builder().build();
        distanceResponse.setDistance(9L);
        DistanceMinResponse minResponse = DistanceMinResponse.builder()
                .distance(9L)
                .path(list)
                .build();

        BDDMockito.when(distanceServiceMock.distanceFromSpecificPath(ArgumentMatchers.any()))
                .thenReturn(distanceResponse);
        BDDMockito.when(distanceServiceMock.distanceFromSpecificPathToSave(ArgumentMatchers.anyLong(), ArgumentMatchers.any()))
                .thenReturn(distanceResponse);
        BDDMockito.when(distanceServiceMock.minimumDistanceBetweenTwoPaths(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any()))
                .thenReturn(minResponse);
        BDDMockito.when(distanceServiceMock.minimumDistanceBetweenTwoPathsToSaved(
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.any()))
                .thenReturn(minResponse);
    }

    @Test
    @DisplayName("Distance From Specific Path WhenSuccessful")
    void distanceFromSpecificPath_WhenSuccessful() {
        Long distance = controller.desafio5(null).getBody().getDistance();
        Assertions.assertThat(distance).isEqualTo(9L);

    }

    @Test
    @DisplayName("Distance From Specific Path To Save WhenSuccessful")
    void distanceFromSpecificPathToSave_WhenSuccessful() {
        Long distance = controller.desafio6(1L, null).getBody().getDistance();
        Assertions.assertThat(distance).isEqualTo(9L);

    }

    @Test
    @DisplayName("Minimum Distance Between Two Paths WhenSuccessful")
    void minimumDistanceBetweenTwoPaths_WhenSuccessful() {
        Long distance = controller.desafio7("A", "B", null).getBody().getDistance();
        Assertions.assertThat(distance).isEqualTo(9L);

    }

    @Test
    @DisplayName("Minimum Distance Between Two Paths To Saved WhenSuccessful")
    void minimumDistanceBetweenTwoPathstToSaved_WhenSuccessful() {
        Long distance = controller.desafio8("A", "B", null).getBody().getDistance();
        Assertions.assertThat(distance).isEqualTo(9L);

    }
}