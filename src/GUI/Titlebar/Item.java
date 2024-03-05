package GUI.Titlebar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Item extends JPanel {

    protected JLabel lbLabel;
    protected Color bgDefault, colorDefault;
    protected int fontSize = 16;
    public String text;

    public Item(Rectangle rec, String text) {
        this.bgDefault = new Color(238, 180, 180);
        this.colorDefault = new Color(70, 45, 45);
        this.text = text;

        setLayout(null);
        setBounds(rec);
        setBackground(this.bgDefault);

        lbLabel = new JLabel(text);
        lbLabel.setForeground(this.colorDefault);
        setFontSize(fontSize);
        add(lbLabel);

        setLabel(text);
    }

    public void setLabel(String text) {
        lbLabel.setText(text);
    }

    public void setFontSize(int size) {
        fontSize = size;
        lbLabel.setFont(new Font(Font.DIALOG, Font.BOLD, fontSize));
    }

    public void setBgDefault(Color bgDefault) {
        this.bgDefault = bgDefault;
        setBackground(this.bgDefault);
    }

    public void setColorDefault(Color colorDefault) {
        this.colorDefault = colorDefault;
        lbLabel.setForeground(this.colorDefault);
    }
}
