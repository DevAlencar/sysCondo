package org.sysCondo.views;

import org.sysCondo.components.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class NewResident extends JFrame {
    private ContentPanel contentPanel; // Painel de conteúdo
    private SideMenu sideMenu; // Menu lateral

    public NewResident() {
        // Configurações da janela
        setTitle("Cadastrar novo morador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null); // Centraliza a janela
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Painel principal com BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Adiciona o header ao painel principal
        mainPanel.add(new Header(), BorderLayout.NORTH);

        // Barra lateral de navegação
        contentPanel = new ContentPanel(); // Inicializa o painel de conteúdo
        sideMenu = new SideMenu(contentPanel.getContentPanel()); // Passa o painel de conteúdo
        mainPanel.add(sideMenu.getSideMenuPanel(), BorderLayout.WEST);

        JPanel contentContainer = new JPanel(new BorderLayout());
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));

        JLabel contentTitle = new JLabel("Cadastro de Moradores", JLabel.CENTER);
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

        gbc.gridy = 0;
        formContainer.add(getInputContainer("Nome do Morador"), gbc);
        gbc.gridy = 1;
        formContainer.add(getInputContainer("CPF"), gbc);
        gbc.gridy = 2;
        formContainer.add(getComboBoxContainer("Unidade Associada", new String[]{"Unidade 1", "Unidade 2", "Unidade 3"}), gbc);
        gbc.gridy = 3;
        formContainer.add(getInputContainer("Endereço de email"), gbc);
        gbc.gridy = 4;
        formContainer.add(getInputContainer("Número de telefone"), gbc);
        gbc.gridy = 5;
        formContainer.add(getComboBoxContainer("Situação do residente", new String[]{"Proprietário", "Inquilino"}), gbc);

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
        gbc.gridy = 6;
        formContainer.add(formButtonsContainer, gbc);

        // Adiciona o painel de conteúdo
        mainPanel.add(contentPanel.setContent(contentContainer));

        // Adiciona o painel principal à janela
        add(mainPanel);

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

    private JPanel getComboBoxContainer(String label, String[] options) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel comboBoxLabel = new JLabel(label);

        container.setBackground(Color.WHITE);
        container.setPreferredSize(new Dimension(100, 100));
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
        container.add(comboBoxLabel, BorderLayout.NORTH);
        container.add(comboBox, BorderLayout.CENTER);

        return container;
    }

    // Método para acessar o painel de conteúdo (usado pela SideMenu para modificar o conteúdo)
    public JPanel getContentPanel() { // Retorna o painel em vez de ContentPanel
        return contentPanel.getContentPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NewResident::new);
    }
}
