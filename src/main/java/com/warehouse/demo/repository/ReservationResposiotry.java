package com.warehouse.demo.repository;

import com.warehouse.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationResposiotry extends JpaRepository<Reservation,Long> {

}
