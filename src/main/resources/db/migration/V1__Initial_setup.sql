create table orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    item_id BIGINT,
    order_desc TEXT,
    order_date TIMESTAMP
);
