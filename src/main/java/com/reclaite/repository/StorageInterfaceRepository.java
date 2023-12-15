package com.reclaite.repository;

import com.reclaite.model.StorageInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageInterfaceRepository extends JpaRepository<StorageInterface, Long> {
}
