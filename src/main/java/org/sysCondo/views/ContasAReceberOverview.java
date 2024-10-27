package org.sysCondo.views;

import javax.swing.*;
import java.awt.*;

public class ContasAReceberOverview extends JPanel {
    private JTable table; // Tabela para visualizar contas

    public ContasAReceberOverview() {
        setLayout(new BorderLayout());

        // Adicionar um título
        JLabel titleLabel = new JLabel("Overview de Contas a Receber", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        add(titleLabel, BorderLayout.NORTH);

        // Adicionar a tabela para visualizar contas
        table = new JTable(); // Configure a tabela com dados reais
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

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
