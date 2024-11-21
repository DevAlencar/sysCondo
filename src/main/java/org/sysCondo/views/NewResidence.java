package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.UnitResidentialController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewResidence extends JPanel {
    // Variáveis de instância para armazenar referências dos campos de entrada
    private RoundJTextField propertyNumberInput;
    private RoundJTextField propertySizeInput;
    private RoundJTextField ownerNameInput;
    private RoundJTextField ownerPhoneInput;

    public NewResidence() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(Color.WHITE);
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
        gbc.anchor = GridBagConstraints.NORTH;

        // Cria e adiciona os campos de entrada com variáveis de instância
        gbc.gridy = 0;
        propertyNumberInput = createAndAddInputField(formContainer, gbc, "Número da propriedade");
        gbc.gridy = 1;
        propertySizeInput = createAndAddInputField(formContainer, gbc, "Tamanho da propriedade");
        gbc.gridy = 2;
        ownerNameInput = createAndAddInputField(formContainer, gbc, "Nome do proprietário");
        gbc.gridy = 3;
        ownerPhoneInput = createAndAddInputField(formContainer, gbc, "Telefone do proprietário");

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
        gbc.gridy = 5;
        gbc.weighty = 1.0;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

        saveBtn.addActionListener(e -> {
            String propertyNumber = propertyNumberInput.getText();
            String propertySize = propertySizeInput.getText();
            String ownerName = ownerNameInput.getText();
            String ownerPhone = ownerPhoneInput.getText();
            
            UnitResidentialController unitResidentialController = new UnitResidentialController();
            unitResidentialController.createUnitResidential(propertyNumber, propertySize, ownerName, ownerPhone);
        });

        // Exibe a janela
        setVisible(true);
    }

    // Método auxiliar para criar e adicionar o campo de entrada
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
