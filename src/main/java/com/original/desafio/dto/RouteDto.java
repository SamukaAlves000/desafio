package com.original.desafio.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RouteDto implements Serializable {

    private String source;
    private String target;
    private Long distance;
}
