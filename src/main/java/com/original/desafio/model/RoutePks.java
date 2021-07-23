package com.original.desafio.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoutePks implements Serializable {
    private String source;
    private String target ;
}
