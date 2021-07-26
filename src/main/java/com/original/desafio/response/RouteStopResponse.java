package com.original.desafio.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteStopResponse {

    private Long stops;
    private String route;
}
