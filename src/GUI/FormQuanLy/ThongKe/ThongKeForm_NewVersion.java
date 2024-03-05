/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.FormQuanLy.ThongKe;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;

import BUS.*;
import DEV.*;
import GUI.FormChon.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

/**
 *
 * @author DELL
 */
public class ThongKeForm_NewVersion extends JPanel {

    QuanLyHoaDonBUS qlhdBUS = new QuanLyHoaDonBUS();
    QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
    QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
    QuanLyKhachHangBUS qlkhBUS = new QuanLyKhachHangBUS();
    QuanLyPhieuNhapBUS qlpnBUS = new QuanLyPhieuNhapBUS();
    QuanLyNhaCungCapBUS qlnccBUS = new QuanLyNhaCungCapBUS();
    QuanLyChiTietHoaDonBUS qlcthdBUS = new QuanLyChiTietHoaDonBUS();
    QuanLyChiTietPhieuNhapBUS qlctpnBUS = new QuanLyChiTietPhieuNhapBUS();
    QuanLyLoaiSanPhamBUS qllspBUS = new QuanLyLoaiSanPhamBUS();

    JTextField txNgay1 = new JTextField(7);
    JTextField txNgay2 = new JTextField(7);
    JTextField txNhanVien = new JTextField(10);
    JTextField txKhachHang = new JTextField(10);
    JTextField txNhaCC = new JTextField(10);
    JTextField txSanPham = new JTextField(10);

    JPanel plSanPham, plNhanVien, plKhachHang, plNhaCC;

    DatePicker dPicker1;
    DatePicker dPicker2;
    MoreButton btnChonNhanVien = new MoreButton();
    MoreButton btnChonKhachHang = new MoreButton();
    MoreButton btnChonNhaCC = new MoreButton();
    MoreButton btnChonSanPham = new MoreButton();
    RefreshButton btnRefresh = new RefreshButton();

    JCheckBox chbNhanVien = new JCheckBox();
    JCheckBox chbKhachHang = new JCheckBox();
    JCheckBox chbNhaCC = new JCheckBox();
    JCheckBox chbSanPham = new JCheckBox();

    JComboBox<String> cbChonLoaiThongKe = new JComboBox<>(new String[] { "Bán ra", "Nhập vào" });

    MyTable tb = new MyTable();
    MyTable tbTong = new MyTable();

    public ThongKeForm_NewVersion() {
        setLayout(new BorderLayout());

        // panel chon ngay
        DatePickerSettings ds = new DatePickerSettings();
        ds.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(ds);
        dPicker2 = new DatePicker(ds.copySettings());
        dPicker1.addDateChangeListener((dce) -> {
            txNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });

        txNgay1.setBorder(BorderFactory.createTitledBorder("Từ"));
        txNgay2.setBorder(BorderFactory.createTitledBorder("Đến"));

        JPanel plChonNgay = new JPanel();
        plChonNgay.setBorder(BorderFactory.createTitledBorder("Khoảng ngày"));

        addDocumentListener(txNgay1);
        addDocumentListener(txNgay2);
        plChonNgay.add(txNgay1);
        plChonNgay.add(dPicker1);
        plChonNgay.add(txNgay2);
        plChonNgay.add(dPicker2);
        // ============== End panel chon ngay ======================
        // tb.setHeaders(new String[]{"Hóa đơn"});

        // event
        btnChonSanPham.addActionListener((ae) -> {
            new ChonSanPhamForm(txSanPham, null, null, null, null);
        });
        btnChonNhanVien.addActionListener((ae) -> {
            new ChonNhanVienForm(txNhanVien);
        });
        btnChonKhachHang.addActionListener((ae) -> {
            new ChonKhachHangForm(txKhachHang);
        });
        btnChonNhaCC.addActionListener((ae) -> {
            new ChonNhaCungCapForm(txNhaCC);
        });
        btnRefresh.addActionListener((ae) -> {
            // refresh();
        });
        // ================= End Event ====================

        plNhanVien = getPanelTieuChi("Nhân viên", chbNhanVien, txNhanVien, btnChonNhanVien);
        plSanPham = getPanelTieuChi("Sản phẩm", chbSanPham, txSanPham, btnChonSanPham);
        plKhachHang = getPanelTieuChi("Khách hàng", chbKhachHang, txKhachHang, btnChonKhachHang);
        plNhaCC = getPanelTieuChi("Nhà cung cấp", chbNhaCC, txNhaCC, btnChonNhaCC);

        JPanel plLoaiThongKe = new JPanel();
        plLoaiThongKe.setBorder(BorderFactory.createTitledBorder("Loại thống kê"));
        plLoaiThongKe.add(cbChonLoaiThongKe);

        plNhaCC.setVisible(false); // khởi đầu là thống kê bán ra nên chưa cần khung chọn ncc
        cbChonLoaiThongKe.addActionListener((ae) -> {
            calculate();
            switch (cbChonLoaiThongKe.getSelectedItem().toString()) {
                case "Bán ra":
                    plKhachHang.setVisible(true);
                    plNhaCC.setVisible(false);
                    break;
                case "Nhập vào":
                    plKhachHang.setVisible(false);
                    plNhaCC.setVisible(true);
                    break;
            }
        });

        // add
        // panel chon tieu chi
        JPanel plChonTieuChi = new JPanel();
        plChonTieuChi.setPreferredSize(new Dimension(300, 800));
        plChonTieuChi.add(plLoaiThongKe);
        plChonTieuChi.add(plChonNgay);
        plChonTieuChi.add(plNhanVien);
        plChonTieuChi.add(plSanPham);
        plChonTieuChi.add(plKhachHang);
        plChonTieuChi.add(plNhaCC);
        plChonTieuChi.add(btnRefresh);

        tbTong.setPreferredSize(new Dimension(tbTong.getPreferredSize().width, 70));

        JPanel plContent = new JPanel();
        plContent.setLayout(new BorderLayout());
        plContent.add(tb, BorderLayout.CENTER);
        plContent.add(tbTong, BorderLayout.SOUTH);

        this.add(plChonTieuChi, BorderLayout.WEST);
        this.add(plContent, BorderLayout.CENTER);
    }

