package org.sysCondo.views;

import org.sysCondo.components.Header;
import org.sysCondo.components.ContentPanel;
import org.sysCondo.components.SideMenu;

import javax.swing.*;
import java.awt.*;

public class SysCondoMainScreen extends JFrame {

    private ContentPanel contentPanel; // Painel de conteúdo
    private SideMenu sideMenu; // Menu lateral
    private CardLayout cardLayout;
    private ContasAReceberOverview overviewPanel;
    private ContasAReceberAdd addPanel;

    public SysCondoMainScreen() {
        // Configurações da janela
        setTitle("SysCondo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null); // Centraliza a janela
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Adiciona o header ao painel principal
        mainPanel.add(new Header(), BorderLayout.NORTH);

        // Barra lateral de navegação
        contentPanel = new ContentPanel(); // Inicializa o painel de conteúdo
        sideMenu = new SideMenu(contentPanel.getContentPanel()); // Passa o painel de conteúdo
        mainPanel.add(sideMenu.getSideMenuPanel(), BorderLayout.WEST);

        // Adiciona o painel de conteúdo
        mainPanel.add(contentPanel.getContentPanel(), BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(mainPanel);

        // Exibe a janela
        setVisible(true);
    }

    // Método para acessar o painel de conteúdo (usado pela SideMenu para modificar o conteúdo)
    public JPanel getContentPanel() { // Retorna o painel em vez de ContentPanel
        return contentPanel.getContentPanel();
    }

    public static void main(String[] args) {
        // Inicia a tela principal
        SwingUtilities.invokeLater(SysCondoMainScreen::new);
    }
}
