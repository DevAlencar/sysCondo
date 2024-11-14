package org.sysCondo.views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Statements extends JPanel {
    JFrame parentFrame;
    public Statements(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer, BorderLayout.CENTER);

        JLabel contentTitle = new JLabel("Comunicados", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentContainer.add(contentTitle);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar cardsPanel

        // Criar e adicionar alguns cards de exemplo
        List<CardData> cardDataList = createDummyCardData();
        for (CardData cardData : cardDataList) {
            JPanel card = createCard(cardData);
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

    private JPanel createCard(CardData cardData) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        Border outsideBorder = new LineBorder(Color.BLACK, 1, true);
        Border padding = new EmptyBorder(20,25,20,25);
        card.setBorder(BorderFactory.createCompoundBorder(outsideBorder, padding));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(750, 150)); // Ajuste o tamanho máximo do card
        card.setPreferredSize(new Dimension(750, 150));

        JLabel titleLabel = new JLabel(cardData.title);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel authorLabel = new JLabel("Autor: " + cardData.author);
        authorLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel dateLabel = new JLabel("Criado em: " + cardData.date);
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Quando clicado, mostra a descrição do card
                showCardDetailsDialog(parentFrame, cardData);
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
        card.add(authorLabel);
        card.add(Box.createVerticalStrut(10)); // Gap entre o título e o autor
        card.add(dateLabel);

        return card;
    }

    private List<CardData> createDummyCardData() {
        List<CardData> data = new ArrayList<>();
        data.add(new CardData("Reunião do Conselho de Moradores", "Joana Presidente", "10/05/2023", "Teremos uma reunião do conselho nesta quinta-feira para discutir melhorias no salão de festas."));
        data.add(new CardData("Aulas de Yoga no Jardim", "Carlos Instrutor", "15/06/2023", "Começando na próxima segunda, aulas de yoga gratuitas para os moradores. Tragam suas esteiras!"));
        data.add(new CardData("Novo Horário da Piscina", "Administração", "20/07/2023", "A piscina agora estará aberta das 6h às 22h todos os dias. Aproveitem o horário estendido!"));
        data.add(new CardData("Encontrado Gato Perdido", "Maria da Portaria", "22/05/2023", "Um gato cinza foi encontrado nas áreas comuns. Por favor, entre em contato para recuperar seu pet."));
        data.add(new CardData("Feira de Artesanato no Salão", "Associação de Moradores", "05/08/2023", "Feira de artesanato este sábado no salão principal. Venha prestigiar os talentos locais!"));
        data.add(new CardData("Campeonato de Futebol no Campo", "Equipe Esportiva", "30/09/2023", "As inscrições para o campeonato de futebol do condomínio estão abertas. Participe e divirta-se!"));
        data.add(new CardData("Novas Regras de Estacionamento", "Segurança", "01/10/2023", "Verifique as novas regras de estacionamento para evitar multas e garantir a organização de todos."));
        data.add(new CardData("Plantio Comunitário de Árvores", "Verde Vivo", "12/06/2023", "Participe do plantio de árvores no nosso espaço verde. Juntos por um ambiente mais saudável!"));
        data.add(new CardData("Concurso de Fotografia do Condomínio", "Comissão de Eventos", "08/07/2023", "Envie suas fotos favoritas do condomínio para participar do nosso concurso fotográfico."));
        data.add(new CardData("Desconto em Academias Locais", "Administração", "18/08/2023", "Moradores têm desconto exclusivo em academias próximas. Garanta sua saúde com uma oferta imperdível!"));

        return data;
    }

    // Método para mostrar o diálogo modal com as informações do card
    private static void showCardDetailsDialog(JFrame parentFrame, CardData cardData) {
        JDialog dialog = new JDialog(parentFrame, cardData.title, true);
        dialog.setSize(600, 600);

        // Painel principal que conterá todos os componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Define o padding do painel principal
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 pixels de padding em cada lado

        // Componentes
        JLabel titleLabel = new JLabel(cardData.title);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel authorLabel = new JLabel("Autor: " + cardData.author);
        authorLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel dateLabel = new JLabel("Criado em: " + cardData.date);
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        JTextArea descriptionArea = new JTextArea(cardData.description);
        descriptionArea.setFont(new Font("Roboto", Font.PLAIN, 15));
        descriptionArea.setBorder(new EmptyBorder(10,20,10,20));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(dialog.getBackground());

        // Adiciona os componentes ao painel com espaçamento entre eles
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // 10 pixels de espaço
        mainPanel.add(authorLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // 10 pixels de espaço
        mainPanel.add(dateLabel);
        mainPanel.add(Box.createVerticalStrut(10)); // 10 pixels de espaço
        mainPanel.add(new JScrollPane(descriptionArea));

        // Adiciona o painel principal ao diálogo
        dialog.getContentPane().add(mainPanel);

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private static class CardData {
        String title;
        String author;
        String date;
        String description;

        CardData(String title, String author, String date, String description) {
            this.title = title;
            this.author = author;
            this.date = date;
            this.description = description;
        }
    }

    // main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Comunicados");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Statements(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}