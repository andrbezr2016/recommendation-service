package com.andrbezr2016.library.recommendation.repository;

import com.andrbezr2016.library.recommendation.entity.NotableBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotableBookRepository extends JpaRepository<NotableBook, UUID> {

    @Query("SELECT bookId FROM NotableBook")
    List<UUID> findAllBookIds();
}
