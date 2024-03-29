/**/
package GUI.FormHienThi;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.*;

import BUS.*;
import DEV.*;
import GUI.GiaoDienChuan.*;

public class HienThiKhuyenMai extends FormHienThi {

    QuanLyKhuyenMaiBUS qlkmBUS = new QuanLyKhuyenMaiBUS();

    JTextField txTim = new JTextField(15);
    JComboBox<String> cbTypeSearch;
    JButton btnRefresh = new JButton("Làm mới");

    // index
    final int MAKM_I = 1, TENKM_I = 2;

    public HienThiKhuyenMai() {
        setLayout(new BorderLayout());

        mtb = new MyTable();
        mtb.setPreferredSize(new Dimension(1200 - 250, 600));
        mtb.setHeaders(new String[] { "STT", "Mã", "Tên", "Điều kiện", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc",
                "Trạng thái" });
        mtb.setColumnsWidth(new double[] { .5, .5, 1.5, .7, .5, 1, 1, 1 });
        mtb.setAlignment(0, JLabel.CENTER);
        mtb.setAlignment(3, JLabel.RIGHT);
        mtb.setAlignment(4, JLabel.CENTER);
        mtb.setupSort();
        setDataToTable(qlkmBUS.getDskm(), mtb);

        // ======== search panel ===========
        cbTypeSearch = new JComboBox<>(new String[] { "Tất cả", "Mã khuyến mãi", "Tên khuyến mãi",
                "Điều kiện khuyến mãi", "Phần trăm khuyến mãi", "Ngày bắt đầu", "Ngày kết thúc" });

        JPanel plHeader = new JPanel();
        JPanel plTim = new JPanel();
        plTim.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
        txTim.setBorder(BorderFactory.createTitledBorder("Tất cả")); // tạo border rỗng
        plTim.add(cbTypeSearch);
        plTim.add(txTim);
        plHeader.add(plTim);

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
        qlkmBUS.readDB();
        setDataToTable(qlkmBUS.getDskm(), mtb);
    }

    private void txSearchOnChange() {
        setDataToTable(
                qlkmBUS.search(txTim.getText(), cbTypeSearch.getSelectedItem().toString(), -1, -1, -1, -1, null, null),
                mtb);
    }

    private void setDataToTable(ArrayList<KhuyenMai> data, MyTable table) {
        table.clear();
        int stt = 1; // lưu số thứ tự dòng hiện tại

        for (KhuyenMai km : data) {
            table.addRow(new String[] {
                    String.valueOf(stt),
                    km.getMaKM(),
                    km.getTenKM(),
                    "≥ " + Format_money.format(km.getDieuKienKM()),
                    String.valueOf(km.getPhanTramKM()) + " %",
                    String.valueOf(km.getNgayBD()),
                    String.valueOf(km.getNgayKT()),
                    km.getTrangThai()
            });
            stt++;
        }
    }
}
