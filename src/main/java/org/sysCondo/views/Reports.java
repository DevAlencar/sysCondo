package org.sysCondo.views;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.sysCondo.components.RoundJButton;

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
                // Exemplo de dados:
                addTableData(Arrays.asList(
                        new String[]{"101", "90m²", "Carlos Silva", "99999-9999"},
                        new String[]{"102", "80m²", "Maria Oliveira", "98888-8888"}
                ));
                break;

            case "Moradores":
                tableModel.setColumnIdentifiers(new String[]{"Nome", "CPF", "Contato", "Unidade", "Veículo"});
                addTableData(Arrays.asList(
                        new String[]{"João Souza", "123.456.789-00", "97777-7777", "101", "ABC-1234, Ford"},
                        new String[]{"Ana Paula", "987.654.321-00", "96666-6666", "102", "XYZ-5678, Toyota"}
                ));
                break;

            case "Taxas":
                tableModel.setColumnIdentifiers(new String[]{"Nome", "Valor", "Vencimento", "Status"});
                addTableData(Arrays.asList(
                        new String[]{"Condomínio", "R$ 300,00", "2024-11-30", "Pago"},
                        new String[]{"Água", "R$ 150,00", "2024-11-25", "Atrasado"}
                ));
                break;

            case "Reservas":
                tableModel.setColumnIdentifiers(new String[]{"Solicitante", "Endereço", "Contato", "Área", "Código", "Data"});
                addTableData(Arrays.asList(
                        new String[]{"João Souza", "Apto 101", "97777-7777", "Salão de Festas", "SF-01", "2024-12-10"},
                        new String[]{"Ana Paula", "Apto 102", "96666-6666", "Churrasqueira", "CH-02", "2024-12-15"}
                ));
                break;

            case "Manutenções":
                tableModel.setColumnIdentifiers(new String[]{"Área", "Tipo", "Solicitante", "Status"});
                addTableData(Arrays.asList(
                        new String[]{"Piscina", "Limpeza", "Carlos Silva", "Concluído"},
                        new String[]{"Elevador", "Reparo", "Administração", "Pendente"}
                ));
                break;

            case "Comunicados":
                tableModel.setColumnIdentifiers(new String[]{"Título", "Mensagem", "Data"});
                addTableData(Arrays.asList(
                        new String[]{"Aviso de Reunião", "Haverá uma reunião no dia 25/11", "2024-11-18"},
                        new String[]{"Manutenção Geral", "A manutenção elétrica será no dia 20/11", "2024-11-15"}
                ));
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
}