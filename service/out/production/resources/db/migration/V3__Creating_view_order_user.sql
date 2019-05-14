create or replace view view_order_user_details as
    select o.*, u.first_name, u.last_name from orders o join users u on o.user_id = u.id;
