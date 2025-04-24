package com.andrbezr2016.library.recommendation.entity;

import com.andrbezr2016.library.recommendation.model.ReadingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Check(constraints = "score > 0 AND score <= 5")
@Entity
@Table(name = "notable_books")
public class NotableBook {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;
    @Column(name = "book_id", nullable = false, unique = true)
    private UUID bookId;
    @Column(name = "score")
    private Integer score;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReadingStatus status;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "notable_book_id")
    private List<Note> notes;
}
