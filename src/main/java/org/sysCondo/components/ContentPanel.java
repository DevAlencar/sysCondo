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
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Bem-vindo ao SysCondo", JLabel.LEFT);
        welcomeLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel subtitleLabel = new JLabel("Gerenciando seu condomínio de forma otimizada e eficiente", JLabel.LEFT);
        subtitleLabel.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 10));
        contentPanel.add(subtitleLabel, BorderLayout.CENTER);

        JLabel logoLabel = new JLabel(new ImageIcon("src/main/java/org/sysCondo/assets/logo2.png"));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(logoLabel, BorderLayout.EAST);

        return contentPanel;
    }

    // Método para trocar o conteúdo (chamado pelo AdditionalOptionsPanel)
    public void setContent(JPanel newContent) {
        contentPanel.removeAll();
        contentPanel.add(newContent, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
