-- Adminer 4.8.1 MySQL 8.0.29 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

CREATE DATABASE `flooid` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `flooid`;

DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `auth_group_permissions`;
CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1,	'Can add log entry',	1,	'add_logentry'),
(2,	'Can change log entry',	1,	'change_logentry'),
(3,	'Can delete log entry',	1,	'delete_logentry'),
(4,	'Can view log entry',	1,	'view_logentry'),
(5,	'Can add permission',	2,	'add_permission'),
(6,	'Can change permission',	2,	'change_permission'),
(7,	'Can delete permission',	2,	'delete_permission'),
(8,	'Can view permission',	2,	'view_permission'),
(9,	'Can add group',	3,	'add_group'),
(10,	'Can change group',	3,	'change_group'),
(11,	'Can delete group',	3,	'delete_group'),
(12,	'Can view group',	3,	'view_group'),
(13,	'Can add content type',	4,	'add_contenttype'),
(14,	'Can change content type',	4,	'change_contenttype'),
(15,	'Can delete content type',	4,	'delete_contenttype'),
(16,	'Can view content type',	4,	'view_contenttype'),
(17,	'Can add session',	5,	'add_session'),
(18,	'Can change session',	5,	'change_session'),
(19,	'Can delete session',	5,	'delete_session'),
(20,	'Can view session',	5,	'view_session'),
(21,	'Can add user',	6,	'add_user'),
(22,	'Can change user',	6,	'change_user'),
(23,	'Can delete user',	6,	'delete_user'),
(24,	'Can view user',	6,	'view_user'),
(25,	'Can add label',	7,	'add_label'),
(26,	'Can change label',	7,	'change_label'),
(27,	'Can delete label',	7,	'delete_label'),
(28,	'Can view label',	7,	'view_label'),
(29,	'Can add submitted data',	8,	'add_submitteddata'),
(30,	'Can change submitted data',	8,	'change_submitteddata'),
(31,	'Can delete submitted data',	8,	'delete_submitteddata'),
(32,	'Can view submitted data',	8,	'view_submitteddata'),
(33,	'Can add data relationships',	9,	'add_datarelationships'),
(34,	'Can change data relationships',	9,	'change_datarelationships'),
(35,	'Can delete data relationships',	9,	'delete_datarelationships'),
(36,	'Can view data relationships',	9,	'view_datarelationships'),
(37,	'Can add submitted url',	10,	'add_submittedurl'),
(38,	'Can change submitted url',	10,	'change_submittedurl'),
(39,	'Can delete submitted url',	10,	'delete_submittedurl'),
(40,	'Can view submitted url',	10,	'view_submittedurl');

