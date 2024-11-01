package org.sysCondo.components;

import org.sysCondo.types.MenuItem;

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
        createAdditionalOptionsPanel(new MenuItem[0]);
    }

    public JPanel getPanel() { // Este método retorna o painel que pode ser adicionado
        return panel;
    }

    public void createAdditionalOptionsPanel(MenuItem[] items) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        for (MenuItem item : items) {
            JButton btn = createStyledButton(item.getTitle());
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = item.getPanel(); // Certifique-se que isso retorna um JPanel
                    contentPanel.removeAll();
                    contentPanel.add(panel, BorderLayout.CENTER);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                }
            });
            panel.add(btn);
        }

        panel.setVisible(false); // Inicialmente invisível
        this.panel = panel;
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
