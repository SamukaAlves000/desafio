package com.original.desafio.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DistanceDto {

    private ArrayList<String> path;
    private List<RouteDto> data;

}
