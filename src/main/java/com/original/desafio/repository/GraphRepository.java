package com.original.desafio.repository;

import com.original.desafio.model.Graph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends JpaRepository<Graph, Long> {

}
