package org.sysCondo.views;

import com.toedter.calendar.JDateChooser;
import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CommonAreasForm extends JPanel {

    public CommonAreasForm() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Reserva de Áreas Comuns", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        formContainer.add(getInputContainer("Nome"), gbc);
        gbc.gridy = 1;
        formContainer.add(getInputContainer("Número da Unidade"), gbc);
        gbc.gridy = 2;
        formContainer.add(getInputContainer("Pedidos Especiais (opcional)"), gbc);

        // Centralizar o formulário
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        // Adicionar botão de reservar
        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton reserveButton = new RoundJButton("Reservar");
        formButtonsContainer.add(reserveButton);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 3;
        formContainer.add(formButtonsContainer, gbc);

        // Ação do botão de reservar
        reserveButton.addActionListener(e -> {
            // Lógica para processar a reserva
            System.out.println("Reserva feita com sucesso");
            // Limpar campos, se necessário
        });

        // Adicionar o calendário abaixo do formulário
        JCalendar calendar = new JCalendar();
        calendar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentContainer.add(calendar, BorderLayout.SOUTH);

        // Exibe a janela
        setVisible(true);
    }

    // Método para criar um campo de entrada com bordas arredondadas
    private JPanel getInputContainer(String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);

        // Defina a fonte do rótulo
        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.setBackground(Color.WHITE);
        RoundJTextField input = new RoundJTextField(1, 10);

        // Defina a fonte do campo de entrada
        input.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.add(inputLabel, BorderLayout.NORTH);
        container.add(input, BorderLayout.CENTER);

        return container;
    }
}
