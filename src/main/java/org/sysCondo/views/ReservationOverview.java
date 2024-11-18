package org.sysCondo.views;

import org.sysCondo.components.Reservation;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

// Change this class to public
public class ReservationOverview extends JPanel {
    private JTable reservationsTable;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private static List<Reservation> allReservations = new ArrayList<>();

    public ReservationOverview() {
        setLayout(new BorderLayout());

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Gerenciar Reservas", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        // Control panel with search field
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchField.setToolTipText("Buscar reserva...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                filterTable(query);
            }
        });

        controlsPanel.add(new JLabel("Pesquisar:"));
        controlsPanel.add(searchField);
        headerPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Solicitante", "Endereço", "Contato", "Área", "Cód.", "Data da Reserva"}, 0);
        reservationsTable = new JTable(tableModel);
        customizeTable();

        sorter = new TableRowSorter<>(tableModel);
        reservationsTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
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
                    reservation.getSolicitante(),
                    reservation.getEndereco(),
                    reservation.getContato(),
                    reservation.getArea(),
                    reservation.getCodigoArea(),
                    reservation.getData()
            });
        }
        tableModel.fireTableDataChanged();
        // Ordena a tabela pela coluna desejada (exemplo: coluna 3 que é a área)
        // Create a list for the sort keys
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        // Add the sort key for the column you want to sort (e.g., index 3 for "Área")
        sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING)); // Change 3 to the index of your desired column

        // Set the sort keys to the sorter
        sorter.setSortKeys(sortKeys);
    }

    public static void addReservation(Reservation reservation) {
        allReservations.add(reservation);
    }

    public static List<Reservation> getAllReservations() {
        return allReservations;
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
                    reservation.getEndereco(),
                    reservation.getContato(),
                    reservation.getArea(),
                    reservation.getCodigoArea(),
                    reservation.getData(),
                    reservation.getTimeSlot()
            });
        }

        tableModel.fireTableDataChanged(); // Notify table data changed
    }

    public static List<String> getAvailableTimeSlots(String area, Date date) {
        // Utilize Arrays.asList para garantir compatibilidade com versões anteriores
        List<String> allTimeSlots = Arrays.asList(
                "08:00 - 10:00",
                "10:00 - 12:00",
                "12:00 - 14:00",
                "14:00 - 16:00",
                "16:00 - 18:00",
                "18:00 - 20:00"
        );

        List<String> reservedSlots = new ArrayList<>();
        for (Reservation reservation : allReservations) {
            if (reservation.getArea().equalsIgnoreCase(area) && reservation.getData().equals(date)) {
                reservedSlots.add(reservation.getTimeSlot());
            }
        }

        // Retorna os horários que ainda não foram reservados
        List<String> availableSlots = new ArrayList<>(allTimeSlots);
        availableSlots.removeAll(reservedSlots);

        return availableSlots;
    }


}
