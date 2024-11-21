package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.controller.CommonAreaController;
import org.sysCondo.controller.MaintenanceController;
import org.sysCondo.controller.UserController;
import org.sysCondo.model.commonArea.CommonArea;
import org.sysCondo.model.user.User;
import org.sysCondo.model.user.UserRole;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CommonAreasMaintenence extends JPanel {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel formContainer = new JPanel(new GridBagLayout());

    public CommonAreasMaintenence() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Solicitar manutenção de área comum", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 250));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Adicionar campos ao formulário
        gbc.gridy = 0;

        CommonAreaController commonAreaController = new CommonAreaController();
        // retorna as áreas comuns salvas no banco
        List<CommonArea> commonAreas = commonAreaController.getAllCommonAreas();
        String[] commonAreasNames = new String[commonAreas.size()];
        for (CommonArea commonArea : commonAreas) {
            System.out.println(commonArea.getCommonAreaName());
            commonAreasNames[commonAreas.indexOf(commonArea)] = commonArea.getCommonAreaName();
        }

        JComboBox<String> area = getComboBoxContainer("Área", commonAreasNames);

        // Adicionar o campo de data
        gbc.gridy = 1;
        JComboBox<String> maintenence = getComboBoxContainer("Tipo de manutenção", new String[]{"Tipo 1", "Tipo 2", "Tipo 3"});

        // Centralizar o formulário
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        // Adicionar botão de reservar
        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton submitButton = new RoundJButton("Solicitar");
        formButtonsContainer.add(submitButton);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 2;
        formContainer.add(formButtonsContainer, gbc);
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

        // Exibe a janela
        setVisible(true);

        MaintenanceController maintenanceController = new MaintenanceController();

        // Cria um usuário para testar
        // TODO: remover após implementar a autenticação, pois o usuário é definido a partir do login
        UserController userController = new UserController();
        User teste = userController.getUserByDocument("123456789");

        // onclick botão de enviar
        submitButton.addActionListener(e -> {
            maintenanceController.createMaintenance(teste, commonAreas.get(area.getSelectedIndex()), "Em progresso", maintenence.getSelectedItem().toString());
        });


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
        JFrame frame = new JFrame("Solicitar manutenção de área comum");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new CommonAreasMaintenence());
        frame.setVisible(true);
    }
}