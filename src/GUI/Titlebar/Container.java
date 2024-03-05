package GUI.Titlebar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Container extends JPanel {

    private int bottomPos = 0;
    private ArrayList<Item> navItems = new ArrayList<>();

    public Container(Rectangle rec) {
        setLayout(null);
        setBounds(rec);
        setPreferredSize(new Dimension(rec.width, rec.height));
        setBackground(new Color(238, 180, 180));
    }

    public void addItem(Item item, Boolean fullWidth) {
        if (fullWidth) {
            item.setBounds(0, bottomPos, getWidth(), item.getHeight());
            if (item instanceof TitlebarButn) {
                TitlebarButn btnitem = (TitlebarButn) item;
                btnitem.relocate();
            }
        } else {
            item.setLocation(item.getBounds().x, item.getBounds().y);
        }

        bottomPos += item.getHeight();
        navItems.add(item); // add to arraylist
        add(item); // add to jpanel
    }

    public void addItem(Item i) {
        addItem(i, true);
    }

    public Item getItem(int i) {
        return navItems.get(i);
    }

    public int getLength() {
        return navItems.size();
    }
}
