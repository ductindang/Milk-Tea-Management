package GUI.FormChon;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

import GUI.FormHienThi.*;

/**
 *
 * @author DELL
 */
public class ChonKhuyenMaiForm extends JFrame {

    HienThiKhuyenMai formHienThi = new HienThiKhuyenMai();

    JButton btnOK = new JButton("Chọn");
    JButton btnCancel = new JButton("Thoát");
    JTextField txTarget;

    public ChonKhuyenMaiForm(JTextField _txTarget) {
        this.setTitle("Chọn khuyến mãi");
        this.setLayout(new BorderLayout());
        this.setSize(1200 - 200, 600);
        this.setLocationRelativeTo(null);
        this.txTarget = _txTarget;

        // ======= Buttons Panel ===========
        btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_cancel_30px_1.png")));
        btnOK.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_ok_30px.png")));

        JPanel plBtns = new JPanel();
        plBtns.add(btnOK);
        plBtns.add(btnCancel);

        this.add(formHienThi, BorderLayout.CENTER);
        this.add(plBtns, BorderLayout.SOUTH);
        this.setVisible(true);

        // actionlistener
        btnOK.addActionListener((ActionEvent ae) -> {
            String makm = formHienThi.getSelectedRow(1);
            if (makm != null) {
                this.txTarget.setText(makm);
                this.dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Chưa chọn khuyến mãi nào!");
            }
        });

        btnCancel.addActionListener((ae) -> {
            this.dispose();
        });
    }
}
