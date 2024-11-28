package org.sysCondo.views;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sysCondo.components.*;
import org.sysCondo.controller.BookingController;
import org.sysCondo.controller.CommonAreaController;
import org.sysCondo.model.booking.Booking;
import org.sysCondo.model.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CommonAreasFacilities extends JPanel {
    JComboBox<String> timeSlotComboBox = new JComboBox<>();

    public CommonAreasFacilities() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        timeSlotComboBox = new JComboBox<>();

        initializeReservations();

        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 30, 30, 30));
        add(contentContainer);

        JLabel titleLabel = new JLabel("Áreas Comuns Disponíveis", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        JPanel areasPanel = new JPanel(new GridLayout(2, 5, 10, 10));
        areasPanel.setBackground(Color.WHITE);

        // Add area cards
        areasPanel.add(createAreaCard("Piscina", "0001", "src/main/java/org/sysCondo/assets/piscina.jpg"));
        areasPanel.add(createAreaCard("Quadra Poliesportiva", "0002", "src/main/java/org/sysCondo/assets/quadra.jpg"));
        areasPanel.add(createAreaCard("Academia", "0003", "src/main/java/org/sysCondo/assets/academia.jpg"));
        areasPanel.add(createAreaCard("Churrasqueira", "0004", "src/main/java/org/sysCondo/assets/churrasqueira.jpg"));
        areasPanel.add(createAreaCard("Salão de eventos", "0005", "src/main/java/org/sysCondo/assets/salao.jpeg"));
        areasPanel.add(createAreaCard("Sala de reuniões", "0006", "src/main/java/org/sysCondo/assets/reuniao.jpeg"));
        areasPanel.add(createAreaCard("Sala de Jogos", "0007", "src/main/java/org/sysCondo/assets/jogos.jpg"));
        areasPanel.add(createAreaCard("Brinquedoteca", "0008", "src/main/java/org/sysCondo/assets/brinquedoteca.jpg"));
        areasPanel.add(createAreaCard("SPA e Sauna", "0009", "src/main/java/org/sysCondo/assets/spasauna.jpg"));
        areasPanel.add(createAreaCard("Espaço gourmet", "0010", "src/main/java/org/sysCondo/assets/espaco.jpg"));

        contentContainer.add(areasPanel, BorderLayout.CENTER);

        RoundJButton checkAllReservationsButton = new RoundJButton("Ver Todas as Reservas");
        checkAllReservationsButton.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        checkAllReservationsButton.setBackground(Color.BLACK);
        checkAllReservationsButton.setForeground(Color.WHITE);
        checkAllReservationsButton.setFocusPainted(false);
        checkAllReservationsButton.setBorderPainted(false);

        checkAllReservationsButton.addActionListener(e -> {
            ReservationOverview reservationsOverview = new ReservationOverview();
            // Updated method to retrieve all reservations
            reservationsOverview.updateTable();

            JFrame frame = new JFrame("Todas as Reservas");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(reservationsOverview);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(checkAllReservationsButton);
        contentContainer.add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createAreaCard(String areaName, String areaCode, String imagePath) {
        CommonAreaController commonAreaController = new CommonAreaController();
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);

        JLabel imageLabel;
        try {
            ImageIcon imageIcon = new ImageIcon(imagePath);
            Image scaledImage = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImage), JLabel.CENTER);
        } catch (Exception e) {
            imageLabel = new JLabel("Imagem não encontrada", JLabel.CENTER);
        }
        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        card.add(imageLabel, BorderLayout.CENTER);

        // Create a panel for the area name and code
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE); // Ensure background matches the card's background

        JLabel nameLabel = new JLabel(areaName, JLabel.CENTER);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        titlePanel.add(nameLabel);

        JLabel codLabel = new JLabel("Cód: " + areaCode, JLabel.CENTER);
        codLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        titlePanel.add(codLabel);

        // Add title panel to the card
        card.add(titlePanel, BorderLayout.NORTH);
        RoundJButton reserveButton = new RoundJButton("Reservar");
        reserveButton.setFont(new Font("Roboto", Font.BOLD, 12));
        reserveButton.setBackground(new Color(0, 123, 255));
        reserveButton.setForeground(Color.WHITE);

        reserveButton.addActionListener(e -> {
            JFrame reservationFrame = new JFrame("Reservar - " + areaName);
            reservationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            reservationFrame.setLayout(new BorderLayout());
            reservationFrame.setSize(600, 350); // Aumentei o tamanho para acomodar os elementos

            // Seleção de data
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Hoje");
            p.put("text.month", "Mês");
            p.put("text.year", "Ano");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

            // Lista de horários disponíveis
            JComboBox<String> timeSlotComboBox = new JComboBox<>();
            model.addChangeListener(event -> {
                Date selectedDate = (Date) datePicker.getModel().getValue();
                timeSlotComboBox.removeAllItems();
                if (selectedDate != null) {
                    List<String> availableTimeSlots = ReservationOverview.getAvailableTimeSlots(areaName, selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    if (availableTimeSlots.isEmpty()) {
                        timeSlotComboBox.addItem("Nenhum horário disponível");
                    } else {
                        for (String timeSlot : availableTimeSlots) {
                            timeSlotComboBox.addItem(timeSlot);
                        }
                    }
                }
            });

            // Painel principal que organiza os campos
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // Configuração geral
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;

            // Configuração para a seleção de data
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.5; // Proporção vertical
            mainPanel.add(new JLabel("Escolha uma data:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            mainPanel.add(datePicker, gbc);

            // Configuração para a seleção de horário
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 0.5;
            mainPanel.add(new JLabel("Escolha um horário:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            mainPanel.add(timeSlotComboBox, gbc);

            // Botão de confirmação
            JButton confirmButton = new JButton("Confirmar");
            confirmButton.addActionListener(confirmEvent -> {
                BookingController bookingController = new BookingController();
                User currentUser = Session.getCurrentUser();

                // Obter valores dos campos
                Date selectedDate = (Date) datePicker.getModel().getValue();
                String selectedTimeSlot = (String) timeSlotComboBox.getSelectedItem();

                String formattedDate = "";
                if (selectedDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
                    formattedDate = sdf.format(selectedDate);
                }

                // Validar campos
                if (selectedDate == null || selectedTimeSlot == null || selectedTimeSlot.equals("Nenhum horário disponível")) {
                    JOptionPane.showMessageDialog(reservationFrame, "Por favor, preencha todos os campos e selecione uma data e horário válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean isAvailable = ReservationOverview.getAvailableTimeSlots(areaName, selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).contains(selectedTimeSlot);
                if (isAvailable) {
                    Booking newBooking = new Booking();
                    newBooking.setUserBookingFk(currentUser);
                    newBooking.setCommonAreaBookingFk(commonAreaController.getCommonAreaById(Long.parseLong(areaCode)));
                    newBooking.setBookingStatus("Pendente");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy");
                    LocalDate bookingDate = LocalDate.parse(formattedDate, formatter);
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String startTime = selectedTimeSlot.split(" - ")[0];
                    LocalTime localTime = LocalTime.parse(startTime, timeFormatter);
                    LocalDateTime bookingDateTime = LocalDateTime.of(bookingDate, localTime);
                    newBooking.setBookingDateTime(bookingDateTime);

                    newBooking.setBookingDuration(2);

                    bookingController.createBooking(newBooking.getUserBookingFk(), newBooking.getCommonAreaBookingFk(), newBooking.getBookingDateTime(), newBooking.getBookingDuration());

                    ReservationOverview.addReservation(newBooking);
                    JOptionPane.showMessageDialog(reservationFrame, "Reserva confirmada!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    reservationFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(reservationFrame, "Horário já reservado. Escolha outro horário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Adicionar componentes à janela
            reservationFrame.add(mainPanel, BorderLayout.CENTER);
            reservationFrame.add(confirmButton, BorderLayout.SOUTH);
            reservationFrame.setLocationRelativeTo(null);
            reservationFrame.setVisible(true);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(reserveButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        commonAreaController.createCommonAreaIfNotExists(areaName, Long.parseLong(areaCode));

        return card;
    }

    private boolean checkAvailability(String areaName, LocalDate date) {
        for (Booking booking : ReservationOverview.getAllReservations()) {
            if (booking.getCommonAreaBookingFk().getCommonAreaName().equalsIgnoreCase(areaName) && booking.getBookingDateTime().toLocalDate().equals(date)) {
                return false;
            }
        }
        return true;
    }

    private void initializeReservations() {
        BookingController bookingController = new BookingController();
        List<Booking> allBookings = bookingController.getAllBookings();
        for(Booking booking : allBookings){
            ReservationOverview.addReservation(booking);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Áreas Comuns Disponíveis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new CommonAreasFacilities());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}