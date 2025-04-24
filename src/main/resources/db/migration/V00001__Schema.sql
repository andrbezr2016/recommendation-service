CREATE TABLE IF NOT EXISTS notable_books (
    id uuid NOT NULL,
    book_id uuid NOT NULL UNIQUE,
    score integer,
    status varchar NOT NULL,
    created_at timestamp NOT NULL,
    modified_at timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS notes (
    id uuid NOT NULL,
    notable_book_id uuid REFERENCES notable_books(id) ON DELETE CASCADE,
    content varchar NOT NULL,
    created_at timestamp NOT NULL,
    modified_at timestamp,
    PRIMARY KEY (id)
);

ALTER TABLE notable_books ADD CONSTRAINT score_constraint CHECK (score > 0 AND score <= 5)