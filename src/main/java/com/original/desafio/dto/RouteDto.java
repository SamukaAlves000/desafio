package com.original.desafio.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RouteDto implements Serializable {

    private String source;
    private String target;
    private Long distance;
}
