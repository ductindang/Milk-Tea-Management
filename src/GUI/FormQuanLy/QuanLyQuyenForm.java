package GUI.FormQuanLy;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import BUS.*;
import GUI.FormHienThi.*;
import GUI.FormThemSua.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

public class QuanLyQuyenForm extends JPanel {

    HienThiQuyen formHienThi = new HienThiQuyen();

    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();

    public QuanLyQuyenForm() {
        setLayout(new BorderLayout());

        // buttons
        if (!LoginForm.quyenLogin.getChiTietQuyen().contains("qlQuyen")) {
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
            formHienThi.refresh();
        });
    }

    private void btnSuaMouseClicked() {
        String maquyen = formHienThi.getSelectedRow(1);
        if (maquyen != null) {
            ThemSuaQuyenForm suaq = new ThemSuaQuyenForm("Sửa", maquyen);

            // https://stackoverflow.com/questions/4154780/jframe-catch-dispose-event
            suaq.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    formHienThi.refresh();
                }
            });

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn quyền nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String maquyen = formHienThi.getSelectedRow(1);
        if (maquyen != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa quyền " + maquyen + " ?", "Chú ý",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                new QuanLyQuyenBUS().delete(maquyen);
                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn quyền nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaQuyenForm themq = new ThemSuaQuyenForm("Thêm", "");
        themq.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                formHienThi.refresh();
            }
        });
    }
}
