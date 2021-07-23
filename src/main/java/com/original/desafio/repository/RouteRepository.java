package com.original.desafio.repository;

import com.original.desafio.model.Route;
import com.original.desafio.model.RoutePks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, RoutePks> {
}
