package org.sysCondo.components;

import javax.swing.*;
import java.awt.*;

// Classe para criar um botão arredondado
public class RoundJButton extends JButton {
    private int cornerRadius;

    public RoundJButton(String text) {
        super(text);
        this.cornerRadius = 20; // Defina o raio desejado para os cantos arredondados
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false); // Para evitar que o fundo padrão do botão seja desenhado
        setForeground(Color.WHITE);
        setFont(new Font("Roboto Medium", Font.PLAIN, 12));
    }

    protected void paintComponent(Graphics g) {
        // Desenha o fundo do botão
        g.setColor(Color.BLACK);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Chama o método da superclasse para desenhar o texto do botão
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Define a cor da borda
        g.setColor(Color.WHITE);

        // Desenha a borda arredondada
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}
