package org.sysCondo.views;
import org.jdatepicker.JDatePicker;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.Reservation;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sysCondo.components.DateLabelFormatter;
import org.sysCondo.components.RoundJTextField;

import java.util.Properties;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

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

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(areaName, JLabel.CENTER);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        titlePanel.add(nameLabel);

        JLabel codLabel = new JLabel("Cód: " + areaCode, JLabel.CENTER);
        codLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
        titlePanel.add(codLabel);

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

            // Campos de texto
            JTextField nomeField = new RoundJTextField(20, 10);
            JTextField contatoField = new RoundJTextField(20, 10);
            JTextField enderecoField = new RoundJTextField(20, 10);

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
                    List<String> availableTimeSlots = ReservationOverview.getAvailableTimeSlots(areaName, selectedDate);
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

            // Configuração de linhas com alturas diferentes
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weighty = 0.1; // 10% do espaço vertical
            mainPanel.add(new JLabel("Nome:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            mainPanel.add(nomeField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weighty = 0.1;
            mainPanel.add(new JLabel("Contato:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            mainPanel.add(contatoField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weighty = 0.1;
            mainPanel.add(new JLabel("Endereço:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            mainPanel.add(enderecoField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 0.1;
            mainPanel.add(new JLabel("Escolha uma data:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 3;
            mainPanel.add(datePicker, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weighty = 0.2;
            mainPanel.add(new JLabel("Escolha um horário:"), gbc);

            gbc.gridx = 1;
            gbc.gridy = 4;
            mainPanel.add(timeSlotComboBox, gbc);

            // Botão de confirmação
            JButton confirmButton = new JButton("Confirmar");
            confirmButton.addActionListener(confirmEvent -> {
                // Obter valores dos campos
                String nome = nomeField.getText();
                String contato = contatoField.getText();
                Date selectedDate = (Date) datePicker.getModel().getValue();
                String selectedTimeSlot = (String) timeSlotComboBox.getSelectedItem();
                String endereco = enderecoField.getText();

                String formattedDate = "";
                if (selectedDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy");
                    formattedDate = sdf.format(selectedDate);
                }

                // Printar no console para verificar
                System.out.println("Nome: " + nome);
                System.out.println("Contato: " + contato);
                System.out.println("Endereço: " + endereco);
                System.out.println("Data Selecionada: " + formattedDate);
                System.out.println("Horário Selecionado: " + selectedTimeSlot);
                System.out.println("Área: " + areaName);
                System.out.println("Cód. área: " + areaCode);

                // Validar campos
                if (nome.isEmpty() || contato.isEmpty() || endereco.isEmpty() || selectedDate == null || selectedTimeSlot == null || selectedTimeSlot.equals("Nenhum horário disponível")) {
                    JOptionPane.showMessageDialog(reservationFrame, "Por favor, preencha todos os campos e selecione uma data e horário válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean isAvailable = ReservationOverview.getAvailableTimeSlots(areaName, selectedDate).contains(selectedTimeSlot);
                if (isAvailable) {
                    Reservation newReservation = new Reservation(nome, endereco, contato, areaName, areaCode, selectedDate, selectedTimeSlot, "Pendente");
                    ReservationOverview.addReservation(newReservation);
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

        return card;
    }

    private boolean checkAvailability(String areaName, Date date) {
        for (Reservation reservation : ReservationOverview.getAllReservations()) {
            if (reservation.getArea().equalsIgnoreCase(areaName) && reservation.getData().equals(date)) {
                return false;
            }
        }
        return true;
    }

    private void initializeReservations() {
        ReservationOverview.addReservation(new Reservation("João Silva", "Rua A", "123456789", "Piscina", "0001", new Date(), "10:00 - 11:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Maria Oliveira", "Rua B", "987654321", "Quadra Poliesportiva", "0002", new Date(), "14:00 - 15:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Carlos Pereira", "Rua C", "456123789", "Academia", "0003", new Date(), "07:00 - 08:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Ana Costa", "Rua D", "789456123", "Churrasqueira", "0004", new Date(), "12:00 - 14:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Pedro Santos", "Rua E", "321654987", "Salão de eventos", "0005", new Date(), "18:00 - 22:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Laura Lima", "Rua F", "654321987", "Sala de reuniões", "0006", new Date(), "09:00 - 10:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Mariana Rocha", "Rua G", "963852741", "Sala de Jogos", "0007", new Date(), "16:00 - 17:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Felipe Almeida", "Rua H", "147258369", "Brinquedoteca", "0008", new Date(), "10:00 - 11:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Camila Ferreira", "Rua I", "258369147", "SPA e Sauna", "0009", new Date(), "15:00 - 17:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Lucas Gomes", "Rua J", "369258147", "Espaço gourmet", "0010", new Date(), "19:00 - 21:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Fernanda Ribeiro", "Rua K", "456789123", "Piscina", "0011", new Date(), "11:00 - 12:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Tiago Martins", "Rua L", "987123654", "Salão de festas", "0012", new Date(), "17:00 - 21:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Roberta Mendes", "Rua M", "654789321", "Piscina", "0013", new Date(), "13:00 - 14:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Vinícius Costa", "Rua N", "321789654", "Churrasqueira", "0014", new Date(), "12:00 - 14:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Juliana Almeida", "Rua O", "852741963", "Churrasqueira", "0015", new Date(), "14:00 - 16:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Rafael Lima", "Rua P", "789123456", "Quadra Poliesportiva", "0016", new Date(), "16:00 - 17:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Sofia Santos", "Rua Q", "147852369", "Sala de Jogos", "0017", new Date(), "17:00 - 18:00", "Pendente"));
        ReservationOverview.addReservation(new Reservation("Gabriel Souza", "Rua R", "963741258", "Quadra Poliesportiva", "0018", new Date(), "15:00 - 16:00", "Pendente"));
    }

}