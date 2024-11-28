package org.sysCondo.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.controller.*;
import org.sysCondo.model.Statement;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.maintenance.Maintenance;
import org.sysCondo.model.tax.Tax;
import org.sysCondo.model.unitResidential.UnitResidential;
import org.sysCondo.model.user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class Reports extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public Reports(JFrame parentFrame) {
        setLayout(new BorderLayout());

        // Painel para o título e controles
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);

        // Título
        JLabel titleLabel = new JLabel("RELATÓRIOS", JLabel.CENTER);
        titleLabel.setFont(new java.awt.Font("Roboto Medium", java.awt.Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Adiciona o painel de título ao painel principal
        add(headerPanel, BorderLayout.NORTH);

        // Dropdown para selecionar o tipo de relatório
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel selectLabel = new JLabel("Selecione o tipo de relatório:");
        selectLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 14));
        controlsPanel.add(selectLabel);

        UIManager.put("ComboBox.selectionBackground", new Color(159, 159, 159)); // Cor de fundo da seleção
        UIManager.put("ComboBox.selectionForeground", Color.BLACK); // Cor do texto da seleção

        String[] reportTypes = {"Unidades", "Moradores", "Taxas", "Reservas", "Manutenções", "Comunicados"};
        JComboBox<String> reportSelector = new JComboBox<>(reportTypes);
        reportSelector.setBackground(new Color(250, 250, 250));  // Cor de fundo (cinza claro)
        reportSelector.setForeground(new Color(0, 0, 0));  // Cor do texto (preto)
        reportSelector.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        reportSelector.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        controlsPanel.add(reportSelector);

        JButton generateButton = new RoundJButton("Gerar Relatório");
        generateButton.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        generateButton.setForeground(Color.WHITE);
        controlsPanel.add(generateButton);

        JButton exportButton = new RoundJButton("Exportar para PDF");
        exportButton.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        exportButton.setForeground(Color.WHITE);
        controlsPanel.add(exportButton);

        add(controlsPanel, BorderLayout.NORTH);

        // Tabela para exibir os dados
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Chama o método para personalizar a tabela
        customizeTable();

        // Ação ao clicar no botão "Gerar Relatório"
        generateButton.addActionListener(e -> {
            String selectedReport = (String) reportSelector.getSelectedItem();
            if (selectedReport != null) {
                populateTable(selectedReport);
            }
        });

        // Ação ao clicar no botão "Exportar para PDF"
        exportButton.addActionListener(e -> exportToPDF());
    }

    /**
     * Popula a tabela com base no tipo de relatório selecionado.
     */
    private void populateTable(String reportType) {
        tableModel.setRowCount(0); // Limpa os dados da tabela

        switch (reportType) {
            case "Unidades":
                tableModel.setColumnIdentifiers(new String[]{"Número", "Tamanho", "Nome do Dono", "Telefone"});

                UnitResidentialController unitResidentialController = new UnitResidentialController();

                // Busca as unidades residenciais no banco de dados
                List<UnitResidential> units = unitResidentialController.getAllUnits();

                // Adiciona os dados à tabela
                for (UnitResidential unit : units) {
                    tableModel.addRow(new String[]{
                            unit.getUnitResidentialNumber(),
                            unit.getUnitResidentialSize(),
                            unit.getOwnerName(),
                            unit.getOwnerContact()
                    });
                }

                break;

            case "Moradores":
                tableModel.setColumnIdentifiers(new String[]{"Nome", "CPF", "Contato", "Unidade"});

                UserController userController = new UserController();

                // Busca os moradores no banco de dados
                List<User> users = userController.getAllUsers();

                // Adiciona os dados à tabela
                for (User user : users) {
                    tableModel.addRow(new String[]{
                            user.getUserName(),
                            user.getUserDocument(),
                            user.getUserContact(),
                            user.getUnitResidentialFk(),
                    });
                }

                break;

            case "Taxas":
                tableModel.setColumnIdentifiers(new String[]{"Nome", "Valor", "Vencimento"});

                TaxController taxController = new TaxController();

                // Busca as taxas no banco de dados
                List<Tax> taxes = taxController.getAllTaxes();

                // Adiciona os dados à tabela
                for (Tax tax : taxes) {
                    tableModel.addRow(new String[]{
                            tax.getName(),
                            String.valueOf(tax.getValue()),
                            tax.getFinishDate().toString(),
                    });
                }

                break;

            case "Reservas":
                tableModel.setColumnIdentifiers(new String[]{"Solicitante", "Contato", "Área", "Código", "Data"});

                BookingController bookingController = new BookingController();

                List<Booking> bookings = bookingController.getAllBookings();

                // Adiciona os dados à tabela
                for (Booking booking : bookings) {
                    tableModel.addRow(new String[]{
                            booking.getUserBookingFk().getUserName(),
                            booking.getUserBookingFk().getUserContact(),
                            booking.getCommonAreaBookingFk().getCommonAreaName(),
                            booking.getBookingId().toString(),
                            booking.getBookingDateTime().toString(),
                    });
                }

                break;

            case "Manutenções":
                tableModel.setColumnIdentifiers(new String[]{"Área", "Tipo", "Solicitante", "Status"});

                MaintenanceController maintenanceController = new MaintenanceController();
                List<Maintenance> maintenances = maintenanceController.getAllMaintenances();

                for (Maintenance maintenance : maintenances) {
                    tableModel.addRow(new String[]{
                            maintenance.getCommonAreaMaintenanceFk().getCommonAreaName(),
                            maintenance.getType(),
                            maintenance.getUserMaintenanceFk().getUserName(),
                            maintenance.getStatus(),
                    });
                }

                break;

            case "Comunicados":
                tableModel.setColumnIdentifiers(new String[]{"Título", "Mensagem", "Data"});

                StatementController statementController = new StatementController();

                List<Statement> statements = statementController.getAllStatements();

                for (Statement statement : statements) {
                    tableModel.addRow(new String[]{
                            statement.getStatementTitle(),
                            statement.getStatementDescription(),
                            statement.getStatementDate().toString(),
                    });
                }

                break;
        }
    }

    /**
     * Adiciona dados à tabela.
     */
    private void addTableData(List<String[]> data) {
        for (String[] row : data) {
            tableModel.addRow(row);
        }
    }

    /**
     * Exporta os dados da tabela para um arquivo PDF.
     */
    private void exportToPDF() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Relatorio.pdf"));
            document.open();
            document.add(new Paragraph("Relatório", new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 16)));
            document.add(new Paragraph(" "));

            PdfPTable pdfTable = new PdfPTable(tableModel.getColumnCount());
            pdfTable.setWidthPercentage(100);

            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                pdfTable.addCell(new Phrase(tableModel.getColumnName(i)));
            }

            for (int rows = 0; rows < tableModel.getRowCount(); rows++) {
                for (int cols = 0; cols < tableModel.getColumnCount(); cols++) {
                    pdfTable.addCell(new Phrase(String.valueOf(tableModel.getValueAt(rows, cols))));
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

    /**
     * Personaliza o layout da tabela.
     */
    private void customizeTable() {
        table.setRowHeight(30);
        table.setFont(new java.awt.Font("Roboto", java.awt.Font.PLAIN, 14));
        table.setFillsViewportHeight(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setShowGrid(true);

        table.getTableHeader().setFont(new java.awt.Font("Roboto Bold", java.awt.Font.PLAIN, 16));
        table.getTableHeader().setBackground(new Color(100, 53, 159));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(headerRenderer);
        }

        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
    }

    // main
    public static void main(String[] args) {
        JFrame frame = new JFrame("Relatórios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.add(new Reports(frame));
        frame.setVisible(true);
    }
}