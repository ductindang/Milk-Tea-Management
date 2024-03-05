package GUI.FormQuanLy;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

import BUS.*;
import DEV.*;
import GUI.FormHienThi.*;
import GUI.FormThemSua.*;
import GUI.GiaoDienChuan.*;
import GUI.MyButton.*;

public class QuanLyHoaDonForm extends JPanel {

    HienThiHoaDon formHienThi = new HienThiHoaDon();
    ThemButton btnThem = new ThemButton();
    SuaButton btnSua = new SuaButton();
    XoaButton btnXoa = new XoaButton();
    JButton btnPrintPDF = new JButton("In PDF");

    public QuanLyHoaDonForm() {
        setLayout(new BorderLayout());

        btnThem.setEnabled(false);
        btnXoa.setEnabled(false);
        btnSua.setEnabled(false);

        JPanel plBtn = new JPanel();

        plBtn.add(btnPrintPDF);

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

        btnPrintPDF.addActionListener((ae) -> {
            if (formHienThi.getSelectedRow(0) != null) {
                MyTable mtb = formHienThi.getTable();
                new WritePDF()
                        .writeHoaDon(String.valueOf(mtb.getTable().getValueAt(mtb.getTable().getSelectedRow(), 1)));
            } else {
                JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để in");
            }
        });
    }

    private void btnSuaMouseClicked() {
        String mahd = formHienThi.getSelectedRow(1);
        if (mahd != null) {
            ThemSuaHoaDonForm tshd = new ThemSuaHoaDonForm("Sửa", mahd);
            tshd.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    formHienThi.refresh();
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn hóa đơn nào để sửa");
        }
    }

    private void btnXoaMouseClicked() {
        String mahd = formHienThi.getSelectedRow(1);
        if (mahd != null) {
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hóa đơn " + mahd
                    + " ? Mọi chi tiết trong hóa đơn sẽ bị xóa theo",
                    "Chú ý", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {

                new QuanLyChiTietHoaDonBUS().deleteAll(mahd);
                new QuanLyHoaDonBUS().delete(mahd);

                formHienThi.refresh();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm nào để xóa");
        }
    }

    private void btnThemMouseClicked() {
        ThemSuaHoaDonForm themhd = new ThemSuaHoaDonForm("Thêm", "");
        themhd.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                formHienThi.refresh();
            }
        });
    }
}
