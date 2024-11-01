package org.sysCondo.views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CommonAreasFacilities extends JPanel {

    public CommonAreasFacilities() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 30, 30, 30));
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Áreas Comuns Disponíveis", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para os cartões de áreas comuns
        JPanel areasPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // Ajuste o GridLayout conforme necessário
        areasPanel.setBackground(Color.WHITE);

        // Adiciona cartões para as áreas
        areasPanel.add(createAreaCard("Piscina", "Imagem da piscina"));
        areasPanel.add(createAreaCard("Quadra de Tênis", "Imagem da quadra"));
        areasPanel.add(createAreaCard("Academia", "Imagem da academia"));

        contentContainer.add(areasPanel, BorderLayout.CENTER);

        // Botão central para verificar disponibilidade geral
        JButton checkAvailabilityButton = new JButton("Verificar Disponibilidade");
        checkAvailabilityButton.setFont(new Font("Roboto", Font.BOLD, 16));
        checkAvailabilityButton.setBackground(Color.BLACK);
        checkAvailabilityButton.setForeground(Color.WHITE);
        checkAvailabilityButton.setFocusPainted(false);
        checkAvailabilityButton.setBorderPainted(false);

        // Adiciona o botão abaixo do painel de áreas
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(checkAvailabilityButton);
        contentContainer.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Método para criar um cartão de área
    private JPanel createAreaCard(String areaName, String placeholderText) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        card.setBackground(Color.WHITE);

        // Placeholder para imagem
        JLabel imageLabel = new JLabel(placeholderText, JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(200, 150));
        imageLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        card.add(imageLabel, BorderLayout.CENTER);

        // Nome da área
        JLabel nameLabel = new JLabel(areaName, JLabel.CENTER);
        nameLabel.setFont(new Font("Roboto", Font.BOLD, 16));
        card.add(nameLabel, BorderLayout.NORTH);

        // Botão de verificar disponibilidade
        JButton checkButton = new JButton("Verificar Disponibilidade");
        checkButton.setFont(new Font("Roboto", Font.PLAIN, 12));
        checkButton.setBackground(Color.WHITE);
        checkButton.setFocusPainted(false);
        checkButton.setBorderPainted(false);
        checkButton.setForeground(Color.BLUE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(checkButton);
        card.add(buttonPanel, BorderLayout.SOUTH);

        return card;
    }
}
