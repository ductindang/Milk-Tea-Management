-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.27-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlytrasua
CREATE DATABASE IF NOT EXISTS `quanlytrasua` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `quanlytrasua`;

-- Dumping structure for table quanlytrasua.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaSP` varchar(10) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `DonGia` float NOT NULL,
  KEY `MaSP` (`MaSP`),
  KEY `MaHD` (`MaHD`),
  CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`) ON UPDATE CASCADE,
  CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.chitiethoadon: ~8 rows (approximately)
INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `SoLuong`, `DonGia`) VALUES
	('HD1', 'SP4', 1, 25000),
	('HD1', 'SP5', 1, 15000),
	('HD2', 'SP7', 1, 20000),
	('HD2', 'SP4', 1, 25000),
	('HD3', 'SP2', 1, 20000),
	('HD3', 'SP6', 1, 20000),
	('HD4', 'SP2', 1, 20000),
	('HD4', 'SP4', 1, 25000),
	('HD5', 'SP5', 1, 15000),
	('HD5', 'SP7', 1, 20000);

-- Dumping structure for table quanlytrasua.chitietphieunhap
CREATE TABLE IF NOT EXISTS `chitietphieunhap` (
  `MaPN` varchar(10) DEFAULT NULL,
  `MaSP` varchar(10) DEFAULT NULL,
  `SoLuong` int(10) unsigned NOT NULL,
  `DonGia` float unsigned NOT NULL,
  KEY `MaSP` (`MaSP`),
  KEY `MaPN` (`MaPN`),
  CONSTRAINT `chitietphieunhap_ibfk_2` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`) ON UPDATE CASCADE,
  CONSTRAINT `chitietphieunhap_ibfk_3` FOREIGN KEY (`MaPN`) REFERENCES `phieunhap` (`MaPN`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.chitietphieunhap: ~8 rows (approximately)
INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`) VALUES
	('PN8', 'SP1', 20, 20000),
	('PN8', 'SP2', 20, 20000),
	('PN9', 'SP3', 20, 25000),
	('PN10', 'SP4', 20, 25000),
	('PN10', 'SP5', 20, 15000),
	('PN10', 'SP6', 20, 20000),
	('PN11', 'SP7', 20, 20000),
	('PN11', 'SP8', 20, 20000),
	('PN5', 'SP2', 1, 20000),
	('PN7', 'SP2', 1, 20000),
	('PN8', 'SP8', 1, 20000);

