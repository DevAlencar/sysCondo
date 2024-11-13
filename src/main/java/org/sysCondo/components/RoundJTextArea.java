package org.sysCondo.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;

public class RoundJTextArea extends JTextArea {
    private int cornerRadius;

    public RoundJTextArea(int rows, int columns, int cornerRadius) {
        super(rows, columns);
        this.cornerRadius = cornerRadius;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 5, 5, 5)); // Define um espaço de borda interno
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Usuários relatam se certificar que a superchamada é invocada corretamente e no ponto esperado
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
        g2.dispose();

        setForeground(getForeground());
        super.paintComponent(g); // Super chamada combinada com controle da cor de frente
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getForeground());
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));
        g2.dispose();
    }

    @Override
    public Insets getInsets() {
        return new Insets(10, 10, 10, 10); // Ajuste de espaço interno, conforme necessidade
    }
}