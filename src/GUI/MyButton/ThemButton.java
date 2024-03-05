/* */
package GUI.MyButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ThemButton extends JButton {

    public ThemButton() {
        this.setText("Thêm");
        this.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_add_30px.png")));
    }
}
