package org.sysCondo.views;

import javax.swing.*;
import java.awt.*;

public class ContasAReceber extends JPanel {
    public ContasAReceber() {
        setLayout(new BorderLayout());

        // Adicionar um título
        JLabel titleLabel = new JLabel("Contas a Receber", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        add(titleLabel, BorderLayout.NORTH);

        // Adicionar uma tabela ou painel para visualizar contas
        JTable table = new JTable(); // Configure a tabela com dados reais
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Método para mostrar a tela de adicionar nova conta
    public void showAddAccountScreen() {
        JPanel addAccountPanel = new JPanel();
        addAccountPanel.setLayout(new GridLayout(3, 2));

        addAccountPanel.add(new JLabel("Nome da Conta:"));
        JTextField accountNameField = new JTextField();
        addAccountPanel.add(accountNameField);

        addAccountPanel.add(new JLabel("Valor da Conta:"));
        JTextField accountValueField = new JTextField();
        addAccountPanel.add(accountValueField);

        int result = JOptionPane.showConfirmDialog(this, addAccountPanel, "Adicionar Nova Conta", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String accountName = accountNameField.getText();
            String accountValue = accountValueField.getText();
            // Aqui você pode adicionar a lógica para salvar a conta
            System.out.println("Conta adicionada: " + accountName + " - " + accountValue);
        }
    }
}
