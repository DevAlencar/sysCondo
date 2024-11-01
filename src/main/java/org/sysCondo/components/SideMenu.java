package org.sysCondo.components;

import org.sysCondo.types.MenuItem;
import org.sysCondo.views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenu {

    private JPanel sideMenuPanel;
    private AdditionalOptionsPanel accountsReceivableOptionsPanel; // Painel de opções para contas a receber
    private AdditionalOptionsPanel accountsPayableOptionsPanel; // Painel de opções para contas a pagar
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
        AdditionalOptionsPanel residentsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        MenuItem[] residentsItems = {
                new MenuItem("Cadastro", new NewResident()),
        };
        residentsOptionsPanel.createAdditionalOptionsPanel(residentsItems);

        AdditionalOptionsPanel accountsReceivableOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        MenuItem[] receivableItems = {
                new MenuItem("Adicionar conta", new AccReceivableAdd()),
                new MenuItem("Overview", new AccReceivableOverview())
        };
        accountsReceivableOptionsPanel.createAdditionalOptionsPanel(receivableItems);

        AdditionalOptionsPanel accountsPayableOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        MenuItem[] payableItems = {
                new MenuItem("Adicionar conta", new AccPayableAdd()),
                new MenuItem("Overview", new AccPayableOverview())
        };
        accountsPayableOptionsPanel.createAdditionalOptionsPanel(payableItems);

        AdditionalOptionsPanel commsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        MenuItem[] commsItems = {
                new MenuItem("Novo Comunicado", new NewStatement()),
                new MenuItem("Comunicados", new Statements(parentFrame))
        };
        commsOptionsPanel.createAdditionalOptionsPanel(commsItems);

        AdditionalOptionsPanel reservationsOptionsPanel = new AdditionalOptionsPanel(contentPanel);
        MenuItem[] reserveItems = {
                new MenuItem("Solicitar reserva", new CommonAreasForm())
        };
        reservationsOptionsPanel.createAdditionalOptionsPanel(reserveItems);

        JPanel sideMenu = new JPanel();
        sideMenu.setLayout(new BoxLayout(sideMenu, BoxLayout.Y_AXIS));
        sideMenu.setPreferredSize(new Dimension(250, contentPanel.getHeight()));
        sideMenu.setBackground(new Color(235, 235, 235));
        JButton residents = createSideMenuButton("Gestão de moradores", "src/main/java/org/sysCondo/assets/people.png");
        residents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        residentsOptionsPanel.toggleVisibility();
                    }
                });
        sideMenu.add(residents);
        sideMenu.add(residentsOptionsPanel.getPanel());

        sideMenu.add(createSideMenuButton("Gestão de residências", "src/main/java/org/sysCondo/assets/house.png"));
        JButton receivableButton = createSideMenuButton("Contas a receber", "src/main/java/org/sysCondo/assets/receive.png");
        receivableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountsReceivableOptionsPanel.toggleVisibility();
            }
        });
        sideMenu.add(receivableButton);
        sideMenu.add(accountsReceivableOptionsPanel.getPanel());

        JButton payableButton = createSideMenuButton("Contas a pagar", "src/main/java/org/sysCondo/assets/pay.png");
        payableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accountsPayableOptionsPanel.toggleVisibility();
            }
        });
        sideMenu.add(payableButton);
        sideMenu.add(accountsPayableOptionsPanel.getPanel());

        JButton reservationButton = createSideMenuButton("Reservas", "src/main/java/org/sysCondo/assets/calendar.png");
        reservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {reservationsOptionsPanel.toggleVisibility();}
        });
        sideMenu.add(reservationButton);
        sideMenu.add(reservationsOptionsPanel.getPanel());

        sideMenu.add(createSideMenuButton("Manutenções", "src/main/java/org/sysCondo/assets/maintenance.png"));

        JButton commsButton = createSideMenuButton("Comunicação", "src/main/java/org/sysCondo/assets/chat.png");
        commsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commsOptionsPanel.toggleVisibility();
            }
        });
        sideMenu.add(commsButton);
        sideMenu.add(commsOptionsPanel.getPanel());

        return sideMenu;
    }

    private JButton createSideMenuButton(String text, String iconPath) {
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