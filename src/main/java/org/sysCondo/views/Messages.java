package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextArea;
import org.sysCondo.components.RoundJTextField;
import org.sysCondo.controller.StatementController;
import org.sysCondo.model.Statement;
import org.sysCondo.types.Message;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Messages extends JPanel {
    JFrame parentFrame;
    public Messages(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(30, 0, 30, 0));

        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(0, 0, 20, 0));
        add(contentContainer, BorderLayout.CENTER);

        JLabel contentTitle = new JLabel("Mensagens", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentContainer.add(contentTitle);
        RoundJButton newMessageButton = new RoundJButton("Nova mensagem");
        newMessageButton.addActionListener(e -> openNewMessageDialog());
        newMessageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        contentContainer.add(newMessageButton);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar cardsPanel

        List<Message> messages = getDummyMessages();
        for (Message message : messages) {
            JPanel card = createCard(message);
            cardsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            card.setAlignmentX(Component.CENTER_ALIGNMENT);
            cardsPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.setPreferredSize(new Dimension(800, Integer.MAX_VALUE));
        scrollPane.setMaximumSize(new Dimension(800, Integer.MAX_VALUE));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentContainer.add(scrollPane);

        setVisible(true);
    }

    private JPanel createCard(Message data) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        Border outsideBorder = new LineBorder(Color.BLACK, 1, true);
        Border padding = new EmptyBorder(20,25,20,25);
        card.setBorder(BorderFactory.createCompoundBorder(outsideBorder, padding));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(750, 150)); // Ajuste o tamanho máximo do card
        card.setPreferredSize(new Dimension(750, 150));

        JLabel titleLabel = new JLabel(data.getTitle());
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel dateLabel = new JLabel("Criado em: " + data.getDate().toString());
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Quando clicado, mostra a descrição do card
                showMessageDialog(parentFrame, data);
            }

            // Adiciona efeitos de mouse para melhorar a experiência do usuário
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                card.setBackground(Color.LIGHT_GRAY); // Muda a cor ao passar o mouse
            }

            @Override
            public void mouseExited(MouseEvent e) {
                card.setCursor(Cursor.getDefaultCursor());
                card.setBackground(Color.WHITE); // Retorna à cor original ao sair com o mouse
            }
        });

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10)); // Gap entre o título e o autor
        card.add(dateLabel);

        return card;
    }
    private List<Message> getDummyMessages() {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message(
                "Reunião de condomínio marcada",
                new Date(),
                "Informamos que a próxima reunião do condomínio será no dia 30/11 às 19h no salão de festas.",
                "Síndico Carlos"
        ));

        messages.add(new Message(
                "Manutenção agendada para a piscina",
                new Date(System.currentTimeMillis() - 86400000L), // Ontem
                "A piscina estará fechada para manutenção entre os dias 25/11 e 27/11.",
                "Equipe de Manutenção"
        ));

        messages.add(new Message(
                "Pagamento de taxa atrasado",
                new Date(System.currentTimeMillis() - 604800000L), // 7 dias atrás
                "Lembrete: sua taxa de condomínio está atrasada. Regularize sua situação até o dia 30/11 para evitar multas.",
                "Administração"
        ));

        messages.add(new Message(
                "Nova regra para uso do salão de festas",
                new Date(System.currentTimeMillis() + 86400000L), // Amanhã
                "A partir de 01/12, será necessário agendar o salão de festas com pelo menos 15 dias de antecedência.",
                "Síndico Carlos"
        ));

        messages.add(new Message(
                "Campanha de doação para funcionários",
                new Date(System.currentTimeMillis() + 604800000L), // Daqui a 7 dias
                "Participe da campanha de arrecadação de fundos para os funcionários do condomínio. Doações até 05/12.",
                "Administração"
        ));

        return messages;
    }
    private static void showMessageDialog(JFrame parentFrame, Message data) {
        JDialog dialog = new JDialog(parentFrame, data.getTitle(), true);
        dialog.setSize(600, 600);
        dialog.setMinimumSize(new Dimension(600, 600));

        // Painel principal que conterá todos os componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Define o padding do painel principal
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 pixels de padding em cada lado

        // Componentes
        JPanel messageContainer = new JPanel();
        messageContainer.setBackground(Color.WHITE);
        messageContainer.setLayout(new BoxLayout(messageContainer, BoxLayout.Y_AXIS));
        messageContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel titleLabel = new JLabel(data.getTitle());
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel dateLabel = new JLabel("Criado em: " + data.getDate());
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel authorLabel = new JLabel("Autor: " + data.getAuthor());
        authorLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        JLabel message = new JLabel(data.getText());
        message.setFont(new Font("Roboto", Font.PLAIN, 12));

        messageContainer.add(authorLabel);
        messageContainer.add(Box.createVerticalStrut(10)); // Gap entre o título e o autor
        messageContainer.add(message);
        messageContainer.add(Box.createVerticalStrut(10)); // Gap entre o título e o autor
        messageContainer.add(new RoundJButton("Responder"));

        // Adiciona os componentes ao painel com espaçamento entre eles
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // 10 pixels de espaço
        mainPanel.add(dateLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // 10 pixels de espaço
        mainPanel.add(messageContainer);

        // Adiciona o painel principal ao diálogo
        dialog.getContentPane().add(mainPanel);

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
    private void openNewMessageDialog() {
        JDialog dialog = new JDialog(parentFrame, "Nova mensagem", true);
        dialog.setSize(500, 500);
        dialog.setMinimumSize(new Dimension(500, 500));
        dialog.setMaximumSize(new Dimension(500, 500));

        // Painel principal que conterá todos os componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Define o padding do painel principal
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 pixels de padding em cada lado

        JLabel titleLabel = new JLabel("Adicionar nova mensagem", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Painel para o formulário
        JPanel formContainer = new JPanel(new GridBagLayout());
        formContainer.setBackground(Color.WHITE);
        formContainer.setPreferredSize(new Dimension(400, 450));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Adicionar campos ao formulário
        gbc.gridy = 0;
        RoundJTextField title = createAndAddInputField(formContainer, gbc, "Título da mensagem");
        gbc.gridy = 1;
        RoundJTextArea message = createAndAddTextArea(formContainer, gbc, "Mensagem");
        gbc.gridy = 2;
        RoundJButton submitMessage = new RoundJButton("Adicionar nova mensagem");
        submitMessage.addActionListener(e -> {
            // aqui vai a lógica pra submitar a mensagem
        });
        formContainer.add(submitMessage, gbc);
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        JPanel paddingBottom = new JPanel();
        paddingBottom.setBackground(Color.WHITE);
        formContainer.add(paddingBottom, gbc);

        JPanel formWrapper = new JPanel();
        formWrapper.setBackground(Color.WHITE);
        formWrapper.add(formContainer);
        mainPanel.add(formWrapper, BorderLayout.CENTER);

        // Adiciona o painel principal ao diálogo
        dialog.getContentPane().add(mainPanel);

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
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
    private RoundJTextArea createAndAddTextArea(JPanel formContainer, GridBagConstraints gbc, String label) {
        JPanel container = new JPanel(new BorderLayout());
        JLabel inputLabel = new JLabel(label);
        inputLabel.setFont(new Font("Roboto", Font.PLAIN, 14));
        container.setBackground(Color.WHITE);
        RoundJTextArea textArea = new RoundJTextArea(10,20, 10);
        textArea.setFont(new Font("Roboto", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        container.add(inputLabel, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        formContainer.add(container, gbc);

        return textArea;
    }
}
