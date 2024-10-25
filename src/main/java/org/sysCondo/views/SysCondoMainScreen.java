package org.sysCondo.views;

import org.sysCondo.components.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SysCondoMainScreen extends JFrame {

    private JPanel contentPanel; // Moved outside to update it dynamically
    private JPanel additionalOptionsPanel; // Panel for additional options
    private boolean areAdditionalOptionsVisible = false; // Track visibility of additional options

    public SysCondoMainScreen() {
        // Configurações da janela
        setTitle("SysCondo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null); // Centraliza a janela

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // adiciona o header painel principal
        mainPanel.add(new Header(), BorderLayout.NORTH);

        // Barra lateral de navegação
        JPanel sideMenu = createSideMenu();
        mainPanel.add(sideMenu, BorderLayout.WEST);

        // Painel de conteúdo (parte direita da tela)
        contentPanel = createContentPanel(); // Initialize content panel
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

        // Adiciona os botões da barra lateral com ícones
        sideMenu.add(createSideMenuButton("Gestão de moradores", "src/main/java/org/sysCondo/assets/people.png"));
        sideMenu.add(createSideMenuButton("Gestão de residências", "src/main/java/org/sysCondo/assets/house.png"));

        // "Contas a receber" button with ActionListener to switch content
        JButton contasAReceberButton = createSideMenuButton("Contas a receber", "src/main/java/org/sysCondo/assets/receive.png");
        contasAReceberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToContasAReceberContent();
            }
        });
        sideMenu.add(contasAReceberButton);

        // Panel for additional options
        additionalOptionsPanel = createAdditionalOptionsPanel();
        sideMenu.add(additionalOptionsPanel); // Initially hidden

        sideMenu.add(createSideMenuButton("Contas a pagar", "src/main/java/org/sysCondo/assets/pay.png"));
        sideMenu.add(createSideMenuButton("Reservas", "src/main/java/org/sysCondo/assets/calendar.png"));
        sideMenu.add(createSideMenuButton("Manutenções", "src/main/java/org/sysCondo/assets/maintenance.png"));
        sideMenu.add(createSideMenuButton("Comunicação", "src/main/java/org/sysCondo/assets/chat.png"));

        return sideMenu;
    }

    // Método para criar um botão na barra lateral com ícone
    private JButton createSideMenuButton(String text, String iconPath) {
        // Carrega a imagem como um ícone
        ImageIcon icon = new ImageIcon(iconPath);
        // Redimensiona o ícone se necessário (exemplo: 20x20 pixels)
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(resizedImage);

        // Cria o botão com texto e ícone
        JButton button = new JButton(text, icon);
        button.setPreferredSize(new Dimension(240, 40));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Define a fonte do texto

        // Remove a borda e o fundo do botão
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Alinha o texto e o ícone à esquerda
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(10); // Espaço entre o ícone e o texto

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
        JLabel logoLabel = new JLabel(new ImageIcon("src/main/java/org/sysCondo/assets/logo2.png")); // Defina o caminho correto para a imagem
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(logoLabel, BorderLayout.EAST);

        return contentPanel;
    }

    // Método para criar o painel com opções adicionais
    private JPanel createAdditionalOptionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 10));

        // Botão "Adicionar Nova Conta"
        JButton addAccountButton = createAdditionalOptionButton("Adicionar Nova Conta");
        addAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para adicionar nova conta aqui
                JOptionPane.showMessageDialog(panel, "Adicionar nova conta clicado");
            }
        });
        panel.add(addAccountButton);

        // Botão "Overview"
        JButton overviewButton = createAdditionalOptionButton("Overview");
        overviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica para visualizar o overview aqui
                JOptionPane.showMessageDialog(panel, "Overview clicado");
            }
        });
        panel.add(overviewButton);

        panel.setVisible(false); // Initially hidden
        return panel;
    }

    // Método para criar um botão de opções adicionais com o mesmo estilo
    private JButton createAdditionalOptionButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(240, 40));
        button.setMaximumSize(new Dimension(240, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Define a fonte do texto


        // Remove a borda e o fundo do botão
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        // Alinha o texto à esquerda
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setIconTextGap(10); // Espaço entre o ícone e o texto

        // Remove foco pintado
        button.setFocusPainted(false);

        return button;
    }

    // Método para alterar o conteúdo do painel ao clicar em "Contas a receber"
    private void switchToContasAReceberContent() {
        // Adiciona o título para "Contas a Receber" se não estiver presente
        if (contentPanel.getComponentCount() == 0) {
            JLabel contasTitleLabel = new JLabel("Contas a Receber", JLabel.LEFT);
            contasTitleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
            contasTitleLabel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
            contentPanel.add(contasTitleLabel, BorderLayout.NORTH);
        }

        // Alterna a visibilidade do painel de opções adicionais
        if (areAdditionalOptionsVisible) {
            additionalOptionsPanel.setVisible(false);
            areAdditionalOptionsVisible = false;
        } else {
            additionalOptionsPanel.setVisible(true);
            areAdditionalOptionsVisible = true;
        }

        // Atualiza o painel de conteúdo
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        // Inicia a tela principal
        SwingUtilities.invokeLater(SysCondoMainScreen::new);
    }
}
