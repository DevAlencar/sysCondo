package org.sysCondo.views;

import org.sysCondo.components.Header;

import javax.swing.*;
import java.awt.*;

public class SysCondoMainScreen extends JFrame {

    public SysCondoMainScreen() {
        // Configurações da janela
        setTitle("SysCondo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // adiciona o header painel principal
        mainPanel.add(new Header(), BorderLayout.NORTH);

        // Barra lateral de navegação
        JPanel sideMenu = createSideMenu();
        mainPanel.add(sideMenu, BorderLayout.WEST);

        // Painel de conteúdo (parte direita da tela)
        JPanel contentPanel = createContentPanel();
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(mainPanel);

        // Exibe a janela
        setVisible(true);
    }

    // Método para criar a barra lateral de navegação
    private JPanel createSideMenu() {
        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(250, getHeight()));
        sideMenu.setBackground(new Color(235, 235, 235)); // Cor de fundo da barra lateral

        // Adiciona os botões da barra lateral com ícones ou emojis
        sideMenu.add(createSideMenuButton("🏠 Gestão de moradores"));
        sideMenu.add(createSideMenuButton("🏢 Gestão de residências"));
        sideMenu.add(createSideMenuButton("📥 Contas a receber"));
        sideMenu.add(createSideMenuButton("📤 Contas a pagar"));
        sideMenu.add(createSideMenuButton("🗓️ Reservas"));
        sideMenu.add(createSideMenuButton("🔧 Manutenções"));
        sideMenu.add(createSideMenuButton("💬 Comunicação"));

        return sideMenu;
    }

    // Método para criar um botão na barra lateral
    private JButton createSideMenuButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(240, 40));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16)); // Fonte compatível com emojis

        // Remove borda e fundo
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Alinha o texto à esquerda
        button.setHorizontalAlignment(SwingConstants.LEFT);

        // Remove foco pintado
        button.setFocusPainted(false);

        return button;
    }

    // Método para criar o painel de conteúdo
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Adiciona o título ao painel de conteúdo
        JLabel welcomeLabel = new JLabel("Bem-vindo ao SysCondo", JLabel.LEFT);
        welcomeLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        contentPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Adiciona o subtítulo ao painel de conteúdo
        JLabel subtitleLabel = new JLabel("Gerenciando seu condomínio de forma otimizada e eficiente", JLabel.LEFT);
        subtitleLabel.setFont(new Font("Roboto Light", Font.PLAIN, 16));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 10));
        contentPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Adiciona a logo ao painel de conteúdo
        JLabel logoLabel = new JLabel(new ImageIcon("C:\\Users\\Luyza\\IdeaProjects\\telaLogin\\src\\main\\resources\\logo2.png")); // Defina o caminho correto para a imagem
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(logoLabel, BorderLayout.EAST);

        return contentPanel;
    }

    public static void main(String[] args) {
        // Inicia a tela principal
        SwingUtilities.invokeLater(SysCondoMainScreen::new);
    }
}
