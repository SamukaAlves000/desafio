package com.original.desafio.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class DistanceMinResponse {

    private Long distance;
    ArrayList<String> path;
}
