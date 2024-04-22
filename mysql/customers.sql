
use spring_demo1;

CREATE TABLE if not exists customers (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  age int NOT NULL,
  created_date timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

INSERT INTO customers
(name,age) VALUES ("Buzz Lightyears", 35);

INSERT INTO customers
(name,age) VALUES ("Biz Happyyears", 99);

SELECT * FROM customers;
