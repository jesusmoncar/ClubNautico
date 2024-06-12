-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-05-2024 a las 13:04:33
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clubnautico`
--
drop database if exists clubnautico;
create database clubnautico;
use clubnautico;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departure`
--

CREATE TABLE `departure` (
  `id_departure` bigint(20) NOT NULL,
  `departure_time` datetime(6) DEFAULT NULL,
  `id_master` bigint(20) NOT NULL,
  `ship_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `master`
--

CREATE TABLE `master` (
  `id_master` bigint(20) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permit_number` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `member`
--

CREATE TABLE `member` (
  `id_member` bigint(20) NOT NULL,
  `dock_number` varchar(255) DEFAULT NULL,
  `fee` double NOT NULL,
  `is_master` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permit_number` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `member`
--
DELIMITER $$
CREATE TRIGGER `member_to_master_trigger` AFTER INSERT ON `member` FOR EACH ROW BEGIN
    IF NEW.is_master = 1 THEN
        -- Inserta o actualiza el registro en la tabla Master
        INSERT INTO Master (name, last_name, permit_number)
        VALUES (NEW.name, NEW.last_name, NEW.permit_number)
        ON DUPLICATE KEY UPDATE
        name = NEW.name,
        last_name = NEW.last_name,
        permit_number = NEW.permit_number;
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `member_trigger` BEFORE INSERT ON `member` FOR EACH ROW BEGIN
    IF NEW.is_master = 1 THEN
        SET NEW.permit_Number = NEW.permit_Number;
    ELSE
        SET NEW.permit_Number = 0;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ship`
--

CREATE TABLE `ship` (
  `id_ship` bigint(20) NOT NULL,
  `registration_tag` varchar(255) DEFAULT NULL,
  `id_member` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `departure`
--
ALTER TABLE `departure`
  ADD PRIMARY KEY (`id_departure`),
  ADD KEY `FK6hqdfp08rdm1f21hsbtx3s9nr` (`id_master`),
  ADD KEY `FKc21n1ts2jeqvfrpaetnr03vem` (`ship_id`);

--
-- Indices de la tabla `master`
--
ALTER TABLE `master`
  ADD PRIMARY KEY (`id_master`);

--
-- Indices de la tabla `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id_member`);

--
-- Indices de la tabla `ship`
--
ALTER TABLE `ship`
  ADD PRIMARY KEY (`id_ship`),
  ADD KEY `FKkme2cf9ntiwbp0w0wxhtj862w` (`id_member`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `departure`
--
ALTER TABLE `departure`
  MODIFY `id_departure` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `master`
--
ALTER TABLE `master`
  MODIFY `id_master` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `member`
--
ALTER TABLE `member`
  MODIFY `id_member` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ship`
--
ALTER TABLE `ship`
  MODIFY `id_ship` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `departure`
--
ALTER TABLE `departure`
  ADD CONSTRAINT `FK6hqdfp08rdm1f21hsbtx3s9nr` FOREIGN KEY (`id_master`) REFERENCES `master` (`id_master`),
  ADD CONSTRAINT `FKc21n1ts2jeqvfrpaetnr03vem` FOREIGN KEY (`ship_id`) REFERENCES `ship` (`id_ship`);

--
-- Filtros para la tabla `ship`
--
ALTER TABLE `ship`
  ADD CONSTRAINT `FKkme2cf9ntiwbp0w0wxhtj862w` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