DROP TABLE IF EXISTS `data_datarelationships`;
CREATE TABLE `data_datarelationships` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `related_data_name` longtext NOT NULL,
  `data_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `data_datarelationships_data_id_5c1e4bff_fk_data_submitteddata_id` (`data_id`),
  CONSTRAINT `data_datarelationships_data_id_5c1e4bff_fk_data_submitteddata_id` FOREIGN KEY (`data_id`) REFERENCES `data_submitteddata` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `data_submitteddata`;
CREATE TABLE `data_submitteddata` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` longtext NOT NULL,
  `approved` tinyint(1) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `data_submitteddata_user_id_ab2e4e3f_fk_user_user_id` (`user_id`),
  CONSTRAINT `data_submitteddata_user_id_ab2e4e3f_fk_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `data_submitteddata` (`id`, `data`, `approved`, `user_id`) VALUES
(1,	'',	0,	1),
(2,	'Jeroen Michael',	0,	1);

DROP TABLE IF EXISTS `django_admin_log`;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_user_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `django_content_type`;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(1,	'admin',	'logentry'),
(3,	'auth',	'group'),
(2,	'auth',	'permission'),
(4,	'contenttypes',	'contenttype'),
(9,	'data',	'datarelationships'),
(8,	'data',	'submitteddata'),
(5,	'sessions',	'session'),
(10,	'url',	'submittedurl'),
(7,	'user',	'label'),
(6,	'user',	'user');

DROP TABLE IF EXISTS `django_migrations`;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1,	'user',	'0001_initial',	'2022-05-26 11:15:13.162767'),
(2,	'contenttypes',	'0001_initial',	'2022-05-26 11:15:13.879478'),
(3,	'admin',	'0001_initial',	'2022-05-26 11:15:16.613768'),
(4,	'admin',	'0002_logentry_remove_auto_add',	'2022-05-26 11:15:16.672441'),
(5,	'admin',	'0003_logentry_add_action_flag_choices',	'2022-05-26 11:15:16.756532'),
(6,	'contenttypes',	'0002_remove_content_type_name',	'2022-05-26 11:15:18.020086'),
(7,	'auth',	'0001_initial',	'2022-05-26 11:15:23.513138'),
(8,	'auth',	'0002_alter_permission_name_max_length',	'2022-05-26 11:15:24.906612'),
(9,	'auth',	'0003_alter_user_email_max_length',	'2022-05-26 11:15:24.958740'),
(10,	'auth',	'0004_alter_user_username_opts',	'2022-05-26 11:15:25.016637'),
(11,	'auth',	'0005_alter_user_last_login_null',	'2022-05-26 11:15:25.096028'),
(12,	'auth',	'0006_require_contenttypes_0002',	'2022-05-26 11:15:25.183354'),
(13,	'auth',	'0007_alter_validators_add_error_messages',	'2022-05-26 11:15:25.317238'),
(14,	'auth',	'0008_alter_user_username_max_length',	'2022-05-26 11:15:25.367820'),
(15,	'auth',	'0009_alter_user_last_name_max_length',	'2022-05-26 11:15:25.475349'),
(16,	'auth',	'0010_alter_group_name_max_length',	'2022-05-26 11:15:25.730121'),
(17,	'auth',	'0011_update_proxy_permissions',	'2022-05-26 11:15:25.820342'),
(18,	'auth',	'0012_alter_user_first_name_max_length',	'2022-05-26 11:15:25.909071'),
(19,	'data',	'0001_initial',	'2022-05-26 11:15:28.596278'),
(20,	'sessions',	'0001_initial',	'2022-05-26 11:15:29.254168'),
(21,	'url',	'0001_initial',	'2022-05-26 11:15:30.629930'),
(22,	'user',	'0002_user_job_title_user_role_user_topology_labels',	'2022-05-26 11:15:32.748190'),
(23,	'user',	'0003_alter_user_topology_labels',	'2022-05-26 11:15:32.833836'),
(24,	'user',	'0004_alter_user_managers_alter_user_email',	'2022-05-26 11:15:33.321835'),
(25,	'user',	'0005_label',	'2022-05-26 11:15:33.854087'),
(26,	'user',	'0006_remove_user_topology_labels_user_topology_labels',	'2022-05-26 11:15:37.480634'),
(27,	'user',	'0007_alter_user_topology_labels',	'2022-05-26 11:15:37.561143');

DROP TABLE IF EXISTS `django_session`;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `url_submittedurl`;
CREATE TABLE `url_submittedurl` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL,
  `approved` tinyint(1) NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `url_submittedurl_user_id_bc681e25_fk_user_user_id` (`user_id`),
  CONSTRAINT `url_submittedurl_user_id_bc681e25_fk_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `url_submittedurl` (`id`, `url`, `approved`, `user_id`) VALUES
(1,	'http://example.com',	0,	1);

DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `job_title` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_user_email_1c6f3d1a_uniq` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `user_user` (`id`, `first_name`, `last_name`, `email`, `password`, `job_title`, `role`) VALUES
(1,	'Admin',	'Admin',	'a@a.com',	'pbkdf2_sha256$320000$ZTogrmUJ4CBiXFw5HmMVI4$d/ESyKq3hs5drnU99rHAZkav2OW8IfpBryuweU8IZkk=',	'',	'User');

DROP TABLE IF EXISTS `user_user_topology_labels`;
CREATE TABLE `user_user_topology_labels` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `label_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_user_topology_labels_user_id_label_id_bf85144f_uniq` (`user_id`,`label_id`),
  KEY `user_user_topology_labels_label_id_9595f173_fk_user_label_id` (`label_id`),
  CONSTRAINT `user_user_topology_labels_label_id_9595f173_fk_user_label_id` FOREIGN KEY (`label_id`) REFERENCES `user_label` (`id`),
  CONSTRAINT `user_user_topology_labels_user_id_99a20d7b_fk_user_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- 2022-06-14 16:27:43