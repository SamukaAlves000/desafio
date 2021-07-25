package com.original.desafio.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GraphDto {

    private Long id;
    private List<RouteDto> data;
}
