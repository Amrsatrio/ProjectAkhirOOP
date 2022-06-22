-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 22, 2022 at 03:55 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `classId` char(5) NOT NULL,
  `year` int(11) NOT NULL,
  `name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`classId`, `year`, `name`) VALUES
('CL001', 2022, '9A'),
('CL002', 2022, '9B'),
('CL003', 2022, '9C');

-- --------------------------------------------------------

--
-- Table structure for table `excul`
--

CREATE TABLE `excul` (
  `exculId` char(3) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `excul`
--

INSERT INTO `excul` (`exculId`, `name`) VALUES
('E01', 'Futsal'),
('E02', 'Musik'),
('E03', 'Pramuka'),
('E04', 'Jurnalistik'),
('E05', 'Desain Grafis'),
('E06', 'Fotografi'),
('E07', 'Memasak');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `classId` char(5) NOT NULL,
  `studentId` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`classId`, `studentId`) VALUES
('CL001', 'ST001'),
('CL001', 'ST002'),
('CL001', 'ST003'),
('CL001', 'ST004'),
('CL001', 'ST005'),
('CL001', 'ST006'),
('CL001', 'ST007'),
('CL002', 'ST008'),
('CL002', 'ST009'),
('CL002', 'ST010'),
('CL002', 'ST011'),
('CL002', 'ST012'),
('CL002', 'ST013'),
('CL003', 'ST014'),
('CL003', 'ST015'),
('CL003', 'ST016'),
('CL003', 'ST017'),
('CL003', 'ST018'),
('CL003', 'ST019'),
('CL003', 'ST020');

-- --------------------------------------------------------

--
-- Table structure for table `student_detail`
--

CREATE TABLE `student_detail` (
  `studentId` char(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `exculId` char(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student_detail`
--

INSERT INTO `student_detail` (`studentId`, `name`, `gender`, `dateOfBirth`, `email`, `phone`, `address`, `exculId`) VALUES
('ST001', 'Yuga', 'L', '2009-05-07', 'yuga@gmail.com', '081423658974', 'Jelambar', 'E01'),
('ST002', 'Teru', 'P', '2004-06-08', 'teru@gmail.com', '083621459736', 'Grogol', 'E01'),
('ST003', 'Steve', 'L', '2002-06-18', 'ste@gmail.com', '089425637815', 'Tanjung Duren', 'E02'),
('ST004', 'Terisya', 'P', '2006-06-06', 'ters@gmail.com', '089645137842', 'Tanjung Duren', 'E02'),
('ST005', 'Neli', 'P', '2004-06-14', 'nel@gmail.com', '085423697841', 'Grogol Selatan', 'E04'),
('ST006', 'Merona', 'P', '2004-06-08', 'merona@gmail.com', '085157493645', 'Jelambar', 'E06'),
('ST007', 'Marion Setiawan', 'P', '2002-06-18', 'marion@gmail.com', '085923654718', 'Kebon Jeruk Selatan', 'E03'),
('ST008', 'Mario Budiarto', 'L', '2003-03-11', 'mario@gmail.com', '087412659874', 'Tanjung mangga utara', 'E07'),
('ST009', 'Kevin Anggara', 'L', '2002-02-10', 'kevin@gmail.com', '081284695237', 'latumeten 5 gang 4', 'E07'),
('ST010', 'Ratu Ayu', 'P', '2003-09-17', 'ratu.ayu@gmail.com', '085415236974', 'Grogol Petambuaran', 'E07'),
('ST011', 'Resida', 'P', '2004-02-22', 'res@gmail.com', '081254795623', 'Pluit raya selatan', 'E05'),
('ST012', 'Louis Haruto', 'L', '2003-06-04', 'lou@gmail.com', '085157492357', 'Pantai Indah Kapuk 2', 'E04'),
('ST013', 'Lisa Manika', 'P', '2002-08-03', 'lisa@gmail.com', '085256471825', 'Pluit utara raya', 'E04'),
('ST014', 'Felicia', 'P', '2003-08-07', 'fel@gmail.com', '081452647814', 'empang bahagia raya', 'E02'),
('ST015', 'Laurensia', 'P', '2002-11-07', 'lau@gmail.com', '081245796521', 'Kavling Polri Utara', 'E03'),
('ST016', 'Sheren Laura', 'P', '2003-01-13', 'laura@gmail.com', '081245321414', 'Empang bahagia selatan ', 'E05'),
('ST017', 'Miku', 'P', '2003-12-18', 'mikuku@gmail.com', '081225254796', 'Latumeten 4 Utara', 'E07'),
('ST018', 'Romario ', 'L', '2002-03-04', 'roma@gmail.com', '083965214512', 'Jelambar utama', 'E04'),
('ST019', 'Aldi Taher', 'L', '2002-07-23', 'ald@gmail.com', '085154712415', 'Tanjung Duren Raya', 'E03'),
('ST020', 'Lauren', 'P', '2002-08-28', 'lauren@gmail.com', '081284563214', 'Empang Bahagia Raya', 'E06');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `subjectId` char(5) NOT NULL,
  `classId` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subjectId`, `classId`) VALUES
('SD001', 'CL001'),
('SD002', 'CL001'),
('SD003', 'CL001'),
('SD004', 'CL002'),
('SD005', 'CL002'),
('SD007', 'CL002'),
('SD008', 'CL003'),
('SD009', 'CL003'),
('SD010', 'CL013');

-- --------------------------------------------------------

--
-- Table structure for table `subject_detail`
--

CREATE TABLE `subject_detail` (
  `subjectId` char(5) NOT NULL,
  `name` varchar(15) NOT NULL,
  `teacherId` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subject_detail`
