package GUI.FormQuanLy;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import BUS.*;
import GUI.FormHienThi.*;
import GUI.FormThemSua.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

public class QuanLyNhanVienForm extends JPanel {

    HienThiNhanVien formHienThi = new HienThiNhanVien();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();

    public QuanLyNhanVienForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlNhanVien")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);

        this.add(plBtn, BorderLayout.NORTH);
        this.add(formHienThi, BorderLayout.CENTER);

        // actionlistener
        btnThem.addActionListener((ActionEvent ae) -> {
            btnThemMouseClicked();
        });
        btnXoa.addActionListener((ActionEvent ae) -> {
            btnXoaMouseClicked();
        });
        btnSua.addActionListener((ActionEvent ae) -> {
            btnSuaMouseClicked();
        });
    }

    private void btnSuaMouseClicked() {
        String manv = formHienThi.getSelectedRow(1);
        if (manv != null) {
            ThemSuaNhanVienForm suanv = new ThemSuaNhanVienForm("Sửa", manv);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suanv.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String manv = formHienThi.getSelectedRow(1);
        if (manv != null) {
            QuanLyNhanVienBUS qlnvBUS = new QuanLyNhanVienBUS();
            if (qlnvBUS.getNhanVien(manv).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên " + manv + " ? "
                        + "Nhân viên sẽ được TẠM ẨN", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlnvBUS.updateTrangThai(manv, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN nhân viên " + manv + " ?",
                        "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlnvBUS.delete(manv);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn nhân viên nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaNhanVienForm themnv = new ThemSuaNhanVienForm("Thêm", "");
        themnv.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
