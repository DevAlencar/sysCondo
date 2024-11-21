package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextArea;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.StatementController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewStatement extends JPanel {
    public NewStatement() {
        // Painel principal com BorderLayout
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        add(contentContainer);

        JLabel contentTitle = new JLabel("Novo comunicado", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentContainer.add(contentTitle, BorderLayout.NORTH);

        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 400)); // Define a largura e altura do formulário
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.gridy = 0;
        formContainer.add(getInputContainer("Título"), gbc);
        gbc.gridy = 1;
        formContainer.add(getTextArea("Mensagem"), gbc);

        // Centraliza o formContainer
        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        contentContainer.add(formWrapper, BorderLayout.CENTER);

        JPanel formButtonsContainer = new JPanel(new FlowLayout());
        RoundJButton saveBtn = new RoundJButton("Salvar"); // botão de salvar
        RoundJButton cancelBtn = new RoundJButton("Cancelar"); // botão de cancelar
        formButtonsContainer.add(cancelBtn);
        formButtonsContainer.add(saveBtn);
        formButtonsContainer.setBackground(Color.WHITE);
        gbc.gridy = 2;
        formContainer.add(formButtonsContainer, gbc);
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

        // onclick saveBtn
        saveBtn.addActionListener(e -> {
            StatementController statementController = new StatementController();
            String title = ((RoundJTextField) ((JPanel) formContainer.getComponent(0)).getComponent(1)).getText();
            String message = ((RoundJTextArea) ((JScrollPane) ((JPanel) formContainer.getComponent(1)).getComponent(1)).getViewport().getView()).getText();
            statementController.createStatement(title, message);
            System.out.println("Comunicado criado com sucesso!");
        });

        // onclick cancelBtn
        cancelBtn.addActionListener(e -> {
            // Implementar a lógica de cancelar a criação do comunicado
            System.out.println("Criação de comunicado cancelada!");
        });

        // Exibe a janela
        setVisible(true);
    }

    private JPanel getInputContainer(String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);

        container.setBackground(Color.WHITE);
        RoundJTextField input = new RoundJTextField(1, 10);
        container.add(inputLabel, BorderLayout.NORTH);
        container.add(input, BorderLayout.CENTER);

        return container;
    }
    private JPanel getTextArea(String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);

        container.setBackground(Color.WHITE);
        RoundJTextArea textArea = new RoundJTextArea(10,20, 10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        container.add(inputLabel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        return container;
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(NewStatement::new);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Novo Comunicado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new NewStatement());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
