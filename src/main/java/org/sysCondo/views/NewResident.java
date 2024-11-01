package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.UserController;
import org.sysCondo.model.user.UserRole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewResident extends JPanel {
    private RoundJTextField nameField; // Campo para Nome
    private RoundJTextField cpfField; // Campo para CPF
    private RoundJTextField emailField; // Campo para Email
    private RoundJTextField phoneField; // Campo para Telefone
    private JComboBox<String> unitComboBox; // ComboBox para Unidade Associada
    private JComboBox<String> situationComboBox; // ComboBox para Situação do residente

    public NewResident() {
        // Painel principal com BorderLayout
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        JLabel contentTitle = new JLabel("Cadastro de Moradores", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(contentTitle, BorderLayout.NORTH);

        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400)); // Define a largura e altura do formulário
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Adicionando campos ao formulário
        gbc.gridy = 0;
        nameField = addInputField(formContainer, gbc, "Nome do Morador");
        gbc.gridy++;
        cpfField = addInputField(formContainer, gbc, "CPF");
        gbc.gridy++;
        unitComboBox = addComboBoxField(formContainer, gbc, "Unidade Associada", new String[]{"Unidade 1", "Unidade 2", "Unidade 3"});
        gbc.gridy++;
        emailField = addInputField(formContainer, gbc, "Endereço de email");
        gbc.gridy++;
        phoneField = addInputField(formContainer, gbc, "Número de telefone");
        gbc.gridy++;
        situationComboBox = addComboBoxField(formContainer, gbc, "Situação do residente", new String[]{"Proprietário", "Inquilino"});

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
        gbc.gridy++;
        formContainer.add(formButtonsContainer, gbc);

        UserController userController = new UserController();

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lê os dados dos campos
                String name = nameField.getText();
                String cpf = cpfField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String unit = (String) unitComboBox.getSelectedItem();
                String situation = (String) situationComboBox.getSelectedItem();

                // Debug: imprime os valores lidos
                System.out.println("Name: '" + name + "'");
                System.out.println("CPF: '" + cpf + "'");
                System.out.println("Email: '" + email + "'");
                System.out.println("Phone: '" + phone + "'");
                System.out.println("Unit: '" + unit + "'");
                System.out.println("Situation: '" + situation + "'");

                // Verifica se os campos não estão vazios
                if (name.trim().isEmpty() || cpf.trim().isEmpty() || email.trim().isEmpty() || phone.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(NewResident.this, "Por favor, preencha todos os campos obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return; // Não prossegue se algum campo estiver vazio
                }

                // Cria o usuário
                userController.createUser(name, phone, cpf, UserRole.USER);
            }
        });

        // Exibe a janela
        setVisible(true);
    }

    private RoundJTextField addInputField(JPanel container, GridBagConstraints gbc, String label) {
        JPanel inputContainer = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);
        RoundJTextField inputField = new RoundJTextField(1, 10);
        inputContainer.add(inputLabel, BorderLayout.NORTH);
        inputContainer.add(inputField, BorderLayout.CENTER);
        container.add(inputContainer, gbc);
        return inputField; // Retorna o campo para referência posterior
    }

    private JComboBox<String> addComboBoxField(JPanel container, GridBagConstraints gbc, String label, String[] options) {
        JPanel comboBoxContainer = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(Color.BLACK, 1));
                return button;
            }
        });
        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));
        comboBoxContainer.add(comboBoxLabel, BorderLayout.NORTH);
        comboBoxContainer.add(comboBox, BorderLayout.CENTER);
        container.add(comboBoxContainer, gbc);
        return comboBox; // Retorna o combo box para referência posterior
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewResident::new);
    }
}
