package com.andrbezr2016.library.recommendation.repository;

import com.andrbezr2016.library.recommendation.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {
}
