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
        /*ReservationOverview.addReservation(new Reservation(1,"João Silva", "Piscina", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(2, "Maria Oliveira","Quadra Poliesportiva", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(3, "Carlos Pereira",  "Academia", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(4, "Ana Costa",  "Churrasqueira", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(5, "Pedro Santos", "Salão de eventos", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(6, "Laura Lima", "Sala de reuniões", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(7, "Mariana Rocha", "Sala de Jogos", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(8, "Felipe Almeida","Brinquedoteca", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(9, "Camila Ferreira", "SPA e Sauna", new Date(), "Pendente"));
        ReservationOverview.addReservation(new Reservation(10, "Lucas Gomes",  "Espaço gourmet", new Date(), "Pendente"));*/
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