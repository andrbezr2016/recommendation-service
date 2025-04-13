package com.andrbezr2016.library.recommendation.repository;

import com.andrbezr2016.library.recommendation.entity.NotableBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NotableBookRepository extends JpaRepository<NotableBook, UUID> {
}
