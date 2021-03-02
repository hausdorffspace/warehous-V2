package com.warehouse.demo.repository;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface PianoRepository extends JpaRepository<Piano, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.avaliable=:borrowed AND p.user_id=:userId WHERE p.sku=:sku", nativeQuery = true)
    Integer rentPianoWithSKU(@Param("userId") User user, @Param("sku") String sku, @Param("borrowed") Boolean avaliable);

    @Query(value = "SELECT * FROM piano AS p WHERE p.sku=:sku",nativeQuery = true)
    Optional<Piano> findPianoBySku(@Param("sku") String sku);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE piano AS p SET p.avaliable=TRUE WHERE p.sku=:sku",nativeQuery = true)
    Integer returnPianoToTheWarehouseWithSku(@Param("sku") String sku);
}
