INSERT INTO customer (id, name, email) VALUES
    ('11111111-1111-1111-1111-111111111111', 'Alice Smith', 'alice@example.com'),
    ('22222222-2222-2222-2222-222222222222', 'Bob Johnson', 'bob@example.com');

INSERT INTO orders (id, customerid, product, quantity, price, status) VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '11111111-1111-1111-1111-111111111111', 'Laptop', 1, 1200.00, 'NEW'),
    ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '22222222-2222-2222-2222-222222222222', 'Phone', 2, 800.00, 'PROCESSING');

