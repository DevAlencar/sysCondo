package org.sysCondo.views;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sysCondo.components.DateLabelFormatter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class AccReceivableEdit extends JPanel {
    private RoundJTextField accountNameField;
    private JDatePickerImpl dueDateField;
    private RoundJTextField amountField;
    //private JComboBox<String> statusComboBox;

    public AccReceivableEdit(String accountName, String dueDate, String amount, String status) {
        // Layout com espaços mais definidos
        setLayout(new GridLayout(6, 2, 10, 10));

        // Definir rótulos e campos para edição
        JLabel accountNameLabel = new JLabel("Nome da taxa:");
        accountNameField = new RoundJTextField(20, 10);
        accountNameField.setText(accountName);

        JLabel amountLabel = new JLabel("Valor da taxa:");
        amountField = new RoundJTextField(20, 10);
        amountField.setText(amount);

        //JLabel statusLabel = new JLabel("Status:");
        //String[] statuses = {"Pago", "A receber", "Atrasado"};
        //statusComboBox = new JComboBox<>(statuses);
        //statusComboBox.setSelectedItem(status);

        // Personalizando o estilo
        setBackground(new Color(242, 242, 242)); // Fundo cinza claro
        accountNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        amountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        //statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Adicionar componentes ao painel
        add(accountNameLabel);
        add(accountNameField);
        add(amountLabel);
        add(amountField);
        //add(statusLabel);
        //add(statusComboBox);
        dueDateField = getDatePicker(dueDate);

        // Botão para salvar as alterações
        RoundJButton addButton = new RoundJButton("Adicionar taxa");
        addButton.setBackground(new Color(28, 170, 164));  // Cor de fundo do botão
        addButton.setForeground(Color.WHITE);  // Cor do texto
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAccount();
            }
        });

        // Botão para cancelar a edição
        RoundJButton cancelButton = new RoundJButton("Cancelar");
        cancelButton.setBackground(new Color(255, 80, 80));  // Cor de fundo do botão
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelEdit();
            }
        });

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(242, 242, 242)); // Cor de fundo do painel
        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);

        // Adicionar painel de botões ao layout principal
        add(new JLabel());
        add(buttonPanel);
    }

    // Método para adicionar a conta
    private void addAccount() {
        // Aqui você pode implementar a lógica para adicionar a conta
        String accountName = accountNameField.getText();
        String dueDate = ((UtilDateModel) dueDateField.getModel()).getValue().toString(); //! change this to return the date correctly formatted
        String amount = amountField.getText();
        //String status = (String) statusComboBox.getSelectedItem();

        System.out.println(dueDate);
        // Lógica para salvar a conta

        JOptionPane.showMessageDialog(this, "Taxa adicionada com sucesso!");
    }

    // Método para cancelar a edição
    private void cancelEdit() {
        // Lógica para cancelar a edição
        JOptionPane.showMessageDialog(this, "Edição cancelada!");
    }

    // Método para criar um seletor de data usando JDatePickerImpl
    private JDatePickerImpl getDatePicker(String date) {
        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // creates a date formatter using the dd/MM/yyyy pattern

        try{ // trys to parse the string passed as a parameter to a Date object
            Date parsedDate = formatter.parse(date);
            model.setValue(parsedDate); // with success, we can set the model value to the parsed date
            model.setSelected(true); // shows the date in the date picker
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        properties.put("text.today", "Hoje");
        properties.put("text.month", "Mês");
        properties.put("text.year", "Ano");

        JLabel dateLabel = new JLabel("Data: ");
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        // Configuração do JDatePickerImpl
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel,  new DateLabelFormatter());
        add(dateLabel);
        add(datePicker);
        return datePicker;
    }
}