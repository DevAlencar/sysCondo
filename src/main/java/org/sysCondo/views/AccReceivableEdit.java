package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccReceivableEdit extends JPanel {
    private RoundJTextField accountNameField;
    private RoundJTextField dueDateField;
    private RoundJTextField accountTypeField;
    private RoundJTextField amountField;
    private JComboBox<String> statusComboBox;

    public AccReceivableEdit(String accountName, String dueDate, String accountType, String amount, String status) {
        // Layout com espaços mais definidos
        setLayout(new GridLayout(6, 2, 10, 10));

        // Definir rótulos e campos para edição
        JLabel accountNameLabel = new JLabel("Nome da Conta:");
        accountNameField = new RoundJTextField(20, 10);
        accountNameField.setText(accountName);

        JLabel dueDateLabel = new JLabel("Data de Vencimento:");
        dueDateField = new RoundJTextField(20, 10);
        dueDateField.setText(dueDate);

        JLabel accountTypeLabel = new JLabel("Tipo da Conta:");
        accountTypeField = new RoundJTextField(20, 10);
        accountTypeField.setText(accountType);

        JLabel amountLabel = new JLabel("Valor da Conta:");
        amountField = new RoundJTextField(20, 10);
        amountField.setText(amount);

        JLabel statusLabel = new JLabel("Status:");
        String[] statuses = {"Pago", "A receber", "Atrasado"};
        statusComboBox = new JComboBox<>(statuses);
        statusComboBox.setSelectedItem(status);

        // Personalizando o estilo
        setBackground(new Color(242, 242, 242)); // Fundo cinza claro
        accountNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        dueDateLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        accountTypeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Adicionar componentes ao painel
        add(accountNameLabel);
        add(accountNameField);

        add(dueDateLabel);
        add(dueDateField);

        add(accountTypeLabel);
        add(accountTypeField);

        add(amountLabel);
        add(amountField);

        add(statusLabel);
        add(statusComboBox);

        // Botão para salvar as alterações
        RoundJButton addButton = new RoundJButton("Adicionar Conta");
        addButton.setBackground(new Color(28, 170, 164));  // Cor de fundo do botão
        addButton.setForeground(Color.WHITE);  // Cor do texto
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });

        // Botão para cancelar a edição
        RoundJButton cancelButton = new RoundJButton("Cancelar");
        cancelButton.setBackground(new Color(255, 80, 80));  // Cor de fundo do botão
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelEdit();
            }
        });

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(242, 242, 242)); // Cor de fundo do painel
        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);

        // Adicionar painel de botões ao layout principal
        add(new JLabel());
        add(buttonPanel);
    }

    // Método para adicionar a conta
    private void addAccount() {
        // Aqui você pode implementar a lógica para adicionar a conta
        String accountName = accountNameField.getText();
        String dueDate = dueDateField.getText();
        String accountType = accountTypeField.getText();
        String amount = amountField.getText();
        String status = (String) statusComboBox.getSelectedItem();

        // Lógica para salvar a conta

        JOptionPane.showMessageDialog(this, "Conta adicionada com sucesso!");
    }

    // Método para cancelar a edição
    private void cancelEdit() {
        // Lógica para cancelar a edição
        JOptionPane.showMessageDialog(this, "Edição cancelada!");
    }
}
