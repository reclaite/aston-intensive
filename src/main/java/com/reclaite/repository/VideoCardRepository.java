package com.reclaite.repository;

import com.reclaite.model.VideoCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoCardRepository extends JpaRepository<VideoCard, Long> {
}
