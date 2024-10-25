package org.sysCondo.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdditionalOptionsPanel {

    private JPanel panel; // Atributo para o painel
    private boolean isVisible = false; // Controla a visibilidade
    private JPanel contentPanel; // Referência para o painel de conteúdo

    public AdditionalOptionsPanel(JPanel contentPanel) {
        this.contentPanel = contentPanel; // Recebe o painel de conteúdo da tela principal
        panel = createAdditionalOptionsPanel();
    }

    public JPanel getPanel() { // Este método retorna o painel que pode ser adicionado
        return panel;
    }

    private JPanel createAdditionalOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        // Botão "Adicionar Nova Conta"
        JButton addAccountButton = createStyledButton("Adicionar Nova Conta");
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Troca o conteúdo para o painel de adicionar contas
                JPanel addAccountPanel = new ContasAReceberAdd(); // Certifique-se que isso retorna um JPanel
                contentPanel.removeAll();
                contentPanel.add(addAccountPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });
        panel.add(addAccountButton);

        // Botão "Overview"
        JButton overviewButton = createStyledButton("Overview");
        overviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Troca o conteúdo para o painel de overview
                JPanel overviewPanel = new ContasAReceberOverview(); // Certifique-se que isso retorna um JPanel
                contentPanel.removeAll();
                contentPanel.add(overviewPanel, BorderLayout.CENTER);
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });
        panel.add(overviewButton);

        panel.setVisible(false); // Inicialmente invisível
        return panel; // Retorna o painel que pode ser adicionado ao menu lateral
    }

    // Método para criar os botões estilizados
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(240, 40));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Fonte personalizada

        // Remove a borda e o fundo do botão
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Alinha o texto à esquerda
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(10);

        // Remove o foco pintado
        button.setFocusPainted(false);

        return button;
    }

    public void toggleVisibility() {
        isVisible = !isVisible;
        panel.setVisible(isVisible);
    }
}
