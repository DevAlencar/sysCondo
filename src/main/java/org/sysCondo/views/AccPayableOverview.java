package org.sysCondo.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;


public class AccPayableOverview extends JPanel {
    private JTable table;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private Runnable onCloseListener;

    public AccPayableOverview() {
        setLayout(new BorderLayout());

        // Painel para o título e controles
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("CONTAS", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Roboto Medium", java.awt.Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel de controles
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlsPanel.setBackground(new Color(202, 202, 202));

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
        addButton.addActionListener(e -> openAddAccountScreen());

        JButton editButton = new RoundJButton("Editar taxa");
        editButton.addActionListener(e -> openEditAccountScreen()); // Ação para editar a conta selecionada

        JButton deleteButton = new RoundJButton("Apagar taxa");
        deleteButton.addActionListener(e -> deleteSelectedAccount()); // Ação para excluir a conta selecionada

        Font labelFont = new Font("Roboto Medium", Font.PLAIN, 14); // Para os rótulos

        // Adicionar componentes ao painel de controles
        JLabel searchLabel = new JLabel("Pesquisar por:");
        searchLabel.setFont(labelFont); // Alterar a fonte do rótulo
        controlsPanel.add(searchLabel);
        searchField.setFont(labelFont); // Alterar a fonte do campo de texto
        controlsPanel.add(searchField);
        controlsPanel.add(editButton);
        controlsPanel.add(deleteButton);

        controlsPanel.add(exportButton);
        controlsPanel.add(addButton);

        headerPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Configuração da tabela
        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        updateTable();
    }

    private void customizeTable() {
        table.setRowHeight(30);
        table.setFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowGrid(true);

        table.getTableHeader().setFont(new java.awt.Font("Roboto Bold", java.awt.Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(26, 135, 255));
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

    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    private void updateTable() {
        String[] columnNames = {"Nome do Fornecedor", "Data de Vencimento", "Valor da Conta", "Status"};
        Object[][] data = {
                {"Aluguel de Escritório", "05/10/2024", "R$ 2.000,00", "Pago"},
                {"Conta de Energia", "15/10/2024", "R$ 500,00", "Pendente"},
                {"Limpeza do Prédio", "25/10/2024", "R$ 300,00", "Atrasado"}
        };

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) { // Se for a coluna de status
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

    private void setStatusColors() {
        table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value.toString();
                if (status.equals("Pago")) {
                    label.setBackground(new Color(145,255,145));
                } else if (status.equals("Pendente")) {
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

    private void exportToPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("ContasAPagar.pdf"));
            document.open();
            document.add(new Paragraph("Overview de Contas a Pagar", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16)));
            document.add(new Paragraph(" "));

            PdfPTable pdfTable = new PdfPTable(tableModel.getColumnCount());
            pdfTable.setWidthPercentage(100);

            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                pdfTable.addCell(new Phrase(tableModel.getColumnName(i)));
            }

            for (int rows = 0; rows < tableModel.getRowCount(); rows++) {
                for (int cols = 0; cols < tableModel.getColumnCount(); cols++) {
                    pdfTable.addCell(new Phrase(tableModel.getValueAt(rows, cols).toString()));
                }
            }

            document.add(pdfTable);
            JOptionPane.showMessageDialog(this, "PDF exportado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao exportar o PDF.");
        } finally {
            document.close();
        }
    }

    private void openAddAccountScreen() {
        AccPayableAdd addScreen = new AccPayableAdd();
        JFrame frame = new JFrame("Adicionar Conta a Pagar");

        // Configurar o listener para fechar o JFrame
        addScreen.setOnCloseListener(frame::dispose);

        frame.setContentPane(addScreen);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void openEditAccountScreen() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Pegar os dados da conta selecionada
            String accountName = (String) table.getValueAt(selectedRow, 0);
            String dueDate = (String) table.getValueAt(selectedRow, 1);
            String amount = (String) table.getValueAt(selectedRow, 2);
            String status = (String) table.getValueAt(selectedRow, 3);

            // Validar os campos antes de adicionar
            if (accountName.isEmpty() || dueDate.isEmpty() || amount.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Por favor, preencha todos os campos obrigatórios.",
                        "Campos Obrigatórios",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Criar a tela de edição e passá-la para a janela
            AccReceivableEdit editScreen = new AccReceivableEdit(accountName, dueDate, amount, status);
            JFrame frame = new JFrame("Editar Conta");
            frame.setContentPane(editScreen);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            System.out.println("Nome: " + accountName);
            System.out.println("Data: " + dueDate);
            System.out.println("Valor: " + amount);
            System.out.println("Status: " + status);
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
}
