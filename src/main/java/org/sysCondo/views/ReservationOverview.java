package org.sysCondo.views;

import org.sysCondo.components.Reservation;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.controller.BookingController;
import org.sysCondo.controller.UnitResidentialController;
import org.sysCondo.model.booking.Booking;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

// Change this class to public
public class ReservationOverview extends JPanel {
    private JTable reservationsTable;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private static List<Booking> allReservations = new ArrayList<>();

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

        JButton confirmButton = new RoundJButton("Confirmar");
        confirmButton.addActionListener(e -> confirmSelectedAccount());
        controlsPanel.add(confirmButton);

        JButton canceleButton = new RoundJButton("Cancelar");
        canceleButton.addActionListener(e -> canceleSelectedAccount());
        controlsPanel.add(canceleButton);

        add(headerPanel, BorderLayout.NORTH);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"Id", "Solicitante", "Endereço", "Contato", "Área", "Cód.", "Data da Reserva", "Status"}, 0);
        reservationsTable = new JTable(tableModel);
        customizeTable();

        sorter = new TableRowSorter<>(tableModel);
        reservationsTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(reservationsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scrollPane, BorderLayout.CENTER);

        updateTable();
    }

    private void customizeTable() {
        reservationsTable.setRowHeight(30);
        reservationsTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        reservationsTable.setFillsViewportHeight(true);
        reservationsTable.setGridColor(new Color(230, 230, 230));
        reservationsTable.setShowGrid(true);

        reservationsTable.getTableHeader().setFont(new Font("Roboto Bold", Font.PLAIN, 16));
        reservationsTable.getTableHeader().setBackground(new Color(26, 135, 255));
        reservationsTable.getTableHeader().setForeground(Color.WHITE);

        // Centraliza o conteúdo das células
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        reservationsTable.setDefaultRenderer(Object.class, centerRenderer);

        // Adiciona editor para a coluna de Status
        reservationsTable.getColumnModel().getColumn(6).setCellEditor(
                new DefaultCellEditor(new JComboBox<>(new String[]{"Confirmada", "Cancelada"}))
        );
        setStatusColors();
    }


    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    // Change access modifier to public
    public void updateTable() {
        tableModel.setRowCount(0);
        for (Booking booking : allReservations) {
            tableModel.addRow(new Object[]{
                    booking.getBookingId(),
                    booking.getUserBookingFk().getUserName(),
                    booking.getUserBookingFk().getUnitResidentialFk(),
                    booking.getUserBookingFk().getUserContact(),
                    booking.getCommonAreaBookingFk().getCommonAreaName(),
                    booking.getCommonAreaBookingFk().getCommonAreaId(),
                    booking.getBookingDateTime(),
                    booking.getBookingStatus()
            });
        }
        tableModel.fireTableDataChanged();

        // Ordena a tabela pela coluna desejada
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING)); // Coluna "Solicitante"
        sorter.setSortKeys(sortKeys);
        setStatusColors();
    }

    public static void addReservation(Booking booking) {
        allReservations.add(booking);
    }

    public static List<Booking> getAllReservations() {
        return allReservations;
    }

    public void updateTableByArea(String areaName) {
        tableModel.setRowCount(0);
        allReservations.stream()
                .filter(booking -> booking.getCommonAreaBookingFk().getCommonAreaName().equalsIgnoreCase(areaName))
                .forEach(booking -> tableModel.addRow(new Object[]{
                        booking.getBookingId(),
                        booking.getUserBookingFk().getUserName(),
                        booking.getUserBookingFk().getUnitResidentialFk(),
                        booking.getUserBookingFk().getUserContact(),
                        booking.getCommonAreaBookingFk().getCommonAreaName(),
                        booking.getCommonAreaBookingFk().getCommonAreaId(),
                        booking.getBookingDateTime(),
                        booking.getBookingStatus()
                }));
        tableModel.fireTableDataChanged();
        setStatusColors();
    }


    public static List<String> getAvailableTimeSlots(String area, LocalDate date) {
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
        for (Booking booking : allReservations) {
            if (booking.getCommonAreaBookingFk().getCommonAreaName().equalsIgnoreCase(area) && booking.getBookingDateTime().toLocalDate().equals(date)) {
                LocalTime startTime = booking.getBookingDateTime().toLocalTime(); // Obtém o horário inicial
                LocalTime endTime = startTime.plusHours(2);          // Soma 2 horas

                // Formata os horários no formato HH:mm
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                String startFormatted = startTime.format(timeFormatter);
                String endFormatted = endTime.format(timeFormatter);

                // Retorna o horário formatado
                String timeSlot = startFormatted + " - " + endFormatted;
                reservedSlots.add(timeSlot);
            }
        }

        // Retorna os horários que ainda não foram reservados
        List<String> availableSlots = new ArrayList<>(allTimeSlots);
        availableSlots.removeAll(reservedSlots);

        return availableSlots;
    }

    private void setStatusColors() {
        reservationsTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null) {
                    String status = value.toString();

                    // Define a cor de fundo com base no status
                    switch (status) {
                        case "Confirmado":
                            label.setBackground(new Color(145, 255, 145));  // Verde
                            break;
                        case "Pendente":
                            label.setBackground(new Color(245, 255, 145));  // Amarelo
                            break;
                        case "Cancelado":
                            label.setBackground(new Color(255, 145, 145));  // Vermelho
                            break;
                        default:
                            label.setBackground(Color.WHITE);  // Padrão
                            break;
                    }
                }

                // Ajustar seleção para não sobrescrever a cor
                if (isSelected) {
                    label.setBackground(label.getBackground().darker());
                }

                label.setOpaque(true);
                return label;
            }
        });
    }




    private void confirmSelectedAccount() {
        BookingController bookingController = new BookingController();
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = reservationsTable.convertRowIndexToModel(selectedRow);
            String currentStatus = tableModel.getValueAt(modelRow, 7).toString();

            if (currentStatus.equals("Confirmado")) {
                JOptionPane.showMessageDialog(this, "Esta conta já está confirmada.");
                return;
            }

            tableModel.setValueAt("Confirmado", modelRow, 7);
            setStatusColors(); // Atualiza cores
            bookingController.updateStatus(Long.parseLong(tableModel.getValueAt(modelRow,0).toString()), "Confirmado");
            JOptionPane.showMessageDialog(this, "Conta confirmada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma conta para confirmar.");
        }
    }

    private void canceleSelectedAccount() {
        BookingController bookingController = new BookingController();
        int selectedRow = reservationsTable.getSelectedRow();
        if (selectedRow != -1) {
            int modelRow = reservationsTable.convertRowIndexToModel(selectedRow);
            String currentStatus = tableModel.getValueAt(modelRow, 0).toString(); // Índice da coluna de Status

            // Verifica se o status já está como "Cancelado"
            if (currentStatus.equals("Cancelado")) {
                JOptionPane.showMessageDialog(this, "Esta conta já está cancelada.");
                return;
            }

            // Atualiza o status para "Cancelado"
            tableModel.setValueAt("Cancelado", modelRow, 7);
            setStatusColors();  // Atualiza as cores de status
            reservationsTable.repaint();  // Força o redesenho da tabela para aplicar as novas cores
            bookingController.updateStatus(Long.parseLong(tableModel.getValueAt(modelRow,1).toString()), "Cancelado");
            JOptionPane.showMessageDialog(this, "Conta cancelada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma conta para cancelar.");
        }
    }



}
