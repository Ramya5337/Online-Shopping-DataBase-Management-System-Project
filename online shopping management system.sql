
use onlineshoppingsystem;
CREATE TABLE website (
  app_name varchar(20) primary key
);
 CREATE TABLE customer (
  c_id int(11) not null,
  user_name varchar(20) NOT NULL,
  password varchar(10) NOT NULL,
  mobile_no int(10),
  email varchar(20),
  address varchar(20) NOT NULL,
  app_name varchar(20),
  p_id int(11) not null,
  PRIMARY KEY (c_id),
  foreign key (app_name) references website(app_name),
  foreign key (p_id) references products(p_id)
) ;

CREATE TABLE admin (
  admin_id int(11) NOT NULL,
  name varchar(20) DEFAULT NULL,
  password varchar(20) DEFAULT NULL,
  app_name varchar(20),
  PRIMARY KEY (admin_id),
  foreign key (app_name) references website(app_name)
);

CREATE TABLE products (
  p_id int(11) NOT NULL,
  p_name varchar(20) NOT NULL,
  p_category varchar(10) DEFAULT NULL,
  p_availability varchar(10) DEFAULT NULL,
  p_price int(11) DEFAULT NULL,
  admin_id int(11) not null,
  PRIMARY KEY (p_id),
  foreign key (admin_id) references admin(admin_id)
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
  FOREIGN KEY (p_id) REFERENCES products (p_id)
);



