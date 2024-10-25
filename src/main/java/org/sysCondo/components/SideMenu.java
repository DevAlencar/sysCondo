package org.sysCondo.components;

import org.sysCondo.views.SysCondoMainScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenu {

    private JPanel sideMenuPanel; // Painel da barra lateral
    private AdditionalOptionsPanel additionalOptionsPanel; // Painel de opções adicionais
    private JPanel contentPanel; // Referência para o painel de conteúdo

    public SideMenu(JPanel contentPanel) {
        this.contentPanel = contentPanel; // Inicializa com o painel de conteúdo
        sideMenuPanel = createSideMenu(); // Cria o painel da barra lateral
    }

    public JPanel getSideMenuPanel() {
        return sideMenuPanel;
    }

    private JPanel createSideMenu() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(250, contentPanel.getHeight())); // Corrigido para usar o conteúdo do painel
        sideMenu.setBackground(new Color(235, 235, 235)); // Cor de fundo da barra lateral

        // Adiciona botões com ícones
        sideMenu.add(createSideMenuButton("Gestão de moradores", "src/main/java/org/sysCondo/assets/people.png"));
        sideMenu.add(createSideMenuButton("Gestão de residências", "src/main/java/org/sysCondo/assets/house.png"));

        // Botão "Contas a receber" que alterna opções adicionais
        JButton contasAReceberButton = createSideMenuButton("Contas a receber", "src/main/java/org/sysCondo/assets/receive.png");
        contasAReceberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Alterna a visibilidade das opções adicionais
                additionalOptionsPanel.toggleVisibility();
            }
        });
        sideMenu.add(contasAReceberButton);

        // Painel de opções adicionais
        additionalOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        sideMenu.add(additionalOptionsPanel.getPanel());

        // Outros botões
        sideMenu.add(createSideMenuButton("Contas a pagar", "src/main/java/org/sysCondo/assets/pay.png"));
        sideMenu.add(createSideMenuButton("Reservas", "src/main/java/org/sysCondo/assets/calendar.png"));
        sideMenu.add(createSideMenuButton("Manutenções", "src/main/java/org/sysCondo/assets/maintenance.png"));
        sideMenu.add(createSideMenuButton("Comunicação", "src/main/java/org/sysCondo/assets/chat.png"));

        return sideMenu;
    }

    private JButton createSideMenuButton(String text, String iconPath) {
        // Cria o botão com ícone e estilo (o mesmo código do original)
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImage);

        JButton button = new JButton(text, icon);
        button.setPreferredSize(new Dimension(240, 40));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(10);
        button.setFocusPainted(false);

        return button;
    }
}
