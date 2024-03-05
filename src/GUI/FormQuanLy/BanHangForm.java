/**/
package GUI.FormQuanLy;

import javax.swing.JPanel;

public class BanHangForm extends JPanel {

    public BanHangForm(int width, int height) {
        setLayout(null);

        ChonSanPhamPanel cspbh = new ChonSanPhamPanel(0, 0, width - 555, height);
        this.add(cspbh);

        HoaDonBanHang hdbh = new HoaDonBanHang(width - 550, 0, 550, height);
        this.add(hdbh);

        hdbh.setTarget(cspbh);
        cspbh.setTarget(hdbh);
    }
}
