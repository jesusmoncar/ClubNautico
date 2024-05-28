package com.ApiBarco.repository;

import com.ApiBarco.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {



}
