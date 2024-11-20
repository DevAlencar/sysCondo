package org.sysCondo.views;

import org.sysCondo.components.Header;
import org.sysCondo.components.ContentPanel;
import org.sysCondo.components.SideMenu;

import javax.swing.*;
import java.awt.*;

public class SysCondoMainScreen extends JPanel {

    private ContentPanel contentPanel; // Painel de conteúdo
    private SideMenu sideMenu; // Menu lateral
    private JFrame parentFrame;
    //private CardLayout cardLayout;
    //private AccReceivableOverview overviewPanel;
    //private AccReceivableAdd addPanel;

    public SysCondoMainScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Adiciona o header ao painel principal
        mainPanel.add(new Header(), BorderLayout.NORTH);

        // Barra lateral de navegação
        contentPanel = new ContentPanel(); // Inicializa o painel de conteúdo
        sideMenu = new SideMenu(contentPanel.getContentPanel(), this.parentFrame); // Passa o painel de conteúdo
        mainPanel.add(sideMenu.getSideMenuPanel(), BorderLayout.WEST);

        // Adiciona o painel de conteúdo
        mainPanel.add(contentPanel.getContentPanel(), BorderLayout.CENTER);

        // Adiciona o painel principal à janela
        add(mainPanel, BorderLayout.CENTER);

        // Exibe a janela
        setVisible(true);
    }

    // Método para acessar o painel de conteúdo (usado pela SideMenu para modificar o conteúdo)
    public JPanel getContentPanel() { // Retorna o painel em vez de ContentPanel
        return contentPanel.getContentPanel();
    }
}
