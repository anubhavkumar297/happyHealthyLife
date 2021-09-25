SHOW DATABASES;

create schema happy_healthy_life;

USE happy_healthy_life;

SHOW TABLES;

CREATE TABLE `contact_info` (
  `id` int NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE phone_numbers(
	`id` int NOT NULL, 
	`type` varchar (15), 
	`phone_number` varchar (15),
	FOREIGN KEY (id) 
	REFERENCES contact_info(id)
	);
