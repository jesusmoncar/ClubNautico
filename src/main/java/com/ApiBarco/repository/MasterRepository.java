package com.ApiBarco.repository;


import com.ApiBarco.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
    @Query("SELECT COUNT(m) > 0 FROM Master m WHERE m.permit_number = :permitNumber")
    boolean existsByPermitNumber(Long permitNumber);
}
