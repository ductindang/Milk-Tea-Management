package GUI.FormHienThi;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.*;

import BUS.*;
import DEV.*;
import GUI.GiaoDienChuan.*;

public class HienThiTaiKhoan extends FormHienThi {

    QuanLyTaiKhoanBUS qltk = new QuanLyTaiKhoanBUS();

    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");

    // index
    final int USERNAME_I = 1, PASSWORD_I = 2, MANV_I = 3, MAQUYEN_I = 4;

    public HienThiTaiKhoan() {
        setLayout(new BorderLayout());

        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[] { "STT", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền" });
        mtb.setColumnsWidth(new double[] { .5, 2, 2, 3, 2 });
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qltk.getDstk(), mtb);

        // ======== search panel ===========
        cbTypeSearch = new JComboBox<>(
                new String[] { "Tất cả", "Tên tài khoản", "Mật khẩu", "Mã nhân viên", "Mã quyền" });

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder(" ")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_data_backup_30px.png")));
        plHeader.add(btnRefresh);

        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        txTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                txSearchOnChange();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                txSearchOnChange();
            }
        });

        // =========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
    }

    public void refresh() {
        qltk.readDB();
        setDataToTable(qltk.getDstk(), mtb);
    }

    private void txSearchOnChange() {
        setDataToTable(qltk.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString()), mtb);
    }

    private void setDataToTable(ArrayList<TaiKhoan> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        for (TaiKhoan tk : data) {
            table.addRow(new String[] { String.valueOf(stt), tk.getUsername(), tk.getPassword(), tk.getMaNV(),
                    tk.getMaQuyen() });
            stt++;
        }
    }
}
