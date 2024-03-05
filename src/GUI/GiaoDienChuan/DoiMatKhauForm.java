/**/
package GUI.GiaoDienChuan;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;

import BUS.QuanLyTaiKhoanBUS;
import DEV.*;

import javax.swing.*;

public class DoiMatKhauForm extends JFrame {

    TaiKhoan tk;

    JPasswordField txMatKhauCu = new JPasswordField(15);
    JPasswordField txMatKhauMoi = new JPasswordField(15);
    JPasswordField txXacNhanMatKhau = new JPasswordField(15);

    JButton btnDongY = new JButton("Đồng ý");
    JButton btnHuy = new JButton("Hủy");

    public DoiMatKhauForm(String matk) {
        setLayout(new BorderLayout());
        setSize(350, 350);
        setTitle("Đổi mật khẩu");
        setLocationRelativeTo(null);
        tk = new QuanLyTaiKhoanBUS().getTaiKhoan(matk);

        // input
        JPanel plInput = new JPanel();
        txMatKhauCu.setBorder(BorderFactory.createTitledBorder("Mật khẩu cũ: "));
        txMatKhauMoi.setBorder(BorderFactory.createTitledBorder("Mật khẩu mới: "));
        txXacNhanMatKhau.setBorder(BorderFactory.createTitledBorder("Xác nhận mật khẩu: "));

        plInput.add(txMatKhauCu);
        plInput.add(txMatKhauMoi);
        plInput.add(txXacNhanMatKhau);

        this.add(plInput, BorderLayout.CENTER);

        // button
        JPanel plButton = new JPanel();
        plButton.add(btnDongY);
        plButton.add(btnHuy);

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_cancel_30px_1.png")));
        btnDongY.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_ok_30px.png")));

        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnDongY.addActionListener((ae) -> {
            if (checkPass()) {
                if (new QuanLyTaiKhoanBUS().update(tk.getUsername(), new String(txMatKhauMoi.getPassword()),
                        tk.getMaNV(),
                        tk.getMaQuyen())) {
                    JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
                    this.dispose();
                }

            }
        });

        this.add(plButton, BorderLayout.SOUTH);
    }

    private Boolean checkPass() {
        String mkcu = new String(txMatKhauCu.getPassword());
        String mkmoi = new String(txMatKhauMoi.getPassword());
        String xnmk = new String(txXacNhanMatKhau.getPassword());

        if (!mkcu.equals(tk.getPassword())) {
            JOptionPane.showMessageDialog(txMatKhauCu, "Mật khẩu cũ không đúng!");
            txMatKhauCu.requestFocus();
            return false;

        } else if (mkmoi.equals("") || xnmk.equals("")) {
            JOptionPane.showMessageDialog(txMatKhauMoi, "Mật khẩu mới không được để trống!");
            txMatKhauMoi.requestFocus();
            return false;

        } else if (!mkmoi.equals(xnmk)) {
            JOptionPane.showMessageDialog(txXacNhanMatKhau, "Mật khẩu mới không khớp!");
            txXacNhanMatKhau.requestFocus();
            return false;
        }

        return true;
    }

}
