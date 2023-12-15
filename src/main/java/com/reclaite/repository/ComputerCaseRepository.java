package com.reclaite.repository;

import com.reclaite.model.ComputerCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerCaseRepository extends JpaRepository<ComputerCase, Long> {
}
