DROP DATABASE IF EXISTS blogs_project;
CREATE DATABASE IF NOT EXISTS blogs_project;
USE blogs_project;

-- ALTER TABLE `blogs` drop foreign key `blogs_ibfk_1`;
-- ALTER TABLE `comments` drop foreign key `comments_ibfk_1`;
-- ALTER TABLE `comments` drop foreign key `comments_ibfk_2`;
--  `blogs_ibfk_1`

DROP TABLE  IF EXISTS `users`;
DROP TABLE  IF EXISTS `roles`;

CREATE TABLE IF NOT EXISTS `roles` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
 	PRIMARY KEY (`id`)
);

INSERT INTO `roles` (`name`)
VALUES 
('ROLE_ADMIN'), ('ROLE_MANAGER'), ('ROLE_USER');


CREATE TABLE IF NOT EXISTS `users` (
	`id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL,
    `password` varchar(60) NOT NULL,
    `email` varchar(40) NOT NULL,
    `active` smallint NOT NULL,
    `join_date` datetime DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (`id`),
    UNIQUE (`username`)
);

INSERT INTO `users` (`username`, `password`, `email`, `active`)
VALUES 
	('admin', '$2a$10$5GkTMzT6AFbjpXXf.oeGlu5dn63ZXNzuh7wH0MKKq9fIFoP2pJkoS', 'temporaryaion1@hotmail.com', 1);
-- admin / admin.1

INSERT `users` (`username`, `password`, `email`, `active`)
VALUES
	('testuser1', '$2a$10$g.5azVLP19/uFb8svPHgmOOzPnY7TLW80YicwwQ0J4hjUYCJVJN22', 'testemail@gmail.com', 1);
-- testUser1 / test123

INSERT `users` (`username`, `password`, `email`, `active`)
VALUES
	('testuser2', '$2a$10$g.5azVLP19/uFb8svPHgmOOzPnY7TLW80YicwwQ0J4hjUYCJVJN22', 'test222email@gmail.com', 1);
-- testUser2 / test123

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
	`user_id` int NOT NULL,
    `role_id` int NOT NULL,
    
    PRIMARY KEY (`user_id`, `role_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
	ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO `users_roles` (`user_id`, `role_id`)
VALUES
	(1, 1), (1, 2), (1, 3),
	(2, 3),
    (3, 3);


DROP TABLE  IF EXISTS `blogs`;

CREATE TABLE IF NOT EXISTS `blogs` (
	`id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(45),
    `author_id` int,
    `content` varchar(3000),
    `likes` int,
    `write_date` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`author_id`) REFERENCES `users`(`id`)
);

INSERT INTO `blogs` (`title`, `author_id`, `content`, `likes`)
VALUES
	('How to play adc', 2, 'CS well, dont die, do lots of dmg. Dont forget to destroy the nexus!', 0);

INSERT INTO `blogs` (`title`, `author_id`, `content`, `likes`)
VALUES
	('How to play jungle', 3, 'Gank well. Help team. Take dragons xd. Dont forget to destroy the nexus!', 0);


DROP TABLE  IF EXISTS `comments`;

CREATE TABLE IF NOT EXISTS `comments` (
	`id` int NOT NULL AUTO_INCREMENT,
    `comment` varchar(300),
    `commenter_id` int,
    `blog_id` int,
    `write_date` datetime DEFAULT CURRENT_TIMESTAMP,
    
    PRIMARY KEY (`id`),
    FOREIGN KEY (`commenter_id`) REFERENCES `users`(`id`),
    FOREIGN KEY (`blog_id`) REFERENCES `blogs`(`id`)
);


