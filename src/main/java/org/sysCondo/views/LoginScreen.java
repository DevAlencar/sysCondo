package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJPasswordField;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen {

    public static void main(String[] args) {
        // Cria o JFrame (janela principal)
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLocationRelativeTo(null);

        // Cria o painel principal com GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        // Criando um painel de títulos
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o título
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("SysCondo", JLabel.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 45));

        JLabel subtitleLabel = new JLabel("Insira suas credenciais", JLabel.CENTER);
        subtitleLabel.setFont(new Font("Roboto Light", Font.PLAIN, 12));

        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);

        // Adiciona o painel de títulos ao painel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(titlePanel, gbc);

        // Cria o painel do formulário
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o formulário

        // Componentes do formulário
        JLabel cpfLabel = new JLabel("CPF:");
        RoundJTextField cpfField = new RoundJTextField(15, 15);
        JLabel senhaLabel = new JLabel("Senha:");
        RoundJPasswordField senhaField = new RoundJPasswordField(15, 15);

        formPanel.add(cpfLabel);
        formPanel.add(cpfField);
        formPanel.add(senhaLabel);
        formPanel.add(senhaField);

        // Adiciona o painel do formulário ao painel principal
        gbc.gridy = 1;
        mainPanel.add(formPanel, gbc);

        // Cria e adiciona o botão
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
        RoundJButton loginButton = new RoundJButton("Conecte-se");
        buttonPanel.add(loginButton);

        // Adiciona o painel do botão ao painel principal
        gbc.gridy = 2;
        mainPanel.add(buttonPanel, gbc);

        // Cria e adiciona o painel de rodapé
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel footerLabel = new JLabel("© 2022 SysCondo. All rights reserved.");
        footerLabel.setFont(new Font("Roboto Light", Font.PLAIN, 10));
        footerPanel.add(footerLabel);
        footerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o rodapé

        // Adiciona o painel de rodapé ao painel principal
        gbc.gridy = 3;
        mainPanel.add(footerPanel, gbc);

        // Exibe a janela
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Inicia a janela em tela cheia
    }
}