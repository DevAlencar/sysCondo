package org.sysCondo.components;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Header extends JPanel {
    private ImageIcon logo;
    public Header () {
        this.setBackground(Color.WHITE); // seta a cor de background do header
        this.setLayout(new FlowLayout(FlowLayout.LEFT)); // seta o layout pra flowlayout
        this.setBorder(new EmptyBorder(0, 30, 0, 30)); // adiciona padding horizontal de 30
        this.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 25))); // adiciona borda inferior

        logo = new ImageIcon("src/main/java/org/sysCondo/assets/transparentlogo.PNG"); // cria instancia da imagem
        logo = new ImageIcon(logo.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)); // escala a imagem para 60 por 60
        JLabel logoContainer = new JLabel(); // cria container da imagem
        logoContainer.setIcon(logo); // coloca a imagem no container
        logoContainer.setPreferredSize(new Dimension(60, 60)); // seta o tamanho do container

        JLabel companyName = new JLabel("SYSCONDO"); // cria texto da empresa
        companyName.setFont(new Font("Arial", Font.BOLD, 25)); // seta a fonte para arial bold 25px
        companyName.setForeground(Color.black); // seta a cor do texto para preto

        this.add(logoContainer); // adiciona container de imagem ao header
        this.add(companyName); // adiciona nome da empresa ao header
    }
}
