/* */
package GUI.FormQuanLy;

import javax.swing.JPanel;

public class NhapHangForm extends JPanel {

    public NhapHangForm(int width, int height) {
        setLayout(null);

        ChonSanPhamPanel cspnh = new ChonSanPhamPanel(0, 0, width - 555, height);
        this.add(cspnh);

        PhieuNhapHang pnh = new PhieuNhapHang(width - 550, 0, 550, height);
        this.add(pnh);

        pnh.setTarget(cspnh);
        cspnh.setTarget(pnh);
    }
}
