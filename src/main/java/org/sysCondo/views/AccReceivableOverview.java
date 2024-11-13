package org.sysCondo.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;
import java.awt.Font;  // Importação correta para Swing

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;

public class AccReceivableOverview extends JPanel {
    private JTable table; // Tabela para visualizar contas
    private JTextField searchField; // Campo de busca para filtrar
    private DefaultTableModel tableModel; // Modelo da tabela para controle
    private TableRowSorter<DefaultTableModel> sorter; // Ordenador para a tabela

    public AccReceivableOverview() {
        setLayout(new BorderLayout());

        // Criar um painel para o título e o painel de controle
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout()); // Usar BorderLayout para organizar o título e os controles

        // Adicionar um título
        JLabel titleLabel = new JLabel("CONTAS A RECEBER", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Roboto Medium", java.awt.Font.PLAIN, 30)); // Usando java.awt.Font
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH); // Adiciona o título ao painel de cabeçalho

        // Painel de controle para busca e botões
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlsPanel.setBackground(Color.WHITE);

        searchField = new RoundJTextField(20, 10);
        searchField.setToolTipText("Buscar Conta...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                filterTable(query);
            }
        });

        JButton exportButton = new RoundJButton("Exportar para PDF");
        exportButton.addActionListener(e -> exportToPDF());

        JButton addButton = new RoundJButton("Adicionar Conta");
        addButton.addActionListener(e -> openAddAccountScreen()); // Adicionar ação para abrir a tela de adicionar conta

        // Botões Editar e Apagar
        JButton editButton = new RoundJButton("Editar Conta");
        editButton.addActionListener(e -> openEditAccountScreen()); // Ação para editar a conta selecionada

        JButton deleteButton = new RoundJButton("Apagar Conta");
        deleteButton.addActionListener(e -> deleteSelectedAccount()); // Ação para excluir a conta selecionada

        Font labelFont = new Font("Roboto Medium", Font.PLAIN, 14); // Para os rótulos

        // Adicionar componentes ao painel de controles
        JLabel searchLabel = new JLabel("Pesquisar por:");
        searchLabel.setFont(labelFont); // Alterar a fonte do rótulo
        controlsPanel.add(searchLabel);
        searchField.setFont(labelFont); // Alterar a fonte do campo de texto
        controlsPanel.add(searchField);
        controlsPanel.add(exportButton);
        controlsPanel.add(addButton); // Adiciona o botão de adicionar conta
        controlsPanel.add(editButton); // Adiciona o botão de editar
        controlsPanel.add(deleteButton); // Adiciona o botão de apagar

        headerPanel.add(controlsPanel, BorderLayout.SOUTH); // Adiciona o painel de controle ao painel de cabeçalho

        add(headerPanel, BorderLayout.NORTH); // Adiciona o painel de cabeçalho ao layout principal

        // Adicionar a tabela para visualizar contas
        table = new JTable();
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        updateTable(); // Chama para preencher a tabela inicialmente
    }

    private void customizeTable() {
        table.setRowHeight(30);
        table.setFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowGrid(true);

        table.getTableHeader().setFont(new java.awt.Font("Roboto Bold", java.awt.Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(255, 75, 0));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(245, 245, 245));
                } else {
                    c.setBackground(Color.WHITE);
                }
                if (isSelected) {
                    c.setBackground(new Color(0, 123, 255));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        tableModel = new DefaultTableModel();
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    // Método para filtrar a tabela
    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    // Método para atualizar a tabela com dados reais (simulação)
    private void updateTable() {
        // Dados fictícios para demonstrar o layout
        String[] columnNames = {"Nome da Conta", "Data de Vencimento", "Tipo da Conta", "Valor da Conta", "Status"};
        Object[][] data = {
                {"Aluguel de Outubro", "01/10/2024", "Aluguel", "R$ 1.500,00", "Pago"},
                {"Serviço de Jardinagem", "10/10/2024", "Serviços", "R$ 300,00", "A receber"},
                {"Conserto de Elevador", "15/10/2024", "Outros", "R$ 1.200,00", "Atrasado"}
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) { // Se for a coluna de status
                    return String.class; // Retorna como String para cores
                }
                return super.getColumnClass(columnIndex);
            }
        };
        table.setModel(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        sorter.toggleSortOrder(0);

        setStatusColors(); // Chama método para definir cores de status
    }

    // Método para definir cores de status
    private void setStatusColors() {
        table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value.toString();
                if (status.equals("Pago")) {
                    label.setBackground(new Color(145,255,145));
                } else if (status.equals("A receber")) {
                    label.setBackground(new Color(245,255,145));
                } else if (status.equals("Atrasado")) {
                    label.setBackground(new Color(255,145,145));
                } else {
                    label.setBackground(Color.WHITE);
                }
                label.setOpaque(true); // Necessário para mostrar a cor de fundo
                return label;
            }
        });
    }

    // Método para exportar a tabela para um PDF
    private void exportToPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ContasAReceber.pdf"));
            document.open();
            document.add(new Paragraph("Overview de Contas a Receber", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16))); // Usando importação completa
            document.add(new Paragraph(" ")); // Espaço
            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
            for (int i = 0; i < table.getColumnCount(); i++) {
                pdfTable.addCell(table.getColumnName(i));
            }
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    pdfTable.addCell(table.getValueAt(i, j).toString());
                }
            }
            document.add(pdfTable);
            document.close();
            JOptionPane.showMessageDialog(this, "Relatório exportado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao exportar PDF", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openEditAccountScreen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Pegar os dados da conta selecionada
            String accountName = (String) table.getValueAt(selectedRow, 0);
            String dueDate = (String) table.getValueAt(selectedRow, 1);
            String accountType = (String) table.getValueAt(selectedRow, 2);
            String amount = (String) table.getValueAt(selectedRow, 3);
            String status = (String) table.getValueAt(selectedRow, 4);

            // Criar a tela de edição e passá-la para a janela
            AccReceivableEdit editScreen = new AccReceivableEdit(accountName, dueDate, accountType, amount, status);
            JFrame frame = new JFrame("Editar Conta");
            frame.setContentPane(editScreen);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma conta para editar.");
        }
    }


    private void deleteSelectedAccount() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir esta conta?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow); // Remove a conta da tabela
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma conta para excluir.");
        }
    }

    private void openAddAccountScreen() {
        // Lógica para abrir a tela de adicionar conta
        // Aqui você pode instanciar e mostrar a classe que representa a tela de adicionar conta
        AccReceivableAdd addScreen = new AccReceivableAdd();
        JFrame frame = new JFrame("Adicionar Conta");
        frame.setContentPane(addScreen);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