    private JPanel getPanelTieuChi(String name, JCheckBox chb, JTextField tx, MoreButton b) {
        JPanel result = new JPanel();
        result.setBorder(BorderFactory.createTitledBorder(name));
        tx.setBorder(BorderFactory.createTitledBorder(" "));

        addDocumentListener(tx);

        if (chb != null) {
            tx.setEnabled(false);
            b.setEnabled(false);

            chb.addActionListener((ae) -> {
                tx.setEnabled(chb.isSelected());
                b.setEnabled(chb.isSelected());
                calculate();
            });
            result.add(chb);
        }

        result.add(tx);
        result.add(b);

        return result;
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                calculate();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }
        });
    }

    private void calculate() {
        String loaiThongKe = cbChonLoaiThongKe.getSelectedItem().toString();

        switch (loaiThongKe) {
            case "Bán ra":
                calculateBanRa();
                break;
            case "Nhập vào":
                calculateNhapVao();
                break;
        }
    }

    private void calculateBanRa() {
        // NhanVienBanRa();
        SanPhamBanRa();
    }

    // private void NhanVienBanRa() {
    // tb.clear();
    // tb.setHeaders(new String[] { "Mã", "Tên", "Số lượng bán ra", "Tổng tiền" });
    // tb.setAlignment(2, JLabel.CENTER);
    // tb.setAlignment(3, JLabel.RIGHT);

    // int tongAllSoLuong = 0;
    // float tongAllTien = 0;
    // int soluongNV = 0;

    // for (NhanVien nv : qlnvBUS.getDsnv()) {
    // if (!txNhanVien.getText().equals("") &&
    // !nv.getMaNV().equals(txNhanVien.getText())) {
    // continue;
    // }

    // int tongsoluong = 0;
    // float tongtien = 0;
    // MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);
    // for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(),
    // mcd.getNgayDen(), -1, -1)) {
    // if (hd.getMaNhanVien().equals(nv.getMaNV())) {
    // tongtien += hd.getTongTien();
    // for (ChiTietHoaDon cthd : qlcthdBUS.getAllChiTiet(hd.getMaHoaDon())) {
    // tongsoluong += cthd.getSoLuong();
    // }
    // }
    // }

    // tongAllSoLuong += tongsoluong;
    // tongAllTien += tongtien;

    // tb.addRow(new String[] {
    // nv.getMaNV(),
    // nv.getTenNV(),
    // String.valueOf(tongsoluong),
    // Format_money.format(tongtien)
    // });

    // soluongNV++;
    // }

    // tbTong.clear();
    // tbTong.setHeaders(new String[] { "TỔNG TẤT CẢ", "", "", "" });
    // tbTong.addRow(new String[] { "",
    // String.valueOf(soluongNV) + " nhân viên",
    // String.valueOf(tongAllSoLuong) + " sản phẩm",
    // Format_money.format(tongAllTien)
    // });
    // }

    private void SanPhamBanRa() {
        tb.clear();
        tb.setHeaders(new String[] { "Loại", "Mã sản phẩm", "Tên sản phẩm", "Số lượng bán ra", "Tổng tiền" });
        tb.setAlignment(3, JLabel.CENTER);
        tb.setAlignment(4, JLabel.RIGHT);

        int tongAllSoLuong = 0;
        float tongAllTien = 0;

        for (LoaiSanPham lsp : qllspBUS.getDslsp()) {

            tb.addRow(new String[] { lsp.getTenLSP().toUpperCase(), "", "", "", "" });

            for (SanPham sp : qlspBUS.getDssp()) {
                if (!txSanPham.getText().equals("") && !sp.getMaSP().equals(txSanPham.getText())
                        || !sp.getMaLSP().equals(lsp.getMaLSP())) {
                    continue;
                }

                int tongsoluong = 0;
                float tongtien = 0;
                MyCheckDate mcd = new MyCheckDate(txNgay1, txNgay2);
                for (HoaDon hd : qlhdBUS.search("Tất cả", "", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                    for (ChiTietHoaDon cthd : qlcthdBUS.getAllChiTiet(hd.getMaHoaDon())) {
                        if (cthd.getMaSanPham().equals(sp.getMaSP())) {
                            tongsoluong += cthd.getSoLuong();
                            tongtien += cthd.getDonGia() * cthd.getSoLuong();
                        }
                    }
                }

                tongAllSoLuong += tongsoluong;
                tongAllTien += tongtien;

                tb.addRow(new String[] {
                        "",
                        sp.getMaSP(),
                        sp.getTenSP(),
                        String.valueOf(tongsoluong),
                        Format_money.format(tongtien)
                });
            }

            tb.addRow(new String[] { "", "", "", "", "" });
        }

        tbTong.clear();
        tbTong.setHeaders(new String[] { "TỔNG TẤT CẢ", "", "", "", "" });
        tbTong.addRow(new String[] { "", "", "",
                String.valueOf(tongAllSoLuong) + " sản phẩm",
                Format_money.format(tongAllTien)
        });
    }

    private void calculateNhapVao() {
    }
}
