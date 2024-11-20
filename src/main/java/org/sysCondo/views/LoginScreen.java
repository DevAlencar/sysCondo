package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJPasswordField;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.components.Session;
import org.sysCondo.controller.AuthenticationController;
import org.sysCondo.model.user.User;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JPanel {
    private RoundJTextField userField;
    private RoundJTextField passwordField;
    public LoginScreen(JFrame parentFrame) {
        // Cria o painel principal com GridBagLayout
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints mainPanelGbc = new GridBagConstraints();
        mainPanelGbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanelGbc.anchor = GridBagConstraints.CENTER;
        mainPanelGbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

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
        mainPanelGbc.gridx = 0;
        mainPanel.add(titlePanel, mainPanelGbc);

        // Cria o painel do formulário
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(200, 200)); // Define a largura e altura do formulário
        GridBagConstraints formContainerGbc = new GridBagConstraints();
        formContainerGbc.fill = GridBagConstraints.HORIZONTAL;
        formContainerGbc.insets = new Insets(5, 0, 5, 0);
        formContainerGbc.gridx = 0;
        formContainerGbc.weightx = 1.0;

        // Componentes do formulário
        formContainerGbc.gridy = 0;
        userField = createAndAddInputField(formContainer, formContainerGbc, "CPF");
        formContainerGbc.gridy = 1;
        passwordField = createAndAddInputField(formContainer, formContainerGbc, "Senha");

        // Adiciona o painel do formulário ao painel principal
        mainPanelGbc.gridy = 1;
        mainPanel.add(formContainer, mainPanelGbc);

        // Cria e adiciona o botão
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
        RoundJButton loginButton = new RoundJButton("Conecte-se");

        loginButton.addActionListener(e -> { // aqui vai ser a parte da validação e envio dos dados @arthur
            AuthenticationController authenticationController = new AuthenticationController();
            User user = authenticationController.login(userField.getText(), passwordField.getText());
            if(user == null){
                //adicionar aviso de senha ou documento errado
            }else{
                Session.setCurrentUser(user);
                SysCondoMainScreen mainScreen = new SysCondoMainScreen(parentFrame);
                parentFrame.setContentPane(mainScreen);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        buttonPanel.add(loginButton);

        // Adiciona o painel do botão ao painel principal
        mainPanelGbc.gridy = 2;
        mainPanel.add(buttonPanel, mainPanelGbc);

        // Cria e adiciona o painel de rodapé
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.WHITE);
        JLabel footerLabel = new JLabel("© 2024 SysCondo. All rights reserved.");
        footerLabel.setFont(new Font("Roboto Light", Font.PLAIN, 10));
        footerPanel.add(footerLabel);
        footerPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o rodapé

        // Adiciona o painel de rodapé ao painel principal
        mainPanelGbc.gridy = 3;
        mainPanel.add(footerPanel, mainPanelGbc);

        // Exibe a janela
        add(mainPanel);
        setVisible(true);
    }
    private RoundJTextField createAndAddInputField(JPanel formContainer, GridBagConstraints gbc, String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);
        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.setBackground(Color.WHITE);
        RoundJTextField input = new RoundJTextField(1, 10);
        input.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.add(inputLabel, BorderLayout.NORTH);
        container.add(input, BorderLayout.CENTER);
        formContainer.add(container, gbc);
        return input; // Retorna o campo de entrada para que possamos armazenar a referência
    }
}