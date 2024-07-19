
use onlineshoppingsystem;
CREATE TABLE website (
  app_name varchar(20) NOT NULL
);
 CREATE TABLE customer (
  c_id int(11) not null,
  user_name varchar(20) NOT NULL,
  password varchar(10) NOT NULL,
  mobile_no int(10),
  email varchar(20),
  address varchar(20) NOT NULL,
  PRIMARY KEY (c_id)
) ;

CREATE TABLE admin (
  admin_id int(11) NOT NULL,
  name varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  PRIMARY KEY (admin_id)
);

CREATE TABLE products (
  p_id int(11) NOT NULL,
  p_name varchar(20) NOT NULL,
  p_category varchar(10) DEFAULT NULL,
  p_availability varchar(10) DEFAULT NULL,
  p_price int(11) DEFAULT NULL,
  PRIMARY KEY (p_id)
);

CREATE TABLE login (
  c_id int(11) DEFAULT NULL,
  user_name varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  forgot_password varchar(20) DEFAULT NULL,
  sign_up varchar(10) DEFAULT NULL,
  FOREIGN KEY (c_id) REFERENCES customer (c_id)
);

CREATE TABLE shopping_cart (
  p_id int(11) DEFAULT NULL,
  p_name varchar(20) NOT NULL,
  billing_products int(11) DEFAULT NULL,
  total_price int(11) DEFAULT NULL,
  KEY p_id (p_id),
  FOREIGN KEY (p_id) REFERENCES products (p_id)
);



