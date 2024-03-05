package GUI.FormQuanLy;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import BUS.*;
import GUI.FormHienThi.*;
import GUI.FormThemSua.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

public class QuanLyTaiKhoanForm extends JPanel {

    HienThiTaiKhoan formHienThi = new HienThiTaiKhoan();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();

    public QuanLyTaiKhoanForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlTaiKhoan")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);

        // =========== add all to this jpanel ===========
        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtn, BorderLayout.NORTH);

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
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            ThemSuaTaiKhoanForm suatk = new ThemSuaTaiKhoanForm("Sửa", masp);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suatk.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn tài khoản nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String malsp = formHienThi.getSelectedRow(1);
        if (malsp != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa tài khoản " + malsp + " ?", "Chú ý",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new QuanLyTaiKhoanBUS().delete(malsp);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn tài khoản nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaTaiKhoanForm themtk = new ThemSuaTaiKhoanForm("Thêm", "");

        themtk.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
