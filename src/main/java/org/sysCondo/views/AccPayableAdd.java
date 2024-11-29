package org.sysCondo.views;

import org.sysCondo.components.*;
import org.sysCondo.controller.AccountController;
import org.sysCondo.controller.TaxController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AccPayableAdd extends JPanel {
    // Variáveis de instância para armazenar referências dos campos de entrada
    private RoundJTextField accountValueInput;
    private RoundJTextField accountFinishDateInput;
    private JComboBox<String> accountStatusInput;
    private JComboBox<String> accountTypeInput;
    private RoundJTextField accountSupplierInput;

    public AccPayableAdd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Adicionar nova conta", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        accountSupplierInput = createAndAddInputField(formContainer, gbc, "Nome do Fornecedor");
        gbc.gridy = 1;
        accountFinishDateInput = createAndAddInputField(formContainer, gbc, "Data de Vencimento (dd/mm/aaaa)");
        gbc.gridy = 2;
        formContainer.add(getComboBoxContainer("Status", new String[]{"Pago", "A pagar", "Atrasado"}), gbc);
        gbc.gridy = 3;
        accountValueInput = createAndAddInputField(formContainer, gbc, "Valor da Conta");

        // Centralizar o formulário
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        // Adicionar botões
        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton addButton = new RoundJButton("Adicionar Despesa");
        RoundJButton cancelButton = new RoundJButton("Cancelar");
        formButtonsContainer.add(cancelButton);
        formButtonsContainer.add(addButton);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 5;
        formContainer.add(formButtonsContainer, gbc);
        gbc.gridy = 6;
        gbc.weighty = 1.0;
        JPanel paddingPanel = new JPanel();
        paddingPanel.setBackground(Color.WHITE);
        formContainer.add(paddingPanel, gbc);

        // Ação do botão de adicionar despesa
        addButton.addActionListener(e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String accountSupplier = accountSupplierInput.getText();
            LocalDate accountFinishDate = LocalDate.parse(accountFinishDateInput.getText(), formatter);
            String accountStatus = Objects.requireNonNull(accountStatusInput.getSelectedItem()).toString();
            float accountValue = Float.parseFloat(accountValueInput.getText());

            AccountController accountController = new AccountController();
            accountController.createAccount(accountSupplier, accountValue, accountStatus, accountFinishDate);
            // Limpa os campos após adicionar
        });

        setVisible(true);
    }

    // Método para criar um campo de entrada com bordas arredondadas
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

    // Método para criar um combobox com bordas arredondadas
    private JPanel getComboBoxContainer(String label, String[] options) {
        JPanel container = new JPanel(new BorderLayout());

        // Criar o rótulo
        JLabel comboBoxLabel = new JLabel(label);
        comboBoxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.setBackground(Color.WHITE);

        // Criar o JComboBox e armazenar referência
        accountStatusInput = new JComboBox<>(options);
        accountStatusInput.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(Color.BLACK, 1));
                return button;
            }
        });
        accountStatusInput.setBackground(Color.WHITE);
        accountStatusInput.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));
        accountStatusInput.setFont(new Font("Roboto", Font.PLAIN, 14));

        // Adicionar rótulo e combo box ao painel
        container.add(comboBoxLabel, BorderLayout.NORTH);
        container.add(accountStatusInput, BorderLayout.CENTER);

        return container; // Retorna o painel com rótulo e combo box
    }
}
