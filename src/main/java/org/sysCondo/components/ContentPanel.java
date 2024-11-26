package org.sysCondo.components;

import javax.swing.*;
import java.awt.*;

public class ContentPanel {

    private JPanel contentPanel;

    public ContentPanel() {
        contentPanel = createContentPanel(); // Cria o painel de conteúdo
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new CardLayout());
        contentPanel.setBackground(Color.WHITE);

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
        welcomePanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Bem-vindo ao SysCondo", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 38));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        welcomePanel.add(welcomeLabel);

        JLabel subtitleLabel = new JLabel("Gerenciando seu condomínio de forma otimizada e eficiente", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        welcomePanel.add(subtitleLabel);

        JLabel logoLabel = new JLabel(new ImageIcon("src/main/java/org/sysCondo/assets/logo2.png"));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
        welcomePanel.add(logoLabel);

        contentPanel.add(welcomePanel, "Welcome");

        return contentPanel;
    }

}
