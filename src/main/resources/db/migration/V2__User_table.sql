create table users (
    id BIGINT  PRIMARY KEY  default 0,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL
);

ALTER TABLE orders
    ADD CONSTRAINT orders_users_fk FOREIGN KEY (user_id) REFERENCES users(id);
