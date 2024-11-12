package org.sysCondo.views;

import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class NewResident extends JPanel {
    private int gridyCounter = 4;
    private List<RoundJTextField[]> vehiclesInputs = new ArrayList<RoundJTextField[]>(); // stores all the vehicles input references
    private JPanel formContainer = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    public NewResident() {
        // Painel principal com BorderLayout
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // cria painel de conteudo para armazenar todo o conteudo da página
        JPanel contentContainer = new JPanel(new BorderLayout());
        JLabel contentTitle = new JLabel("Cadastro de Moradores", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        contentContainer.add(contentTitle, BorderLayout.NORTH);
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        // cria container de formulário scrolavel
        formContainer.setBackground(Color.WHITE);
        formContainer.setBorder(new EmptyBorder(60, 0, 0, 0));
        JScrollPane formScrollPane = new JScrollPane(formContainer);
        formScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        formScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        formScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        formScrollPane.setBorder(new EmptyBorder(0,0,0,0));
        formScrollPane.setMaximumSize(new Dimension(400,400));

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.gridy = 0;
        RoundJTextField nameInput = getInputContainer("Nome do morador");
        gbc.gridy = 1;
        RoundJTextField documentInput = getInputContainer("CPF do morador");
        gbc.gridy = 2;
        RoundJTextField phoneInput = getInputContainer("Telefone do morador");
        gbc.gridy = 3;
        JComboBox<String> residenceNumber = getComboBoxContainer("Número da residência", new String[]{"Unidade 1", "Unidade 2", "Unidade 3"});
        gbc.gridy = gridyCounter++;
        RoundJButton newVehicle = new RoundJButton("Adicionar veículo");
        newVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicleInputs();
            }
        });
        formContainer.add(newVehicle, gbc);

        // Centraliza o formContainer
        JPanel formWrapper = new JPanel();
        formWrapper.setLayout(new BoxLayout(formWrapper, BoxLayout.Y_AXIS));
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formScrollPane);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton saveBtn = new RoundJButton("Salvar"); // botão de salvar
        RoundJButton cancelBtn = new RoundJButton("Cancelar"); // botão de cancelar
        formButtonsContainer.add(cancelBtn);
        formButtonsContainer.add(saveBtn);
        formButtonsContainer.setBackground(Color.WHITE);
        formWrapper.add(formButtonsContainer, BorderLayout.SOUTH);

        // Exibe a janela
        setVisible(true);
    }

    private RoundJTextField getInputContainer(String label) {
        JLabel inputLabel = new JLabel(label);
        JPanel inputContainer = new JPanel(new BorderLayout());
        RoundJTextField input = new RoundJTextField(1, 10);

        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        input.setFont(new Font("Roboto", Font.PLAIN, 14));
        inputContainer.setBackground(Color.WHITE);
        inputContainer.add(inputLabel, BorderLayout.NORTH);
        inputContainer.add(input, BorderLayout.CENTER);
        formContainer.add(inputContainer, gbc);

        return input;
    }

    private void addVehicleInputs() {
        gbc.gridy = gridyCounter++;
        JLabel vehicleIndicator = new JLabel("Veiculo " + (vehiclesInputs.size() + 1));
        vehicleIndicator.setFont(new Font("Roboto", Font.BOLD, 24));
        formContainer.add(vehicleIndicator, gbc);
        gbc.gridy = gridyCounter++;
        RoundJTextField plateInput = getInputContainer("Placa do veículo");
        gbc.gridy = gridyCounter++;
        RoundJTextField brandInput = getInputContainer("Marca do veículo");

        // Armazena os campos em um array e adiciona à lista
        RoundJTextField[] inputsPair = {plateInput, brandInput};
        vehiclesInputs.add(inputsPair);

        // Atualiza o layout após adicionar novos componentes
        formContainer.revalidate();
        formContainer.repaint();
    }

    private JComboBox<String> getComboBoxContainer(String label, String[] options) {
        JPanel comboBoxContainer = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);

        comboBoxLabel.setFont(new Font("Roboto", Font.PLAIN, 14));

        comboBoxContainer.setBackground(Color.WHITE);
        comboBoxContainer.setPreferredSize(new Dimension(100, 50));
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

        comboBox.setFont(new Font("Roboto", Font.PLAIN, 14));

        comboBoxContainer.add(comboBoxLabel, BorderLayout.NORTH);
        comboBoxContainer.add(comboBox, BorderLayout.CENTER);
        formContainer.add(comboBoxContainer, gbc);
        return comboBox;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewResident::new);
    }
}