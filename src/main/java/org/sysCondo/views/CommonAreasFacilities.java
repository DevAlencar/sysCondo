package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.Reservation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Date;

public class CommonAreasFacilities extends JPanel {

    public CommonAreasFacilities() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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

        RoundJButton checkButton = new RoundJButton("Ver Reservas");
        checkButton.setFont(new Font("Roboto", Font.BOLD, 12));
        checkButton.setBackground(Color.BLACK);
        checkButton.setForeground(Color.WHITE);
        checkButton.setFocusPainted(false);
        checkButton.setBorderPainted(false);

        checkButton.addActionListener(e -> {
            ReservationOverview reservationsOverview = new ReservationOverview();
            reservationsOverview.updateTableByArea(areaName);

            JFrame frame = new JFrame("Reservas - " + areaName);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.add(reservationsOverview);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(checkButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }

    private void initializeReservations() {
        ReservationOverview.addReservation(new Reservation("João Silva", "Rua A", "123456789", "Piscina", "0001", new Date()));
        ReservationOverview.addReservation(new Reservation("Maria Oliveira", "Rua B", "987654321", "Quadra Poliesportiva", "0002", new Date()));
        ReservationOverview.addReservation(new Reservation("Carlos Pereira", "Rua C", "456123789", "Academia", "0003", new Date()));
        ReservationOverview.addReservation(new Reservation("Ana Costa", "Rua D", "789456123", "Churrasqueira", "0004", new Date()));
        ReservationOverview.addReservation(new Reservation("Pedro Santos", "Rua E", "321654987", "Salão de eventos", "0005", new Date()));
        ReservationOverview.addReservation(new Reservation("Laura Lima", "Rua F", "654321987", "Sala de reuniões", "0006", new Date()));
        ReservationOverview.addReservation(new Reservation("Mariana Rocha", "Rua G", "963852741", "Sala de Jogos", "0007", new Date()));
        ReservationOverview.addReservation(new Reservation("Felipe Almeida", "Rua H", "147258369", "Brinquedoteca", "0008", new Date()));
        ReservationOverview.addReservation(new Reservation("Camila Ferreira", "Rua I", "258369147", "SPA e Sauna", "0009", new Date()));
        ReservationOverview.addReservation(new Reservation("Lucas Gomes", "Rua J", "369258147", "Espaço gourmet", "0010", new Date()));
        ReservationOverview.addReservation(new Reservation("Fernanda Ribeiro", "Rua K", "456789123", "Piscina", "0011", new Date()));
        ReservationOverview.addReservation(new Reservation("Tiago Martins", "Rua L", "987123654", "Salão de festas", "0012", new Date()));
        ReservationOverview.addReservation(new Reservation("Roberta Mendes", "Rua M", "654789321", "Piscina", "0013", new Date()));
        ReservationOverview.addReservation(new Reservation("Vinícius Costa", "Rua N", "321789654", "Churrasqueira", "0014", new Date()));
        ReservationOverview.addReservation(new Reservation("Juliana Almeida", "Rua O", "852741963", "Churrasqueira", "0015", new Date()));
        ReservationOverview.addReservation(new Reservation("Rafael Lima", "Rua P", "789123456", "Quadra Poliesportiva", "0016", new Date()));
        ReservationOverview.addReservation(new Reservation("Sofia Santos", "Rua Q", "147852369", "Sala de Jogos", "0017", new Date()));
        ReservationOverview.addReservation(new Reservation("Gabriel Souza", "Rua R", "963741258", "Quadra Poliesportiva", "0018", new Date()));
    }

}