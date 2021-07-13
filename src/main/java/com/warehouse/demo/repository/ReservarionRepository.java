package com.warehouse.demo.repository;

import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservarionRepository extends JpaRepository<Reservation,Long> {

    @Query(value = "SELECT * FROM reservation AS r WHERE r.piano_id = (SELECT id FROM piano AS p WHERE p.sku=:sku)", nativeQuery = true)
    List<Reservation> reservationListOfPianoWithSKU(@Param("sku") String sku);

    @Query(value = "SELECT * FROM reservation AS r WHERE r.user_id=:user_id", nativeQuery = true)
    List<Reservation> getAllUserReservation(@Param("user_id") Long userId);

}
