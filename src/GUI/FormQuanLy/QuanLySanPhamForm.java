package GUI.FormQuanLy;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import BUS.*;
import GUI.FormHienThi.*;
import GUI.FormThemSua.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

public class QuanLySanPhamForm extends JPanel {

    HienThiSanPham formHienThi = new HienThiSanPham();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();

    public QuanLySanPhamForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlSanPham")) {
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnSua.setEnabled(false);
        }

        JPanel plBtn = new JPanel();
        plBtn.add(btnThem);
        plBtn.add(btnXoa);
        plBtn.add(btnSua);

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
            ThemSuaSanPhamForm suasp = new ThemSuaSanPhamForm("Sửa", masp);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suasp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String masp = formHienThi.getSelectedRow(1);
        if (masp != null) {
            QuanLySanPhamBUS qlspBUS = new QuanLySanPhamBUS();
            if (qlspBUS.getSanPham(masp).getTrangThai() == 0) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sản phẩm " + masp + " ? "
                        + "Sản phẩm sẽ được TẠM ẨN", "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.updateTrangThai(masp, 1);
                    formHienThi.refresh();
                }
            } else {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn XÓA HOÀN TOÀN sản phẩm " + masp + " ?",
                        "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                    qlspBUS.delete(masp);
                    formHienThi.refresh();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaSanPhamForm themsp = new ThemSuaSanPhamForm("Thêm", "");
        themsp.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
