/* */
package GUI.FormHienThi;

import javax.swing.JPanel;
import GUI.GiaoDienChuan.*;

public class FormHienThi extends JPanel {

    MyTable mtb;

    public FormHienThi() {

    }

    public String getSelectedRow(int col) {
        int i = mtb.getTable().getSelectedRow();
        if (i >= 0) {
            int realI = mtb.getTable().convertRowIndexToModel(i);
            return mtb.getModel().getValueAt(realI, col).toString();
        }
        return null;
    }

    public MyTable getTable() {
        return this.mtb;
    }
}
