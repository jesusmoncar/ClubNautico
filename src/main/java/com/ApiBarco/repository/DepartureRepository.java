package com.ApiBarco.repository;

import com.ApiBarco.entity.Departures;
import com.ApiBarco.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartureRepository extends JpaRepository<Departures, Long> {
}
