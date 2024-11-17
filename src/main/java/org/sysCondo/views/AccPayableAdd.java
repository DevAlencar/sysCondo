package org.sysCondo.views;

import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class AccPayableAdd extends JPanel {
    private Runnable onCloseListener; // Campo para armazenar o listener
    private RoundJTextField nomeFornecedorField;
    private RoundJTextField dataVencimentoField;
    private JComboBox<String> statusComboBox;
    private RoundJTextField valorContaField;

    public AccPayableAdd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Adicionar Nova Taxa", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
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

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        nomeFornecedorField = createInputField("Nome do Fornecedor", formContainer, gbc);
        gbc.gridy = 1;
        dataVencimentoField = createInputField("Data de Vencimento (dd/mm/aaaa)", formContainer, gbc);
        gbc.gridy = 2;
        statusComboBox = createComboBox("Status", new String[]{"Pago", "A pagar", "Atrasado"}, formContainer, gbc);
        gbc.gridy = 3;
        valorContaField = createInputField("Valor da Conta", formContainer, gbc);

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

        // Ação do botão de adicionar despesa
        addButton.addActionListener(e -> {
            String nome = nomeFornecedorField.getText();
            String dataVencimento = dataVencimentoField.getText();
            String status = (String) statusComboBox.getSelectedItem();
            String valorConta = valorContaField.getText();

            // Validar os campos antes de adicionar
            if (nome.isEmpty() || dataVencimento.isEmpty() || valorConta.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Por favor, preencha todos os campos obrigatórios.",
                        "Campos Obrigatórios",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Mostrar mensagem de sucesso
            JOptionPane.showMessageDialog(
                    this,
                    "Despesa adicionada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Limpar os campos
            nomeFornecedorField.setText("");
            dataVencimentoField.setText("");
            valorContaField.setText("");
            statusComboBox.setSelectedIndex(0); // Reseta o ComboBox para o primeiro item

            System.out.println("Despesa adicionada");
            System.out.println("Nome: " + nome);
            System.out.println("Data de Vencimento: " + dataVencimento);
            System.out.println("Status: " + status);
            System.out.println("Valor da Conta: " + valorConta);

            // Chamar o listener para fechar a janela, se configurado
            if (onCloseListener != null) {
                onCloseListener.run();
            }
        });
        setVisible(true);
    }

    // Método para configurar o listener de fechamento
    public void setOnCloseListener(Runnable onCloseListener) {
        this.onCloseListener = onCloseListener;
    }

    private RoundJTextField createInputField(String label, JPanel container, GridBagConstraints gbc) {
        JPanel inputContainer = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);
        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputContainer.setBackground(Color.WHITE);

        RoundJTextField inputField = new RoundJTextField(1, 10);
        inputField.setFont(new Font("Roboto", Font.PLAIN, 14));

        inputContainer.add(inputLabel, BorderLayout.NORTH);
        inputContainer.add(inputField, BorderLayout.CENTER);

        container.add(inputContainer, gbc);
        return inputField;
    }

    private JComboBox<String> createComboBox(String label, String[] options, JPanel container, GridBagConstraints gbc) {
        JPanel comboBoxContainer = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);
        comboBoxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        comboBoxContainer.setBackground(Color.WHITE);

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
        comboBox.setFont(new Font("Roboto", Font.PLAIN, 14));

        comboBoxContainer.add(comboBoxLabel, BorderLayout.NORTH);
        comboBoxContainer.add(comboBox, BorderLayout.CENTER);

        container.add(comboBoxContainer, gbc);
        return comboBox;
    }
}

