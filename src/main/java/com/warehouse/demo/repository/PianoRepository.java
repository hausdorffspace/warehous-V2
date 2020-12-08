package com.warehouse.demo.repository;

import com.warehouse.demo.model.Piano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PianoRepository extends JpaRepository<Piano,Long> {
}
