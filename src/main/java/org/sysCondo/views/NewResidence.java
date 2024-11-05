package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewResidence extends JPanel {
    public NewResidence() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer, BorderLayout.CENTER);

        JLabel contentTitle = new JLabel("Cadastro de residência", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentContainer.add(contentTitle);

        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400)); // Define a largura e altura do formulário
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        gbc.gridy = 0;
        formContainer.add(getInputContainer("Número da propriedade"), gbc);
        gbc.gridy = 1;
        formContainer.add(getInputContainer("Tamanho da propriedade"), gbc);
        gbc.gridy = 2;
        formContainer.add(getInputContainer("Nome do proprietário"), gbc);
        gbc.gridy = 3;
        formContainer.add(getInputContainer("Telefone do proprietário"), gbc);

        // Centraliza o formContainer
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton saveBtn = new RoundJButton("Salvar"); // botão de salvar
        RoundJButton cancelBtn = new RoundJButton("Cancelar"); // botão de cancelar
        formButtonsContainer.add(cancelBtn);
        formButtonsContainer.add(saveBtn);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 4;
        formContainer.add(formButtonsContainer, gbc);

        // Exibe a janela
        setVisible(true);
    }

    private JPanel getInputContainer(String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);

        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.setBackground(Color.WHITE);
        RoundJTextField input = new RoundJTextField(1, 10);

        input.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.add(inputLabel, BorderLayout.NORTH);
        container.add(input, BorderLayout.CENTER);

        return container;
    }
}
