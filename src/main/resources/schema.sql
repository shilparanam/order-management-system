CREATE TABLE customers (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    id UUID PRIMARY KEY,
    customerid UUID NOT NULL,
    product VARCHAR(255) NOT NULL,
    quantity INTEGER NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_customer FOREIGN KEY (customerid) REFERENCES customer(id)
);

