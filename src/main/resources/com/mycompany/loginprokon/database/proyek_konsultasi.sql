-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 07, 2024 at 07:58 AM
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
-- Database: `proyek_konsultasi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(100) NOT NULL,
  `NIP` int(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `NIP`, `username`, `password`) VALUES
(1, 111111, 'far', 'far');

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `NIP` int(50) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`NIP`, `Nama`, `Status`) VALUES
(111111, 'FARHAN RAMADHAN KHOIR', 'Aktif'),
(111112, 'RIDHWAN NADIF KURNIA', 'Aktif'),
(111113, 'AGISTIA RONNA ANIQA', 'Aktif'),
(111114, 'FITRI NABILLA RAHMA MULYANA', 'Aktif'),
(111115, 'SATRIATAMA PUTRA', 'Aktif');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_pelajaran`
--

CREATE TABLE `jadwal_pelajaran` (
  `id` int(11) NOT NULL,
  `mapel_jadwal` varchar(255) NOT NULL,
  `pukul_jadwal` varchar(255) NOT NULL,
  `kelas_jadwal` varchar(255) NOT NULL,
  `hari_jadwal` varchar(255) NOT NULL,
  `NIP` int(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jadwal_pelajaran`
--

INSERT INTO `jadwal_pelajaran` (`id`, `mapel_jadwal`, `pukul_jadwal`, `kelas_jadwal`, `hari_jadwal`, `NIP`) VALUES
(1, 'Bahasa Indonesia', '08:00', '3', 'Rabu', 111111),
(7, 'Bahasa Indonesia', '08:00', '3', 'Rabu', 111111),
(14, 'Sejarah Islam', '20:00', '3', 'Kamis', 111111),
(20, 'Bahasa Indonesia', '08:00', '3', 'Rabu', 111111),
(21, 'Sejarah Islam', '10:00', '4', 'Senin', 111111),
(22, 'Sejarah Islam', '10:00', '4', 'Senin', 111111),
(23, 'Matematika', '12:00', '4', 'Jumat', 111111),
(26, 'Bahasa Indonesia', '10:00', '4', 'Kamis', 111111),
(27, 'Matematika', '10:00', '2', 'Selasa', 111111);

-- --------------------------------------------------------

--
-- Table structure for table `kalenderisasi`
--

CREATE TABLE `kalenderisasi` (
  `id_kalender` int(100) NOT NULL,
  `tanggal_acara` varchar(50) NOT NULL,
  `keterangan_acara` varchar(255) NOT NULL,
  `semester` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kalenderisasi`
--

INSERT INTO `kalenderisasi` (`id_kalender`, `tanggal_acara`, `keterangan_acara`, `semester`) VALUES
(3, 'May 8, 2024 to Jun 5, 2024', 'PPDB 2024', 'Semester Ganjil'),
(11, 'May 30, 2024 to May 30, 2024', 'Pembagian Rapot', 'Semester Ganjil');

-- --------------------------------------------------------

--
-- Table structure for table `nilai_hafalan_alquran`
--

CREATE TABLE `nilai_hafalan_alquran` (
  `idnilaihaf` int(100) NOT NULL,
  `Surat` varchar(100) NOT NULL,
  `Ayat` int(255) NOT NULL,
  `NamaSiswa` varchar(255) NOT NULL,
  `tanggalhafalan` date DEFAULT NULL,
  `nis` int(100) NOT NULL,
  `kelas` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nilai_hafalan_alquran`
--

INSERT INTO `nilai_hafalan_alquran` (`idnilaihaf`, `Surat`, `Ayat`, `NamaSiswa`, `tanggalhafalan`, `nis`, `kelas`) VALUES
(8, 'al-fill', 40, 'nama', '2024-06-29', 3, '3'),
(9, 'al-fill', 200, 'ridhwan', '2024-06-08', 200, '3'),
(10, 'al-falaq', 75, 'asdasdasda', '2024-06-28', 3, '4a'),
(11, 'al-fill', 25, 'awqasda', '2024-06-29', 200, '4a');

-- --------------------------------------------------------

--
-- Table structure for table `nilai_pelajaran`
--

CREATE TABLE `nilai_pelajaran` (
  `id_pelajaran` int(100) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nis` int(100) NOT NULL,
  `semester` int(100) NOT NULL,
  `mata_pelajaran` varchar(255) NOT NULL,
  `nilai` int(100) NOT NULL,
  `kkm` int(100) NOT NULL,
  `ket` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nilai_pelajaran`
--

INSERT INTO `nilai_pelajaran` (`id_pelajaran`, `nama`, `nis`, `semester`, `mata_pelajaran`, `nilai`, `kkm`, `ket`) VALUES
(83, 'Agis', 1, 5, 'Bahasa Indonesia', 100, 75, 'Hebat'),
(84, 'Agis', 1, 5, 'Matematika', 100, 75, 'Hebat'),
(85, 'Agis', 1, 5, 'Sejarah Islam', 100, 75, 'Hebat'),
(86, 'Satria', 2, 5, 'Bahasa Indonesia', 100, 75, 'Hebat'),
(87, 'Satria', 2, 5, 'Matematika', 100, 75, 'Hebat'),
(88, 'Satria', 2, 5, 'Sejarah Islam', 100, 75, 'Hebat'),
(89, 'Farhan', 3, 5, 'Bahasa Indonesia', 100, 75, 'Hebat'),
(90, 'Farhan', 3, 5, 'Matematika', 100, 75, 'Hebat');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `NIS` int(100) NOT NULL,
  `Nama` varchar(255) NOT NULL,
  `Kelas` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  `Semester` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`NIS`, `Nama`, `Kelas`, `Status`, `Semester`) VALUES
(111111, 'FARHAN RAMADHAN KHOIR', '3A', 'Aktif', 1),
(111112, 'RIDHWAN NADIF KURNIA', '3A', 'Aktif', 1),
(111113, 'AGISTIA RONNA ANIQA', '3B', 'Aktif', 1),
(111114, 'FITRI NABILLA RAHMA MULYANA', '3B', 'Aktif', 1),
(111115, 'SATRIATAMA PUTRA', '3A', 'Aktif', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_nip` (`NIP`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`NIP`);

--
-- Indexes for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_nipjadwal` (`NIP`);

--
-- Indexes for table `kalenderisasi`
--
ALTER TABLE `kalenderisasi`
  ADD PRIMARY KEY (`id_kalender`);

--
-- Indexes for table `nilai_hafalan_alquran`
--
ALTER TABLE `nilai_hafalan_alquran`
  ADD PRIMARY KEY (`idnilaihaf`);

--
-- Indexes for table `nilai_pelajaran`
--
ALTER TABLE `nilai_pelajaran`
  ADD PRIMARY KEY (`id_pelajaran`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`NIS`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `kalenderisasi`
--
ALTER TABLE `kalenderisasi`
  MODIFY `id_kalender` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `nilai_hafalan_alquran`
--
ALTER TABLE `nilai_hafalan_alquran`
  MODIFY `idnilaihaf` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `nilai_pelajaran`
--
ALTER TABLE `nilai_pelajaran`
  MODIFY `id_pelajaran` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `fk_nip` FOREIGN KEY (`NIP`) REFERENCES `guru` (`NIP`);

--
-- Constraints for table `jadwal_pelajaran`
--
ALTER TABLE `jadwal_pelajaran`
  ADD CONSTRAINT `fk_nipjadwal` FOREIGN KEY (`NIP`) REFERENCES `guru` (`NIP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
