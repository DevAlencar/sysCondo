package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CommonAreaMaintenence extends JPanel {
    private final GridBagConstraints gbc = new GridBagConstraints();
    private final JPanel formContainer = new JPanel(new GridBagLayout());

    public CommonAreaMaintenence() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Painel para centralizar o conteúdo
        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer);

        // Título centralizado
        JLabel titleLabel = new JLabel("Solicitar manutenção de área comum", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Bold", Font.PLAIN, 28));
        contentContainer.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 250));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        JComboBox<String> area = getComboBoxContainer("Área", new String[]{"Piscina", "Churrasqueira", "Quadra poli-esportiva", "Quadra tênis"}); // TODO: esse combobox tem q possuir o objeto da área comum retornado do banco {id, nome, etc}
        // Adicionar o campo de data
        gbc.gridy = 1;
        JComboBox<String> maintenence = getComboBoxContainer("Tipo de manutenção", new String[]{"Preventiva", "Corretiva", "Melhoria"});

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

        // Exibe a janela
        setVisible(true);
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
}
