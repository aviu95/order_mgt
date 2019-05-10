create table users (
    id BIGINT default 0 PRIMARY KEY,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
);

ALTER TABLE orders
    ADD CONSTRAINT orders_users_fk FOREIGN KEY (user_id) REFERENCES users(id);
