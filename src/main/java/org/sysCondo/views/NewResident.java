package org.sysCondo.views;

import org.sysCondo.components.*;
import org.sysCondo.controller.UnitResidentialController;
import org.sysCondo.controller.UserController;
import org.sysCondo.model.unitResidential.UnitResidential;
import org.sysCondo.model.user.UserRole;
import org.sysCondo.model.vehicle.Vehicle;

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
    private int gridyCounter = 6;
    private List<RoundJTextField[]> vehiclesInputs = new ArrayList<RoundJTextField[]>(); // stores all the vehicles input references
    private JPanel formContainer = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints();

    // Lista de residências (combobox)
    private JComboBox<UnitResidential> residenceComboBox;

    public NewResident() {
        // Painel principal com BorderLayout
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        // cria painel de conteudo para armazenar todo o conteudo da página
        JPanel contentContainer = new JPanel(new BorderLayout());
        JLabel contentTitle = new JLabel("Cadastro de Moradores", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(contentTitle, BorderLayout.NORTH);
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(contentContainer);

        // cria container de formulário scrolavel
        formContainer.setBackground(Color.WHITE);
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
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridy = 0;
        RoundJTextField nameInput = getInputContainer("Nome do morador");
        gbc.gridy = 1;
        RoundJTextField documentInput = getInputContainer("CPF do morador");
        gbc.gridy = 2;
        RoundJTextField phoneInput = getInputContainer("Telefone do morador");
        gbc.gridy = 3;

        // recebe todas as residencias
        UnitResidentialController unitResidentialController = new UnitResidentialController();
        List<UnitResidential> unitResidentials = unitResidentialController.getAllUnits();

        // Converte as unidades residenciais para um array de Strings com os números das unidades
        String[] residenceOptions = unitResidentials.stream()
                .map(UnitResidential::getUnitResidentialNumber)
                .toArray(String[]::new);

        // apresenta no combobox as residencias cadastradas
        JComboBox<String> residenceNumber = getComboBoxContainer("Número da residência", residenceOptions);
        gbc.gridy = 4;
        formContainer.add(residenceNumber, gbc);

        //JComboBox<String> residenceNumber = getComboBoxContainer("Número da residência", new String[]{"Unidade 1", "Unidade 2", "Unidade 3"});

        gbc.gridy = 5;
        RoundJButton newVehicle = new RoundJButton("Adicionar veículo");
        newVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addVehicleInputs();
            }
        });
        formContainer.add(newVehicle, gbc);
        gbc.gridy = gridyCounter;
        gbc.weighty = 1.0;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

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

        // onclick do botão de salvar
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // pega os valores dos campos
                String name = nameInput.getText();
                String document = documentInput.getText();
                String phone = phoneInput.getText();
                String residence = residenceNumber.getSelectedItem().toString();
                List<Vehicle> vehicles = new ArrayList<Vehicle>();

                // itera sobre os campos de veículos e adiciona à lista
                for (RoundJTextField[] vehicleInputs : vehiclesInputs) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVehicleNumber(vehicleInputs[0].getText());
                    vehicle.setVehicleBrand(vehicleInputs[1].getText());
                    vehicles.add(vehicle);
                }

                // apresenta os veículos cadastrados
                for (Vehicle vehicle : vehicles) {
                    System.out.println("Veículo: " + vehicle.getVehicleNumber() + " - " + vehicle.getVehicleBrand());
                }

                UserController userController = new UserController();
                userController.createUser(name, phone, document, UserRole.USER, residence,vehicles);

            }
        });

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
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));

        comboBox.setFont(new Font("Roboto", Font.PLAIN, 14));

        comboBoxContainer.add(comboBoxLabel, BorderLayout.NORTH);
        comboBoxContainer.add(comboBox, BorderLayout.CENTER);
        formContainer.add(comboBoxContainer, gbc);
        return comboBox;
    }

    // Método para atualizar a lista de residências
    private void updateResidenceList() {
        UnitResidentialController unitController = new UnitResidentialController();
        List<UnitResidential> residences = unitController.getAllUnits();

        // Limpa o combobox atual e adiciona os itens novamente
        residenceComboBox.removeAllItems();
        for (UnitResidential residence : residences) {
            residenceComboBox.addItem(residence);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewResident::new);
    }
}
