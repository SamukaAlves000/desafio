package com.original.desafio.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_ROUTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RoutePks.class)
@Builder
public class Route implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Id
    private String source;

    @Id
    private String target;

    private Long distance;
}
