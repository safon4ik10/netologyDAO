SELECT product_name FROM ORDERS as orders
                             join CUSTOMERS as customers on customers.id = orders.customer_id
where lower(customers.name) = lower(:name);