--

INSERT INTO `subject_detail` (`subjectId`, `name`, `teacherId`) VALUES
('SD001', 'Matematika', 'TE001'),
('SD002', 'Fisika', 'TE002'),
('SD003', 'Kimia', 'TE003'),
('SD004', 'Biologi', 'TE004'),
('SD005', 'Sejarah', 'TE005'),
('SD006', 'Biologi', 'TE006'),
('SD007', 'Akuntansi', 'TE007'),
('SD008', 'Bahasa Indonesi', 'TE008'),
('SD009', 'Bahasa Inggris', 'TE009'),
('SD010', 'Komputer', 'TE010');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `teacherId` char(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`teacherId`, `name`, `gender`, `dateOfBirth`, `email`, `phone`, `address`) VALUES
('TE001', 'Gustav Edwan', 'L', '1981-02-14', 'Gustavo.Ganteng@gmail.com', '085757305624', 'Harco Mangga Dua Bl B-1/19, Dki Jakarta'),
('TE002', 'Titin Hariyah', 'P', '1990-06-02', 'bala37@gmail.com', '062466114918', 'Jl Brigjen Katamso 55, Jawa Tengah'),
('TE003', 'Hilda Hariyah', 'P', '1984-09-25', 'bancar.sihotang@gmail.com', '09591753291', 'Jl KH Wahid Hasyim 18, Dki Jakarta'),
('TE004', 'Hasta Pradipta', 'L', '1977-05-13', 'cmegantara@gmail.com', '042345564799', 'Jl MH Thamrin 9, JakartaJl MH Thamrin 9, Jakarta'),
('TE005', 'Chelsea Puspita', 'P', '1990-12-17', 'kurniawan.hendri@gmail.com', '024396055556', 'Jl Tabah Raya, Dki Jakarta'),
('TE006', 'Kasusra Saefullah', 'L', '1976-01-01', 'aryani.janet@gmail.com', '043356343150', 'Jl Pemuda 3, Dki Jakarta'),
('TE007', 'Timbul Mandala', 'L', '1993-11-18', 'rahayu.anastasia@gmail.com', '038669745855', 'Jl Hang Lekiu III 17 Ged Triguna, Dki Jakarta'),
('TE008', 'Ani Wahyuni', 'P', '1989-08-13', 'nadine91@gmail.com', '084590416613', 'Kpg. Bambon No. 984, Administrasi Jakarta Selatan 50804, BaBel'),
('TE009', 'Muhammad Prasetyo', 'L', '1978-03-02', 'mumpuni.rahimah@gmail.com', '036364193179', 'Gg. Jamika No. 315, Pekanbaru 40867, SulBar'),
('TE010', 'Jatmiko Sitorus', 'L', '2000-10-25', 'dabukke.hafshah@gmail.com', '084274481207', 'Dk. Peta No. 74, Bandar Lampung 36192, DIY ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`classId`);

--
-- Indexes for table `excul`
--
ALTER TABLE `excul`
  ADD PRIMARY KEY (`exculId`);

--
-- Indexes for table `student_detail`
--
ALTER TABLE `student_detail`
  ADD PRIMARY KEY (`studentId`);

--
-- Indexes for table `subject_detail`
--
ALTER TABLE `subject_detail`
  ADD PRIMARY KEY (`subjectId`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacherId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
