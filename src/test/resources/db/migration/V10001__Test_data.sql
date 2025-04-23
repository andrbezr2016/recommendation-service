INSERT INTO notable_books (id, book_id, score, status, created_at, modified_at) VALUES
('6ee73aa1-4217-4683-9ca7-891e10bc98f3', '9cf94139-3bc4-4f25-9154-12961f53f46f', 5, 'COMPLETED', '2020-01-01T12:00:00.000', '2020-01-01T14:00:00.000'),
('a9875c41-b8fe-49df-ad6f-62445f74d5ef', '4d9113af-7af9-486a-938e-2b53882077d9', 3, 'COMPLETED', '2020-05-01T12:00:00.000', '2020-05-01T14:00:00.000'),
('2379d45e-77ee-4f36-8409-1ff67ace446c', '646d0dec-4f90-48dd-9481-058d161f9c02', 1, 'COMPLETED', '2020-03-01T12:00:00.000', '2020-03-01T14:00:00.000'),
('ad0ef6df-7d2d-457d-b3d9-15de9dc3e201', 'e881d0a3-e948-4c16-a6a6-16e6e1b3e00c', null, 'IN_PROGRESS', '2020-02-01T12:00:00.000', '2020-02-01T14:00:00.000');

INSERT INTO notes (id, notable_book_id, content, created_at, modified_at) VALUES
('aa532f27-26a6-4c41-8299-e5ce43ad1985', '6ee73aa1-4217-4683-9ca7-891e10bc98f3', 'It is an interesting book', '2020-01-01T14:00:00.000', null),
('8c81ccda-77f6-4677-a762-b539e7280b43', '2379d45e-77ee-4f36-8409-1ff67ace446c', 'It is a bad book', '2020-03-01T14:00:00.000', null);
