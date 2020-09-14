DROP DATABASE IF EXISTS blogs_project;
CREATE DATABASE IF NOT EXISTS blogs_project;
USE blogs_project;

DROP TABLE  IF EXISTS `users`;


CREATE TABLE IF NOT EXISTS `users` (
	`id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL,
    `password` varchar(60) NOT NULL,
    `email` varchar(40) NOT NULL,
    `active` smallint NOT NULL,
    `join_date` datetime DEFAULT CURRENT_TIMESTAMP,
    `roles` varchar(60) NOT NULL,
    
    PRIMARY KEY (`id`),
    UNIQUE (`username`)
);

INSERT INTO `users` (`username`, `password`, `email`, `active`, `roles`)
VALUES 
	('admin', '$2a$10$5GkTMzT6AFbjpXXf.oeGlu5dn63ZXNzuh7wH0MKKq9fIFoP2pJkoS', 'temporaryaion1@hotmail.com', 1, 'ROLE_ADMIN,ROLE_MANAGER,ROLE_USER');
-- admin / admin.1

INSERT `users` (`username`, `password`, `email`, `active`, `roles`)
VALUES
	('testuser1', '$2a$10$g.5azVLP19/uFb8svPHgmOOzPnY7TLW80YicwwQ0J4hjUYCJVJN22', 'testemail@gmail.com', 1, 'ROLE_USER');
-- testUser1 / test123

INSERT `users` (`username`, `password`, `email`, `active`, `roles`)
VALUES
	('testuser2', '$2a$10$g.5azVLP19/uFb8svPHgmOOzPnY7TLW80YicwwQ0J4hjUYCJVJN22', 'test222email@gmail.com', 1, 'ROLE_USER');
-- testUser2 / test123



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

INSERT INTO `blogs` (`title`, `author_id`, `content`, `likes`)
VALUES
	('How to not feed', 2, 'Dont die and blame your team', 0);

INSERT INTO `blogs` (`title`, `author_id`, `content`, `likes`)
VALUES
	('How to win games', 2, 'Destroy nexus and say you carried', 0);

INSERT INTO `comments` (`comment`, `commenter_id`, `blog_id`)
VALUES
	('That is a nice way to play the game.', 3, 2);

INSERT INTO `comments` (`comment`, `commenter_id`, `blog_id`)
VALUES
	('Another comment!', 4, 2);
