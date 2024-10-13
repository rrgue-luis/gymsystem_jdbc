-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2024 at 02:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gymsystem`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_qty_valid_pay` ()   BEGIN
SELECT m.name,
COUNT(payment_is_valid)AS cant_pagos
FROM member m
INNER JOIN payment p ON m.id = p.member_id
WHERE payment_is_valid = 1
GROUP BY name;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `alumno`
--

CREATE TABLE `alumno` (
  `id` int(11) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `hiring_date` date DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `role` enum('BOSS','MANAGER','TRAINER','EMPLOYEE') DEFAULT NULL,
  `status` enum('ACTIVE','INACTIVE','VACATIONS') DEFAULT NULL,
  `shift` enum('MORNING','AFTERNOON','NIGHT') DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `name`, `surname`, `hiring_date`, `salary`, `role`, `status`, `shift`, `phone`, `address`) VALUES
(8, 'luigi', 'fitness', '2024-09-09', 0, 'TRAINER', 'ACTIVE', 'MORNING', '123', 'address'),
(10, 'a', 'a', '2024-09-18', 2300.9, 'TRAINER', 'ACTIVE', 'NIGHT', '1', 'a'),
(11, 'a', 'a', '2024-09-18', 900.32, 'TRAINER', 'ACTIVE', 'AFTERNOON', 'a', 'a'),
(12, 'a', 'a', '2024-09-18', 1200.33, 'TRAINER', 'ACTIVE', 'NIGHT', 'a', 'a'),
(13, 'a', 'a', '2024-09-18', 2.3, 'TRAINER', 'ACTIVE', 'NIGHT', 'a', 'a'),
(14, 'pepe', 'gonzalez', '2024-09-20', 1200.33, 'MANAGER', 'ACTIVE', 'AFTERNOON', '123', 'Address'),
(15, 'Gonzalo', 'Rojas', '2024-09-20', 5300.93, 'MANAGER', 'ACTIVE', 'NIGHT', '123', 'Address'),
(16, 'test', 'test', '2024-09-20', 1200.33, 'EMPLOYEE', 'ACTIVE', 'NIGHT', 'test', 'test'),
(17, 'test', 'test', '2024-09-20', 9000.32, 'BOSS', 'ACTIVE', 'NIGHT', 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `gym`
--

CREATE TABLE `gym` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `schedule` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `status` enum('OPEN','CLOSED','MAINTENANCE') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gym`
--

INSERT INTO `gym` (`id`, `name`, `address`, `schedule`, `phone`, `email`, `capacity`, `status`) VALUES
(1, 'GymDevoto', 'devoto123', 'L a S 24hs', '1123232323', 'gymdevoto@gym', 300, ''),
(2, 'GymCatan', 'Sperez123', 'L a S 12hs', '1122222222', 'gymcatan@gym', 200, ''),
(3, 'GymLafe', 'Isabel444', 'L a D 14hs', '114444444', 'gymlafe@gym', 400, '');

-- --------------------------------------------------------

--
-- Table structure for table `gym_employees`
--

CREATE TABLE `gym_employees` (
  `gym_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `gym_employees`
--

INSERT INTO `gym_employees` (`gym_id`, `employee_id`) VALUES
(1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `gym_members`
--

CREATE TABLE `gym_members` (
  `gym_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `registration_date` date DEFAULT NULL,
  `membership_end_date` date DEFAULT NULL,
  `membership_type` enum('DAILY','WEEKLY','MONTHLY') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `name`, `surname`, `gender`, `phone`, `address`, `birth_date`, `registration_date`, `membership_end_date`, `membership_type`) VALUES
(1, 'Dorian', 'Yates', 'Male', '123851', 'Address', '1975-03-12', '2024-09-13', '2024-09-13', 'MONTHLY'),
(2, 'ezequiel', 'pereira', 'male', '1100993322', 'sperez', '2004-05-17', '2022-02-14', '2024-08-26', 'WEEKLY'),
(3, 'Chris', 'Bumstead', 'male', '11343953312', 'california', '1997-03-23', '2017-05-09', '2024-09-26', 'MONTHLY'),
(4, 'Juan', 'SECrara', 'male', '11009988', 'catan', '2003-03-30', '2017-05-09', '2024-09-26', 'MONTHLY'),
(5, 'Luis', 'Carrizo', 'male', '1165250302', 'machain', '2003-05-17', '2023-05-15', '2024-09-24', 'MONTHLY'),
(35, 'a', 'a', 'a', 'a', 'a', '1999-01-01', '2024-09-16', '2024-09-17', 'DAILY'),
(36, 'a', 'a', 'a', 'a', 'a', '1999-09-16', '2024-09-16', '2024-09-17', 'DAILY'),
(37, 'a', 'a', 'a', 'a', 'a', '1999-01-01', '1999-01-01', '1999-01-02', 'DAILY'),
(38, 'a', 'a', 'a', 'a', 'a', '1999-01-01', '1999-01-01', '1999-01-08', 'WEEKLY');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `amount` float(10,2) DEFAULT NULL,
  `payment_date` date DEFAULT NULL,
  `payment_method` enum('CASH','DEBIT','CREDIT','TRANSFER') DEFAULT NULL,
  `payment_is_valid` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `member_id`, `amount`, `payment_date`, `payment_method`, `payment_is_valid`) VALUES
(1, 1, 2300.45, '2024-08-10', 'CASH', 1),
(2, 2, 1200.47, '2024-08-19', 'CREDIT', 1),
(3, 3, 2300.45, '2024-08-26', 'DEBIT', 1),
(4, 1, 1200.26, '2024-07-26', 'CASH', 1),
(5, 1, 1250.26, '2024-07-26', 'CASH', 0),
(6, 1, 1250.26, '2024-06-26', 'CASH', 0),
(7, 1, 1250.26, '2024-05-26', 'CASH', 0),
(8, 1, 2300.45, '2024-04-26', 'DEBIT', 1),
(9, 1, 2300.45, '2024-03-26', 'DEBIT', 1),
(10, 1, 2300.45, '2024-02-26', 'DEBIT', 1),
(11, 1, 2300.45, '2024-01-26', 'DEBIT', 1),
(12, 2, 2300.45, '2024-07-26', 'DEBIT', 1),
(13, 2, 2300.45, '2024-06-26', 'DEBIT', 1),
(14, 2, 2300.45, '2024-05-26', 'DEBIT', 1),
(15, 2, 2300.45, '2024-04-26', 'DEBIT', 1),
(16, 3, 2300.45, '2024-07-26', 'DEBIT', 1),
(17, 3, 2300.45, '2024-06-26', 'DEBIT', 1),
(18, 3, 2300.45, '2024-05-26', 'DEBIT', 1),
(19, 3, 2300.45, '2024-04-26', 'DEBIT', 1),
(20, 3, 2300.45, '2024-03-26', 'DEBIT', 1),
(21, 4, 2300.45, '2024-08-26', 'DEBIT', 0),
(22, 4, 2300.45, '2024-07-26', 'DEBIT', 0),
(23, 4, 2300.45, '2024-06-26', 'DEBIT', 0),
(24, 4, 2300.45, '2024-05-26', 'DEBIT', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gym`
--
ALTER TABLE `gym`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `gym_employees`
--
ALTER TABLE `gym_employees`
  ADD PRIMARY KEY (`gym_id`,`employee_id`),
  ADD KEY `gym_employees_ibfk_2` (`employee_id`);

--
-- Indexes for table `gym_members`
--
ALTER TABLE `gym_members`
  ADD PRIMARY KEY (`gym_id`,`member_id`),
  ADD KEY `member_id` (`member_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `memberId` (`member_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alumno`
--
ALTER TABLE `alumno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `gym`
--
ALTER TABLE `gym`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `gym_employees`
--
ALTER TABLE `gym_employees`
  ADD CONSTRAINT `gym_employees_ibfk_1` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`),
  ADD CONSTRAINT `gym_employees_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `gym_members`
--
ALTER TABLE `gym_members`
  ADD CONSTRAINT `gym_members_ibfk_1` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`),
  ADD CONSTRAINT `gym_members_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
