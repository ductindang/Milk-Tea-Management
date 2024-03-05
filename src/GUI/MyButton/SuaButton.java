/* */
package GUI.MyButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SuaButton extends JButton {
    public SuaButton() {
        this.setText("Sửa");
        this.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_support_30px.png")));
    }
}
