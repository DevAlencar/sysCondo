package org.sysCondo.views;

import javax.swing.*;
import java.awt.*;

public class ContasAReceberAdd extends JPanel {
    private JTable table; // Tabela para visualizar contas

    public ContasAReceberAdd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Adicionar um título
        JLabel titleLabel = new JLabel("Adicionar Nova Conta", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        add(titleLabel, BorderLayout.NORTH);

        // Adicionar painel para cadastro de nova conta
        JPanel addAccountPanel = new JPanel();
        addAccountPanel.setBackground(Color.WHITE);
        addAccountPanel.setLayout(new GridBagLayout()); // Usar GridBagLayout para melhor controle de layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre os componentes

        gbc.gridx = 0; // Coluna
        gbc.gridy = 0; // Linha
        addAccountPanel.add(new JLabel("Nome da Conta:"), gbc);

        gbc.gridx = 1; // Coluna
        JTextField accountNameField = new JTextField(15);
        addAccountPanel.add(accountNameField, gbc);

        gbc.gridx = 0; // Coluna
        gbc.gridy = 1; // Linha
        addAccountPanel.add(new JLabel("Valor da Conta:"), gbc);

        gbc.gridx = 1; // Coluna
        JTextField accountValueField = new JTextField(15);
        addAccountPanel.add(accountValueField, gbc);

        gbc.gridx = 0; // Coluna
        gbc.gridy = 2; // Linha
        gbc.gridwidth = 2; // O botão ocupará duas colunas
        JButton addButton = new JButton("Adicionar Conta");
        addButton.addActionListener(e -> {
            String accountName = accountNameField.getText();
            String accountValue = accountValueField.getText();
            // Aqui você pode adicionar a lógica para salvar a conta
            System.out.println("Conta adicionada: " + accountName + " - " + accountValue);
            // Limpa os campos após adicionar
            accountNameField.setText("");
            accountValueField.setText("");
            // Atualiza a tabela após adicionar uma nova conta
            updateTable();
        });
        addAccountPanel.add(addButton, gbc);

        // Adiciona o painel de adicionar conta ao painel principal
        add(addAccountPanel, BorderLayout.CENTER);

        // Adicionar a tabela para visualizar contas
        table = new JTable(); // Configure a tabela com dados reais
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.SOUTH);

        updateTable(); // Chama para preencher a tabela inicialmente
    }

    // Método para atualizar a tabela com dados reais (simulação)
    private void updateTable() {
        // Aqui você deve implementar a lógica para atualizar a tabela com dados reais
        // Exemplo de atualização fictícia:
        String[] columnNames = {"Nome da Conta", "Valor da Conta", "Status"};
        Object[][] data = {
                {"Conta 1", "R$ 100,00", "Pendente"},
                {"Conta 2", "R$ 200,00", "Pago"},
                {"Conta 3", "R$ 150,00", "Pendente"}
        };
        table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    public JTable getTable() {
        return table; // Para acesso à tabela se necessário
    }
}
