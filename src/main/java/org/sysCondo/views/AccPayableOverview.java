package org.sysCondo.views;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.AccountController;
import org.sysCondo.controller.TaxController;
import org.sysCondo.model.account.Account;
import org.sysCondo.model.tax.Tax;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.util.List;


public class AccPayableOverview extends JPanel {
    private final JTable table;
    private final JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private final AccountController accountController;
    private Object[][] data;
    private final String[] columnNames = {"Id", "Nome do Fornecedor", "Data de Vencimento", "Tipo de Despesa", "Valor da Conta", "Status"};

    public AccPayableOverview() {
        this.accountController = new AccountController();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        // Painel para o título e controles
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("Contas a pagar", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
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

        JButton payButton = new RoundJButton("Pagar Conta");
        payButton.addActionListener(e -> paySelectedAccount());

        Font labelFont = new Font("Roboto Medium", Font.PLAIN, 14); // Para os rótulos

        // Adicionar componentes ao painel de controles
        JLabel searchLabel = new JLabel("Pesquisar por:");
        searchLabel.setFont(labelFont); // Alterar a fonte do rótulo
        controlsPanel.add(searchLabel);
        searchField.setFont(labelFont); // Alterar a fonte do campo de texto
        controlsPanel.add(searchField);

        controlsPanel.add(exportButton);
        controlsPanel.add(addButton);
        controlsPanel.add(payButton);

        headerPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Configuração da tabela
        table = new JTable();
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(scrollPane, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() { // função chamada cada vez que o painel aparece em tela
            @Override
            public void componentShown(ComponentEvent e) {
                System.out.println("Contas a pagar");
                updateTable();
            }
        });
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
    }

    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    private void updateTable() {
        List<Account> accounts = accountController.getAllAccounts();
        data = accounts.stream()
                .map(account -> new Object[]{
                        account.getAccountId(),
                        account.getSupplier(),
                        account.getFinishDate(),
                        account.getType(),
                        account.getValue(),
                        account.getStatus()
                })
                .toArray(Object[][]::new);

        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) { // Se for a coluna de status
                    return String.class; // Retorna como String para cores
                }
                return super.getColumnClass(columnIndex);
            }
        };
        table.setModel(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.toggleSortOrder(0);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        table.getColumnModel().getColumn(0).setPreferredWidth(30); // Coluna ID com largura menor
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Nome da Fornecedor
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Data de Vencimento
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Tipo de despesa
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Valor da Conta
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Status
        setStatusColors();
    }

    private void setStatusColors() {
        table.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value.toString();
                if (status.equals("Pago")) {
                    label.setBackground(new Color(145,255,145));
                } else if (status.equals("A pagar")) {
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

    private void paySelectedAccount() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = table.convertRowIndexToModel(selectedRow);
            String currentStatus = tableModel.getValueAt(modelRow, 5).toString();
            if (currentStatus.equals("Pago")) {
                JOptionPane.showMessageDialog(this, "Esta conta já está paga.");
                return;
            }
            Account selectedAccount = accountController.getAccountById((Integer) table.getValueAt(selectedRow, 0));
            accountController.updateAccountStatus(selectedAccount.getAccountId(), "Pago");
            table.setValueAt("Pago", selectedRow, 5);
            setStatusColors();
            JOptionPane.showMessageDialog(this, "Conta marcada como paga com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma conta para pagar.");
        }
    }

    private void openAddAccountScreen() {
        AccPayableAdd addScreen = new AccPayableAdd();
        JFrame frame = new JFrame("Adicionar Conta a Pagar");
        frame.setContentPane(addScreen);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
