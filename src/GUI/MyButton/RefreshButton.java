/* */
package GUI.MyButton;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RefreshButton extends JButton {

    public RefreshButton() {
        this.setText("Làm mới");
        this.setIcon(new ImageIcon(this.getClass().getResource("/images/icons8_data_backup_30px.png")));
    }
}
