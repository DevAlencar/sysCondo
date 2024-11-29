package org.sysCondo.views;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.sysCondo.components.DateLabelFormatter;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.TaxController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

public class AccReceivableEdit extends JPanel {
    private RoundJTextField accountNameField;
    private JDatePickerImpl dueDateField;
    private RoundJTextField amountField;
    //private JComboBox<String> statusComboBox;
    private int taxId;
    private String status;

    public AccReceivableEdit(int taxId, String accountName, String dueDate, String amount, String status) {
        this.taxId = taxId;
        this.status=status;
        // Use um layout mais flexível
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margens
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Rótulo e campo de texto para o nome da taxa
        JLabel accountNameLabel = new JLabel("Nome da taxa:");
        accountNameField = new RoundJTextField(20, 10);
        accountNameField.setText(accountName);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(accountNameField, gbc);

        // Rótulo e campo de texto para valor
        JLabel amountLabel = new JLabel("Valor da taxa:");
        amountField = new RoundJTextField(20, 10);
        amountField.setText(amount);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(amountField, gbc);

        // Rótulo e campo para a data
        JLabel dueDateLabel = new JLabel("Data de vencimento:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(dueDateLabel, gbc);

        dueDateField = getDatePicker(dueDate); // Obtenha o componente de data
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(dueDateField, gbc);

        // Botões
        RoundJButton addButton = new RoundJButton("Atualizar taxa");
        addButton.setBackground(new Color(28, 170, 164)); // Cor de fundo
        addButton.setForeground(Color.WHITE); // Cor do texto
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attAccount();
            }
        });

        RoundJButton cancelButton = new RoundJButton("Cancelar");
        cancelButton.setBackground(new Color(255, 80, 80)); // Cor de fundo
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelEdit();
            }
        });

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(242, 242, 242)); // Fundo do painel
        buttonPanel.add(cancelButton);
        buttonPanel.add(addButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Ocupa duas colunas
        add(buttonPanel, gbc);
    }

    // Método para atualizar a conta
    private void attAccount() {
        try {
            // Captura os dados dos campos
            String accountName = accountNameField.getText();

            // Obtém a data selecionada no JDatePicker
            Date selectedDate = ((UtilDateModel) dueDateField.getModel()).getValue();

            // Formatar a data para "yyyy-MM-dd"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dueDate = formatter.format(selectedDate);

            // Converte a data para LocalDate
            LocalDate finishDate = LocalDate.parse(dueDate);
            float amount = Float.parseFloat(amountField.getText());

            // Verifica a data de vencimento em relação à data atual
            //String status = "A pagar"; // Valor padrão de status
            LocalDate currentDate = LocalDate.now();  // Obtém a data atual

            // Se a data de vencimento for no passado, o status muda para 'Atrasado'
            if (finishDate.isBefore(currentDate)) {
                status = "Atrasado";
            }else{
                status = "A pagar";
            }

            // Instancia o controlador (TaxController) para atualizar a taxa
            TaxController taxController = new TaxController();
            taxController.updateTax(taxId, accountName, amount, status, finishDate); // Chama o método de atualização

            // Exibe mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Taxa atualizada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a taxa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // Formato esperado

        try {
            Date parsedDate = formatter.parse(date); // Converte String para Date
            model.setValue(parsedDate);
            model.setSelected(true);
        } catch (ParseException e) {
            System.err.println("Erro ao converter data: " + date);
            e.printStackTrace();
        }

        properties.put("text.today", "Hoje");
        properties.put("text.month", "Mês");
        properties.put("text.year", "Ano");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        return new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }
}