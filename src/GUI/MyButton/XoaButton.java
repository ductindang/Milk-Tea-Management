/* */
package GUI.MyButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class XoaButton extends JButton {
    public XoaButton() {
        this.setText("Xóa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_delete_forever_30px_1.png")));
    }
}
