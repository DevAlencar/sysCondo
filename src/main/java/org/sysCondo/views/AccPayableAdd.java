package org.sysCondo.views;

import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class AccPayableAdd extends JPanel {

    public AccPayableAdd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Adicionar Nova Conta a Pagar", JLabel.CENTER);
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
        formContainer.add(getInputContainer("Nome do Fornecedor"), gbc);
        gbc.gridy = 1;
        formContainer.add(getInputContainer("Data de Vencimento (dd/mm/aaaa)"), gbc);
        gbc.gridy = 2;
        formContainer.add(getComboBoxContainer("Tipo de Despesa", new String[]{"Aluguel", "Serviços", "Outros"}), gbc);
        gbc.gridy = 3;
        formContainer.add(getComboBoxContainer("Status", new String[]{"Pago", "A pagar", "Atrasado"}), gbc);
        gbc.gridy = 4;
        formContainer.add(getInputContainer("Valor da Conta"), gbc);

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
            System.out.println("Despesa adicionada");
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

    // Método para criar um combobox com bordas arredondadas
    private JPanel getComboBoxContainer(String label, String[] options) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);

        comboBoxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.setBackground(Color.WHITE);
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
        container.add(comboBoxLabel, BorderLayout.NORTH);
        container.add(comboBox, BorderLayout.CENTER);

        return container;
    }
}
