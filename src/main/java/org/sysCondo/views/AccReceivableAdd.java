package org.sysCondo.views;

import org.sysCondo.components.*;
import org.sysCondo.controller.TaxController;
import org.sysCondo.controller.UnitResidentialController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AccReceivableAdd extends JPanel {
    // Variáveis de instância para armazenar referências dos campos de entrada
    private RoundJTextField taxNameInput;
    private RoundJTextField taxValueInput;
    private RoundJTextField taxFinishDateInput;
    private JComboBox<String> taxStatusInput;

    public AccReceivableAdd() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30 ,0));

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Adicionar Nova Taxa", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400)); // Define a largura e altura do formulário
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        taxNameInput = createAndAddInputField(formContainer, gbc, "Nome da taxa");
        gbc.gridy = 1;
        taxFinishDateInput = createAndAddInputField(formContainer, gbc,"Data de Vencimento (dd/mm/aaaa)");
        gbc.gridy = 2;
        taxValueInput = createAndAddInputField(formContainer, gbc,"Valor da taxa");

        // Centralizar o formulário
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        // Adicionar botões
        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton addButton = new RoundJButton("Adicionar taxa");
        RoundJButton cancelButton = new RoundJButton("Cancelar");
        formButtonsContainer.add(cancelButton);
        formButtonsContainer.add(addButton);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 3;
        formContainer.add(formButtonsContainer, gbc);
        gbc.weighty = 1.0;
        gbc.gridy = 4;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

        // Ação do botão de adicionar conta
        addButton.addActionListener(e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            String taxName = taxNameInput.getText();
            String taxValueText = taxValueInput.getText();
            String taxFinishDateText = taxFinishDateInput.getText();

            if (taxName.isEmpty() || taxValueText.isEmpty() || taxFinishDateText.isEmpty()){
                JOptionPane.showMessageDialog(this, "Por favor preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            float taxValue = Float.parseFloat(taxValueText);
            LocalDate taxFinishDate = LocalDate.parse(taxFinishDateText, formatter);

            TaxController taxController = new TaxController();
            taxController.createTax(taxName, taxValue, taxFinishDate);
            JOptionPane.showMessageDialog(this, "Taxa " + taxName + " adicionada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            taxNameInput.setText("");
            taxValueInput.setText("");
            taxFinishDateInput.setText("");
            // Limpa os campos após adicionar
        });

        // Exibe a janela
        setVisible(true);
    }

    private RoundJTextField createAndAddInputField(JPanel formContainer, GridBagConstraints gbc, String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);
        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.setBackground(Color.WHITE);
        RoundJTextField input = new RoundJTextField(1, 10);
        input.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.add(inputLabel, BorderLayout.NORTH);
        container.add(input, BorderLayout.CENTER);
        formContainer.add(container, gbc);
        return input; // Retorna o campo de entrada para que possamos armazenar a referência
    }

    // Método para criar um combobox com bordas arredondadas
    private JComboBox<String> getComboBoxContainer(String label, String[] options) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);

        // Defina a fonte do rótulo
        comboBoxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.setBackground(Color.WHITE);
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = super.createArrowButton();
                button.setBackground(Color.WHITE);
                button.setBorder(new LineBorder(Color.BLACK, 1));
                return button;
            }
        });

        comboBox.setBackground(Color.WHITE);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)
        ));

        // Defina a fonte do combo box
        comboBox.setFont(new Font("Roboto", Font.PLAIN, 14));

        container.add(comboBoxLabel, BorderLayout.NORTH);
        container.add(comboBox, BorderLayout.CENTER);

        return comboBox;
    }
}
