package org.sysCondo.views;

import org.sysCondo.components.Reservation;
import org.sysCondo.components.RoundJButton;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Change this class to public
public class ReservationOverview extends JPanel {
    private JTable reservationsTable;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private static List<Reservation> allReservations = new ArrayList<>();

    public ReservationOverview() {
        JButton confirmReservation = new RoundJButton("Confirmar reserva");
        JButton cancelReservation = new RoundJButton("Cancelar reserva");
        JLabel titleLabel = new JLabel("Reservas", JLabel.CENTER);
        JPanel headerPanel = new JPanel(new BorderLayout());
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        setLayout(new BorderLayout());

        // Header panel
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.setBackground(Color.WHITE);
        // Control panel with search field
        controlsPanel.setBackground(Color.LIGHT_GRAY);
        searchField.setToolTipText("Buscar reserva...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                filterTable(query);
            }
        });
        confirmReservation.addActionListener(e -> handleConfirmReservation()); // Ação para confirmar a reserva selecionada
        cancelReservation.addActionListener(e -> handleCancelReservation()); // Ação para cancelar a reserva selecionada

        controlsPanel.add(new JLabel("Pesquisar:"));
        controlsPanel.add(searchField);
        controlsPanel.add(confirmReservation);
        controlsPanel.add(cancelReservation);
        headerPanel.add(controlsPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Identificador", "Solicitante", "Área", "Data", "Status"}, 0);
        reservationsTable = new JTable(tableModel){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        reservationsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customizeTable();

        sorter = new TableRowSorter<>(tableModel);
        reservationsTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        updateTable(); // Populate the table with initial data
    }

    private void customizeTable() {
        reservationsTable.setRowHeight(30);
        reservationsTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        reservationsTable.getTableHeader().setFont(new Font("Roboto Bold", Font.PLAIN, 16));
        reservationsTable.getTableHeader().setBackground(new Color(0, 132, 96));
        reservationsTable.getTableHeader().setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        reservationsTable.setDefaultRenderer(Object.class, centerRenderer);
    }

    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    // Change access modifier to public
    public void updateTable() {
        tableModel.setRowCount(0);
        for (Reservation reservation : allReservations) {
            tableModel.addRow(new Object[]{
                    reservation.getId(),
                    reservation.getSolicitante(),
                    reservation.getArea(),
                    reservation.getData(),
                    reservation.getStatus()
            });
        }
        tableModel.fireTableDataChanged();
        // Ordena a tabela pela coluna desejada (exemplo: coluna 3 que é a área)
        // Create a list for the sort keys
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        // Add the sort key for the column you want to sort (e.g., index 3 for "Área")
        sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING)); // Change 3 to the index of your desired column

        // Set the sort keys to the sorter
        sorter.setSortKeys(sortKeys);
        setStatusColors();
    }

    private void setStatusColors() {
        reservationsTable.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Set background color based on status
                String status = value != null ? value.toString() : "";
                switch (status) {
                    case "Confirmada":
                        label.setBackground(new Color(145, 255, 145));
                        break;
                    case "Pendente":
                        label.setBackground(new Color(245, 255, 145));
                        break;
                    case "Cancelada":
                        label.setBackground(new Color(255, 145, 145));
                        break;
                    default:
                        label.setBackground(Color.WHITE);
                        break;
                }
                label.setOpaque(true); // Necessary for background color
                return label;
            }
        });
    }

    public static void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

    //public static List<Reservation> getAllReservations() {
    //    return allReservations;
    //}

    public void handleConfirmReservation() { // method used to handle the confirmation on the view
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow >= 0) {
            String selectedRowStatus = (String) reservationsTable.getValueAt(selectedRow, 4);
            if (selectedRowStatus.equals("Confirmada")) {
                JOptionPane.showMessageDialog(this, "A reserva atual já está confirmada");
            } else {
                reservationsTable.setValueAt("Confirmada", selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Reserva confirmada com sucesso!");
                System.out.println(reservationsTable.getValueAt(selectedRow, 0)); // getting the reservation id
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para continuar.");
        }
    }

    public void handleCancelReservation() { // method used to handle the cancellation on the view
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow >= 0) {
            String selectedRowStatus = (String) reservationsTable.getValueAt(selectedRow, 4);
            if (selectedRowStatus.equals("Cancelada")) {
                JOptionPane.showMessageDialog(this, "A reserva atual já está cancelada");
            } else {
                reservationsTable.setValueAt("Cancelada", selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Reserva cancelada com sucesso!");
                System.out.println(reservationsTable.getValueAt(selectedRow, 0)); // getting the reservation id
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione uma reserva para continuar.");
        }
    }

    public void updateTableByArea(String areaName) {
        tableModel.setRowCount(0); // Clear the current table
        List<Reservation> filteredReservations = new ArrayList<>();

        // Filter reservations by area
        for (Reservation reservation : allReservations) {
            if (reservation.getArea().equalsIgnoreCase(areaName)) {
                filteredReservations.add(reservation);
            }
        }

        // Sort the filtered reservations by area name
        filteredReservations.sort(Comparator.comparing(Reservation::getArea));

        // Populate the table with sorted reservations
        for (Reservation reservation : filteredReservations) {
            tableModel.addRow(new Object[]{
                    reservation.getSolicitante(),
                    reservation.getArea(),
                    reservation.getData()
            });
        }

        tableModel.fireTableDataChanged(); // Notify table data changed
    }

}
