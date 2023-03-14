drop database if exists seafood;
create database seafood;
use seafood;

-- Table Category
CREATE TABLE category (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

-- Table Product
CREATE TABLE product (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  description TEXT,
  image_url VARCHAR(255),
  price FLOAT NOT NULL,
  quantity INT NOT NULL,
  category_id INT,
  FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE `account` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  name VARCHAR(45) ,
  phone VARCHAR(45) NOT NULL,
  usename VARCHAR(45) NOT NULL,
  flag_delete BIT ,
  avatar VARCHAR(255)
  );
  
  CREATE TABLE `role` (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL
  );
  
CREATE TABLE  account_role (
  id_role INT,
  id_account INT,
  FOREIGN KEY(id_role) REFERENCES `role`(id),
  FOREIGN KEY(id_account) REFERENCES `account`(id),
  PRIMARY KEY (id_role, id_account)
  );

-- Table Order
CREATE TABLE orders (
  id INT PRIMARY KEY AUTO_INCREMENT,
  id_account INT,
  order_date DATE NOT NULL,
  shipping_address VARCHAR(255) NOT NULL,
  total_price FLOAT NOT NULL,
  FOREIGN KEY (id_account) REFERENCES `account`(id)
);

-- Table Order detail
CREATE TABLE order_detail (
  id INT PRIMARY KEY AUTO_INCREMENT,
  order_id INT,
  product_id INT,
  quantity INT NOT NULL,
  price FLOAT NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);


-- Table Cart
CREATE TABLE cart (
  id INT PRIMARY KEY AUTO_INCREMENT,
  id_account INT,
  product_id INT,
  quantity INT NOT NULL,
  total_price FLOAT NOT NULL,
  FOREIGN KEY (id_account) REFERENCES `account`(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);