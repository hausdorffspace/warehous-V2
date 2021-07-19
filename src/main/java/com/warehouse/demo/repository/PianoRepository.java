package com.warehouse.demo.repository;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.Reservation;
import com.warehouse.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface PianoRepository extends JpaRepository<Piano, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.available_now=:borrowed AND p.user_id=:userId WHERE p.sku=:sku", nativeQuery = true)
    Integer rentPianoWithSKU(@Param("userId") User user, @Param("sku") String sku, @Param("borrowed") Boolean available);

    @Query(value = "SELECT * FROM piano AS p WHERE p.sku=:sku",nativeQuery = true)
    Optional<Piano> findPianoBySku(@Param("sku") String sku);

    @Deprecated
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.avaliable=TRUE WHERE p.sku=:sku",nativeQuery = true)
    Integer returnPianoToTheWarehouseWithSku(@Param("sku") String sku);

    @Query(value = "SELECT * FROM piano AS p WHERE p.available_now = 1" , nativeQuery = true)
    List<Piano> findAllAvailablePiano();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.available_now=FALSE WHERE p.sku=:sku", nativeQuery = true)
    Integer setAvailablePianoForFalse(@Param("sku") String sku);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.available_now=TRUE WHERE p.sku=:sku", nativeQuery = true)
    Integer setAvailablePianoForTrue(@Param("sku") String sku);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.available_now=TRUE WHERE p.sku IN :pianosId", nativeQuery = true)
    Integer setAvailablePianoForTrue(@Param("pianosId") List<Long> pianosId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.available_now=FALSE WHERE p.sku IN :pianosId", nativeQuery = true)
    Integer setAvailablePianoForFalse(@Param("pianosId") List<Long> pianosId);
}
