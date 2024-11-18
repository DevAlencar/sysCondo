package org.sysCondo.types;

import javax.swing.*;

public class MenuItem {
    String title;
    JPanel panel;

    public MenuItem(String title, JPanel panel) {
        this.title = title;
        this.panel = panel;
    }

    public String getTitle() {
        return title;
    }

    public JPanel getPanel() {
        return panel;
    }
}