-- Dumping structure for table quanlytrasua.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `MaHD` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaKH` varchar(10) NOT NULL,
  `MaKM` varchar(10) NOT NULL,
  `NgayLap` date NOT NULL,
  `GioLap` time NOT NULL,
  `TongTien` float NOT NULL,
  PRIMARY KEY (`MaHD`),
  KEY `MaNV` (`MaNV`),
  KEY `MaKH` (`MaKH`),
  KEY `MaKM` (`MaKM`),
  CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`) ON UPDATE CASCADE,
  CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  CONSTRAINT `khuyenmai_ibfk_3` FOREIGN KEY (`MaKM`) REFERENCES `khuyenmai` (`MaKM`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.hoadon: ~5 rows (approximately)
INSERT INTO `hoadon` (`MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `GioLap`, `TongTien`) VALUES
	('HD1', 'NV1', '001', 'KM1', '2023-09-13', '09:07:01', 40000),
	('HD2', 'NV1', '003', 'KM1', '2023-09-13', '09:07:48', 45000),
	('HD3', 'NV1', '003', 'KM1', '2023-09-13', '09:52:55', 40000),
	('HD4', 'NV1', '004', 'KM1', '2023-09-13', '09:59:55', 45000),
	('HD5', 'NV1', '003', 'KM1', '2023-09-13', '10:09:05', 35000);

-- Dumping structure for table quanlytrasua.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `MaKH` varchar(10) NOT NULL,
  `TenKH` varchar(50) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrangThai` int(11) NOT NULL,
  PRIMARY KEY (`MaKH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.khachhang: ~8 rows (approximately)
INSERT INTO `khachhang` (`MaKH`, `TenKH`, `DiaChi`, `SDT`, `TrangThai`) VALUES
	('001', 'Trần Văn Anh', 'Quận 3, TPHCM', '0128459675', 0),
	('002', 'Nguyễn Phương Trang', 'Quận 5, TPHCM', '0944585959', 0),
	('003', 'Nguyễn Thị Nhã', 'Quận 4, TPHCM', '0932414234', 0),
	('004', 'Trần Thị Phương', 'Quận 8,TPHCM', '0912467534', 0),
	('005', 'Lê Thanh Ngân', 'Quận 6, TPHCM', '0941235433', 0),
	('006', 'Nguyễn Phương An', 'Quận 4, TPHCM', '0963242332', 0),
	('007', 'Trần Đức Ba', 'Quận Bình Thạnh, TPHCM', '0987634574', 0),
	('008', 'Trần Hải Dương', 'Quận Bình Tân, TPHCM', '0931575938', 0);

-- Dumping structure for table quanlytrasua.khuyenmai
CREATE TABLE IF NOT EXISTS `khuyenmai` (
  `MaKM` varchar(10) NOT NULL,
  `TenKM` varchar(100) NOT NULL,
  `DieuKienKM` float NOT NULL,
  `PhanTramKM` float NOT NULL,
  `NgayBD` date DEFAULT NULL,
  `NgayKT` date DEFAULT NULL,
  PRIMARY KEY (`MaKM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.khuyenmai: ~5 rows (approximately)
INSERT INTO `khuyenmai` (`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES
	('KM1', 'Không khuyến mãi', 0, 0, '2020-01-01', '2024-01-01'),
	('KM2', 'Giảm giá nhân ngày 30/4', 5, 5, '2023-04-30', '2023-05-01'),
	('KM3', 'Giảm giá 1/5', 20, 7.5, '2023-05-01', '2023-05-02'),
	('KM4', 'Giảm giá tết', 15, 5, '2023-12-28', '2023-01-05'),
	('KM5', 'Khuyến mãi xả hàng', 100, 96.69, '2023-05-05', '2023-05-15');

-- Dumping structure for table quanlytrasua.loaisanpham
CREATE TABLE IF NOT EXISTS `loaisanpham` (
  `MaLSP` varchar(10) NOT NULL,
  `TenLSP` varchar(70) NOT NULL,
  `Mota` varchar(200) NOT NULL,
  PRIMARY KEY (`MaLSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.loaisanpham: ~3 rows (approximately)
INSERT INTO `loaisanpham` (`MaLSP`, `TenLSP`, `Mota`) VALUES
	('LSP1', 'TRÀ SỮA', ''),
	('LSP2', 'CÀ PHÊ', ''),
	('LSP3', 'HỒNG TRÀ', '');

-- Dumping structure for table quanlytrasua.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `MaNCC` varchar(10) NOT NULL,
  `TenNCC` varchar(70) NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `Fax` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`MaNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.nhacungcap: ~4 rows (approximately)
INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `DiaChi`, `SDT`, `Fax`) VALUES
	('NCC1', 'Công Ty TNHH Thực Phẩm Hoàng Gia', '52B Nguyễn Thị Nhỏ, P.9, Quận Tân Bình, TP. HCM', '0923576984', NULL),
	('NCC2', 'Công Ty TNHH Quang Minh', '43/6C Nguyễn Văn Dậu, P.6, Quận Bình Thạnh, TP. HCM', '0125858558', NULL),
	('NCC3', 'Công Ty TNHH Hóa Chất Minh Anh', '55A Huỳnh Văn Bánh, P.15, Quận Phú Nhuận, TP. HCM', '0958585823', NULL),
	('NCC4', 'Thương Mại Đức Trung', '232A Lý Thường Kiệt, P.14, Quận 10, TP. HCM', '0985211385', NULL);

-- Dumping structure for table quanlytrasua.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `MaNV` varchar(10) NOT NULL,
  `TenNV` text NOT NULL,
  `NgaySinh` date NOT NULL,
  `DiaChi` varchar(100) NOT NULL,
  `SDT` varchar(15) NOT NULL,
  `TrangThai` int(11) NOT NULL,
  PRIMARY KEY (`MaNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.nhanvien: ~7 rows (approximately)
INSERT INTO `nhanvien` (`MaNV`, `TenNV`, `NgaySinh`, `DiaChi`, `SDT`, `TrangThai`) VALUES
	('NV1', 'Đặng Đức Tin', '2000-05-05', 'Quận Bình Chánh, TPHCM', '0157959595', 0),
	('NV2', 'Nguyễn Trần Mỹ Hiền', '2000-03-13', 'Quận 5, TPHCM', '0125898555', 0),
	('NV3', 'Lý Thiên Phúc', '1999-05-29', 'Quận 8, TPHCM', '0125848448', 0),
	('NV4', 'Hoàng Sỹ Khiêm', '2000-02-20', 'Quận Bình Thạnh, TPHCM', '0125995994', 0),
	('NV5', 'Ngô Thanh Tân', '1998-06-26', 'Quận 10, TPHCM', '0125584542', 0),
	('NV6', 'Nguyễn Thị Lan', '2000-05-05', 'Quận 10, TPHCM', '0749878631', 0),
	('NV7', 'Trần Thị Nhạ', '2023-05-07', 'Quận Bình Chánh, TPHCM', '0964648132', 0);

-- Dumping structure for table quanlytrasua.phanquyen
CREATE TABLE IF NOT EXISTS `phanquyen` (
  `MaQuyen` varchar(10) NOT NULL,
  `TenQuyen` varchar(20) NOT NULL,
  `ChiTietQuyen` varchar(255) NOT NULL,
  PRIMARY KEY (`MaQuyen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.phanquyen: ~5 rows (approximately)
INSERT INTO `phanquyen` (`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES
	('Q1', 'Quản lý', 'xemSanPham xemLoaiSanPham xemHoaDon qlNhanVien qlKhachHang xemPhieuNhap xemNCC qlTaiKhoan qlQuyen'),
	('Q2', 'Nhân viên Bán hàng', 'qlBanHang  xemHoaDon  xemKhachHang'),
	('Q3', 'Phụ Bán Hàng', 'qlBanHang xemSanPham xemKhuyenMai xemKhachHang'),
	('Q4', 'Admin', 'qlBanHang qlNhapHang qlSanPham qlLoaiSanPham qlHoaDon qlKhuyenMai qlNhanVien qlKhachHang qlPhieuNhap qlNCC qlTaiKhoan qlQuyen'),
	('Q5', 'Nhân viên Nhập hàng', 'qlNhapHang xemSanPham xemLoaiSanPham xemNhanVien qlPhieuNhap qlNCC');

-- Dumping structure for table quanlytrasua.phieunhap
CREATE TABLE IF NOT EXISTS `phieunhap` (
  `MaPN` varchar(10) NOT NULL,
  `MaNCC` varchar(10) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `NgayNhap` date NOT NULL,
  `GioNhap` time NOT NULL,
  `TongTien` float NOT NULL,
  PRIMARY KEY (`MaPN`),
  KEY `MaNCC` (`MaNCC`),
  KEY `MaNV` (`MaNV`),
  CONSTRAINT `phieunhap_ibfk_1` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`) ON UPDATE CASCADE,
  CONSTRAINT `phieunhap_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.phieunhap: ~7 rows (approximately)
INSERT INTO `phieunhap` (`MaPN`, `MaNCC`, `MaNV`, `NgayNhap`, `GioNhap`, `TongTien`) VALUES
	('PN10', 'NCC3', 'NV4', '2023-05-10', '20:43:26', 1200000),
	('PN11', 'NCC4', 'NV4', '2023-05-10', '20:43:40', 800000),
	('PN5', 'NCC2', 'NV4', '2023-05-11', '11:22:30', 20000),
	('PN6', 'NCC2', 'NV4', '2023-05-11', '11:32:27', 0),
	('PN7', 'NCC1', 'NV4', '2023-05-11', '11:33:23', 20000),
	('PN8', 'NCC1', 'NV4', '2023-05-10', '20:42:42', 820000),
	('PN9', 'NCC2', 'NV4', '2023-05-10', '20:42:59', 500000);

-- Dumping structure for table quanlytrasua.sanpham
CREATE TABLE IF NOT EXISTS `sanpham` (
  `MaSP` varchar(10) NOT NULL,
  `MaLSP` varchar(30) NOT NULL,
  `TenSP` varchar(70) NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(10) unsigned NOT NULL DEFAULT 1,
  `HinhAnh` varchar(200) NOT NULL,
  `TrangThai` int(11) NOT NULL,
  PRIMARY KEY (`MaSP`),
  KEY `LoaiSP` (`MaLSP`),
  CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`MaLSP`) REFERENCES `loaisanpham` (`MaLSP`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.sanpham: ~8 rows (approximately)
INSERT INTO `sanpham` (`MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES
	('SP1', 'LSP1', 'TRÀ SỮA ÔLONG', 20000, 26, 'ts_olong.jpeg', 0),
	('SP2', 'LSP1', 'TRÀ SỮA TRÂN CHÂU', 20000, 21, 'ts_tranchau.jpeg', 0),
	('SP3', 'LSP1', 'TRÀ SỮA DÂU TÂY', 25000, 21, 'ts_dautay.jpeg', 0),
	('SP4', 'LSP2', 'BẠC XỈU', 25000, 18, 'cf_bacxiu.jpeg', 0),
	('SP5', 'LSP2', 'CÀ PHÊ ĐEN ', 15000, 20, 'cf_den.jpeg', 0),
	('SP6', 'LSP2', 'CÀ PHÊ SỮA', 20000, 20, 'cf_sua.jpeg', 0),
	('SP7', 'LSP3', 'HỒNG TRÀ BƯỞI MẬT ONG', 20000, 19, 'ht_buoimatong.jpeg', 0),
	('SP8', 'LSP3', 'HỒNG TRÀ VIỆT QUẤT', 20000, 26, 'ht_vietquat.jpeg', 0),
	('SP9', 'LSP3', 'TRA SUA THACH', 30000, 12, 'ht_vietquat.jpeg', 0);

-- Dumping structure for table quanlytrasua.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `TenTaiKhoan` varchar(50) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `MaNV` varchar(10) NOT NULL,
  `MaQuyen` varchar(10) NOT NULL,
  PRIMARY KEY (`TenTaiKhoan`),
  KEY `MaQuyen` (`MaQuyen`),
  KEY `MaNV` (`MaNV`),
  CONSTRAINT `taikhoan_ibfk_2` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON UPDATE CASCADE,
  CONSTRAINT `taikhoan_ibfk_3` FOREIGN KEY (`MaQuyen`) REFERENCES `phanquyen` (`MaQuyen`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table quanlytrasua.taikhoan: ~7 rows (approximately)
INSERT INTO `taikhoan` (`TenTaiKhoan`, `MatKhau`, `MaNV`, `MaQuyen`) VALUES
	('admin', '123456', 'NV1', 'Q4'),
	('nhanvien1', '123456', 'NV2', 'Q2'),
	('nhanvien2', '123456', 'NV3', 'Q5'),
	('nhanvien3', '123456', 'NV4', 'Q3'),
	('nhanvien4', '123456', 'NV6', 'Q3'),
	('nhanvien5', '123456', 'NV7', 'Q2'),
	('quanli', '123456', 'NV5', 'Q1');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
