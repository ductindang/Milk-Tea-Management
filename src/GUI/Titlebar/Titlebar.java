package GUI.Titlebar;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

public class Titlebar extends Item {

    public Titlebar(Rectangle rec, String text) {
        super(rec, text);
        setLayout(new GridBagLayout());

        setFontSize(20);
        lbLabel.setForeground(Color.WHITE);
    }
}
