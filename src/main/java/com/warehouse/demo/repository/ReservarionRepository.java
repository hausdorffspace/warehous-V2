package com.warehouse.demo.repository;

import com.warehouse.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservarionRepository extends JpaRepository<Reservation,Long> {
}
