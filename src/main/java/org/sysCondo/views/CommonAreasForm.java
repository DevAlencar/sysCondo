package org.sysCondo.views;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Properties;

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
        formContainer.add(getInputContainer("Solicitante"), gbc);
        gbc.gridy = 1;
        formContainer.add(getInputContainer("Endereço do Solicitante"), gbc);
        gbc.gridy = 2;
        formContainer.add(getInputContainer("Contato do Solicitante"), gbc);
        gbc.gridy = 3;
        formContainer.add(getInputContainer("Área desejada"), gbc);
        gbc.gridy = 4;
        formContainer.add(getInputContainer("Código da área"), gbc);
        // Adicionar o campo de data
        gbc.gridy = 5;
        formContainer.add(getDatePickerContainer(), gbc);

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
        gbc.gridy = 6;
        formContainer.add(formButtonsContainer, gbc);

        // Ação do botão de reservar
        reserveButton.addActionListener(e -> {
            // Lógica para processar a reserva
            System.out.println("Reserva feita com sucesso");
            // Limpar campos, se necessário
        });

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

    // Método para criar um seletor de data usando JDatePickerImpl
    private JPanel getDatePickerContainer() {
        JPanel container = new JPanel(new BorderLayout());
        JLabel dateLabel = new JLabel("Data da Reserva");

        // Defina a fonte do rótulo
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.setBackground(Color.WHITE);

        // Configuração do JDatePickerImpl
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Hoje");
        properties.put("text.month", "Mês");
        properties.put("text.year", "Ano");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        // Adiciona o rótulo e o seletor de data ao painel
        container.add(dateLabel, BorderLayout.NORTH);
        container.add(datePicker, BorderLayout.CENTER);

        return container;
    }
}
