package GUI.FormHienThi;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import javax.swing.event.*;

import BUS.*;
import DEV.*;
import GUI.GiaoDienChuan.*;

public class HienThiNhanVien extends FormHienThi {

    QuanLyNhanVienBUS qlnv = new QuanLyNhanVienBUS();

    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");

    JTextField txKhoangNgay1 = new JTextField(8);
    JTextField txKhoangNgay2 = new JTextField(8);
    DatePicker dPicker1;
    DatePicker dPicker2;

    // index
    final int MANV_I = 1, TENNV_I = 2, NGAYSINH_I = 3, DIACHI_I = 4, SDT_I = 5;

    public HienThiNhanVien() {
        setLayout(new BorderLayout());

        // khoang ngay
        DatePickerSettings pickerSettings = new DatePickerSettings();
        pickerSettings.setVisibleDateTextField(false);
        dPicker1 = new DatePicker(pickerSettings);
        dPicker2 = new DatePicker(pickerSettings.copySettings());
        dPicker1.setDateToToday();
        dPicker2.setDateToToday();

        txKhoangNgay1.setBorder(BorderFactory.createTitledBorder("Từ:"));
        txKhoangNgay2.setBorder(BorderFactory.createTitledBorder("Đến:"));

        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[] { "STT", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "Số điện thoại",
                "Trạng thái" });
        mtb.setColumnsWidth(new double[] { .5, 1.5, 2.5, 1.3, 3, 1.5, 1 });
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlnv.getDsnv(), mtb);

        // ======== search panel ===========
        cbTypeSearch = new JComboBox<>(new String[] { "Tất cả", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ",
                "Số điện thoại", "Trạng thái" });

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

        // pl tim khoang ngay
        JPanel plTimKiemKhoangNgay = new JPanel();
        plTimKiemKhoangNgay.setBorder(BorderFactory.createTitledBorder("Ngày sinh:"));
        plTimKiemKhoangNgay.add(txKhoangNgay1);
        plTimKiemKhoangNgay.add(dPicker1);
        plTimKiemKhoangNgay.add(txKhoangNgay2);
        plTimKiemKhoangNgay.add(dPicker2);
        plHeader.add(plTimKiemKhoangNgay);

        btnRefresh.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_data_backup_30px.png")));
        plHeader.add(btnRefresh);

        cbTypeSearch.addActionListener((ActionEvent e) -> {
            txTim.setBorder(BorderFactory.createTitledBorder(cbTypeSearch.getSelectedItem().toString()));
            txTim.requestFocus();
            if (!txTim.getText().equals("")) {
                txSearchOnChange();
            }
        });

        btnRefresh.addActionListener((ae) -> {
            refresh();
        });

        dPicker1.addDateChangeListener((dce) -> {
            txKhoangNgay1.setText(dPicker1.getDateStringOrEmptyString());
        });
        dPicker2.addDateChangeListener((dce) -> {
            txKhoangNgay2.setText(dPicker2.getDateStringOrEmptyString());
        });

        // https://stackoverflow.com/questions/3953208/value-change-listener-to-jtextfield
        addDocumentListener(txTim);
        addDocumentListener(txKhoangNgay1);
        addDocumentListener(txKhoangNgay2);

        // =========== add all to this jpanel ===========
        this.add(plHeader, BorderLayout.NORTH);
        this.add(mtb, BorderLayout.CENTER);
    }

    public void refresh() {
        qlnv.readDB();
        setDataToTable(qlnv.getDsnv(), mtb);
        txTim.setText("");
        txKhoangNgay1.setText("");
        txKhoangNgay2.setText("");
    }

    private void addDocumentListener(JTextField txField) {
        txField.getDocument().addDocumentListener(new DocumentListener() {
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
    }

    private void txSearchOnChange() {
        LocalDate ngay1 = null, ngay2 = null;
        try {
            ngay1 = java.time.LocalDate.parse(txKhoangNgay1.getText());
            txKhoangNgay1.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay1.setForeground(Color.red);
        }
        try {
            ngay2 = java.time.LocalDate.parse(txKhoangNgay2.getText());
            txKhoangNgay2.setForeground(Color.black);
        } catch (DateTimeParseException e) {
            txKhoangNgay2.setForeground(Color.red);
        }
        setDataToTable(qlnv.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString(), ngay1, ngay2), mtb);
    }

    private void setDataToTable(ArrayList<NhanVien> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại
        Boolean hienNhanVienAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlNhanVien");
        for (NhanVien nv : data) {
            if (hienNhanVienAn || nv.getTrangThai() == 0) {
                table.addRow(new String[] {
                        String.valueOf(stt),
                        nv.getMaNV(),
                        nv.getTenNV(),
                        nv.getNgaySinh().toString(),
                        nv.getDiaChi(),
                        nv.getSDT(),
                        (nv.getTrangThai() == 0 ? "Hiện" : "Ẩn")
                });
                stt++;
            }

        }
    }
}
