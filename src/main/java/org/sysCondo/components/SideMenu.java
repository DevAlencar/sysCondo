package org.sysCondo.components;

import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.types.MenuItem;
import org.sysCondo.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SideMenu {

    private JPanel sideMenuPanel;
    private JPanel contentPanel;
    public JFrame parentFrame;

    public SideMenu(JPanel contentPanel, JFrame parentFrame) {
        this.contentPanel = contentPanel;
        sideMenuPanel = createSideMenu();
        this.parentFrame = parentFrame;
    }

    public JPanel getSideMenuPanel() {
        return sideMenuPanel;
    }

    private JPanel createSideMenu() {
        NewResident newResident = new NewResident();
        NewResidence newResidence = new NewResidence();
        AccReceivableAdd accReceivableAdd = new AccReceivableAdd();
        AccReceivableOverview accRecievableOverview = new AccReceivableOverview();
        AccPayableAdd accPayableAdd = new AccPayableAdd();
        AccPayableOverview accPayableOverview = new AccPayableOverview();
        NewStatement newStatement = new NewStatement();
        Statements statements = new Statements(parentFrame);
        Messages messages = new Messages(parentFrame);
        CommonAreasMaintenence commonAreasMaintenence = new CommonAreasMaintenence();
        CommonAreasMaintenenceRequests commonAreasMaintenenceRequests = new CommonAreasMaintenenceRequests(parentFrame);
        CommonAreasMaintenenceOverview commonAreasMaintenenceOverview = new CommonAreasMaintenenceOverview();
        CommonAreasFacilities commonAreasFacilities = new CommonAreasFacilities();
        ReservationOverview reservationOverview = new ReservationOverview();
        User currentUser = Session.getCurrentUser();
        UserRole role = currentUser.getUserRole();

        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(250, contentPanel.getHeight()));
        sideMenu.setBackground(new Color(235, 235, 235));

        if (role.toString().equals("ADMIN")) {
            AdditionalOptionsPanel residentsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] residentsItems = {
                    new MenuItem("Cadastrar morador", newResident),
            };
            residentsOptionsPanel.createAdditionalOptionsPanel(residentsItems);

            AdditionalOptionsPanel residenceOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] residenceItems = {
                    new MenuItem("Cadastrar residência", newResidence)
            };
            residenceOptionsPanel.createAdditionalOptionsPanel(residenceItems);

            AdditionalOptionsPanel accountsReceivableOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] receivableItems = {
                    new MenuItem("Adicionar taxa", accReceivableAdd),
                    new MenuItem("Visão geral", accRecievableOverview)
            };
            accountsReceivableOptionsPanel.createAdditionalOptionsPanel(receivableItems);

            AdditionalOptionsPanel accountsPayableOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] payableItems = {
                    new MenuItem("Adicionar conta", accPayableAdd),
                    new MenuItem("Visão geral", accPayableOverview)
            };
            accountsPayableOptionsPanel.createAdditionalOptionsPanel(payableItems);

            AdditionalOptionsPanel commsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] commsItems = {
                    new MenuItem("Novo Comunicado", newStatement),
                    new MenuItem("Comunicados", statements),
                    new MenuItem("Mensagens", messages)
            };
            commsOptionsPanel.createAdditionalOptionsPanel(commsItems);

            AdditionalOptionsPanel commonAreasOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] commonAreasItems = {
                    new MenuItem("Solicitar manutenção", commonAreasMaintenence),
                    new MenuItem("Gestão de manutenções", commonAreasMaintenenceOverview),
                    new MenuItem("Áreas comuns", commonAreasFacilities),
                    new MenuItem("Gerenciar reservas", reservationOverview) // Certifique-se de que essa classe existe
            };
            commonAreasOptionsPanel.createAdditionalOptionsPanel(commonAreasItems);

            JButton residents = createSideMenuButton("Gestão de moradores", "src/main/java/org/sysCondo/assets/people.png", residentsOptionsPanel);
            sideMenu.add(residents);
            sideMenu.add(residentsOptionsPanel.getPanel());

            JButton residences = createSideMenuButton("Gestão de residências", "src/main/java/org/sysCondo/assets/house.png", residenceOptionsPanel);
            sideMenu.add(residences);
            sideMenu.add(residenceOptionsPanel.getPanel());

            JButton receivableButton = createSideMenuButton("Contas a receber", "src/main/java/org/sysCondo/assets/receive.png", accountsReceivableOptionsPanel);
            sideMenu.add(receivableButton);
            sideMenu.add(accountsReceivableOptionsPanel.getPanel());

            JButton payableButton = createSideMenuButton("Contas a pagar", "src/main/java/org/sysCondo/assets/pay.png", accountsPayableOptionsPanel);
            sideMenu.add(payableButton);
            sideMenu.add(accountsPayableOptionsPanel.getPanel());

            JButton reservationButton = createSideMenuButton("Áreas comuns", "src/main/java/org/sysCondo/assets/commonArea.png", commonAreasOptionsPanel);
            sideMenu.add(reservationButton);
            sideMenu.add(commonAreasOptionsPanel.getPanel());

            JButton commsButton = createSideMenuButton("Comunicação", "src/main/java/org/sysCondo/assets/chat.png", commsOptionsPanel);
            sideMenu.add(commsButton);
            sideMenu.add(commsOptionsPanel.getPanel());

            JButton reportsButton = createSideMenuButton("Gerar relatórios", "src/main/java/org/sysCondo/assets/reports.png", null);
            reportsButton.addActionListener(e -> {
                // Criando o Reports como um JDialog
                Reports reports = new Reports(parentFrame); // Certifique-se de que a classe Reports aceita um JFrame no construtor

                // Criando o JDialog para exibir os relatórios
                JDialog reportsDialog = new JDialog(parentFrame, "Relatórios", true);
                reportsDialog.setLayout(new BorderLayout());
                reportsDialog.add(reports, BorderLayout.CENTER);

                // Definindo o tamanho e outras propriedades do JDialog
                reportsDialog.setSize(800, 600);  // Ajuste o tamanho conforme necessário
                reportsDialog.setLocationRelativeTo(parentFrame); // Centraliza o pop-up
                reportsDialog.setVisible(true); // Torna o JDialog visível
            });
            sideMenu.add(reportsButton);
        } else {
            AdditionalOptionsPanel accountsReceivableOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] receivableItems = {
                    new MenuItem("Visão geral", accRecievableOverview)
            };
            accountsReceivableOptionsPanel.createAdditionalOptionsPanel(receivableItems);

            AdditionalOptionsPanel commsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] commsItems = {
                    new MenuItem("Comunicados", statements),
                    new MenuItem("Mensagens", messages)
            };
            commsOptionsPanel.createAdditionalOptionsPanel(commsItems);

            AdditionalOptionsPanel commonAreasOptionsPanel = new AdditionalOptionsPanel(contentPanel);
            MenuItem[] commonAreasItems = {
                    new MenuItem("Solicitar manutenção", commonAreasMaintenence),
                    new MenuItem("Solicitações realizadas", commonAreasMaintenenceRequests),
                    new MenuItem("Áreas comuns", commonAreasFacilities),
            };
            commonAreasOptionsPanel.createAdditionalOptionsPanel(commonAreasItems);

            JButton receivableButton = createSideMenuButton("Contas a receber", "src/main/java/org/sysCondo/assets/receive.png", accountsReceivableOptionsPanel);
            sideMenu.add(receivableButton);
            sideMenu.add(accountsReceivableOptionsPanel.getPanel());

            JButton reservationButton = createSideMenuButton("Áreas comuns", "src/main/java/org/sysCondo/assets/commonArea.png", commonAreasOptionsPanel);
            sideMenu.add(reservationButton);
            sideMenu.add(commonAreasOptionsPanel.getPanel());

            JButton commsButton = createSideMenuButton("Comunicação", "src/main/java/org/sysCondo/assets/chat.png", commsOptionsPanel);
            sideMenu.add(commsButton);
            sideMenu.add(commsOptionsPanel.getPanel());
        }

        return sideMenu;
    }


    private JButton createSideMenuButton(String text, String iconPath, AdditionalOptionsPanel optionsPanel) {
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

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                optionsPanel.toggleVisibility();
            }
            // Adiciona efeitos de mouse para melhorar a experiência do usuário
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());
            }
        });

        return button;
    }
}