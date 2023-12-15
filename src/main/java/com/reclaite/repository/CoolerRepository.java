package com.reclaite.repository;

import com.reclaite.model.Cooler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoolerRepository extends JpaRepository<Cooler, Long> {
}
