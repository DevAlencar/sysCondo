package org.sysCondo.views;

import org.sysCondo.components.RoundJButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class CommonAreasMaintenenceRequests extends JPanel {
    private final JFrame parentFrame; // precisa do parentFrame pra conseguir renderizar um dialog em tela
    public CommonAreasMaintenenceRequests (JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel contentContainer = new JPanel();
        contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
        contentContainer.setBackground(Color.WHITE);
        contentContainer.setBorder(new EmptyBorder(30, 0, 30, 0));
        add(contentContainer, BorderLayout.CENTER);

        JLabel contentTitle = new JLabel("Solicitações de manutenção", JLabel.CENTER);
        contentTitle.setFont(new Font("Roboto", Font.BOLD, 28));
        contentTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentContainer.add(contentTitle);

        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.WHITE);
        cardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centralizar cardsPanel

        // Criar e adicionar alguns cards de exemplo
        List<CommonAreasMaintenenceRequests.CardData> cardDataList = createDummyCardData();
        for (CommonAreasMaintenenceRequests.CardData cardData : cardDataList) {
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

    private JPanel createCard(CommonAreasMaintenenceRequests.CardData cardData) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        Border outsideBorder = new LineBorder(Color.BLACK, 1, true);
        Border padding = new EmptyBorder(20,25,20,25);
        card.setBorder(BorderFactory.createCompoundBorder(outsideBorder, padding));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(750, 180)); // Ajuste o tamanho máximo do card
        card.setPreferredSize(new Dimension(750, 180));

        card.addMouseListener(new MouseAdapter() {
            @Override
           public void mouseClicked(MouseEvent e) {
                // se o card possuir status em progresso ou concluida, o user pode acessar os custos associados, caso contrario, recebe mensagem de alerta
                if (!cardData.status.equals("Aguardando aprovação") && !cardData.status.equals("Cancelada")) {
                    showCardDetailsDialog(parentFrame, cardData);
                } else {
                    showMessageDialog(null, "Somente solicitações com status \"Em progresso\" e \"Concluída\" podem ser acessadas.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
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

        JLabel titleLabel = new JLabel(cardData.commonArea);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel statusLabel = new JLabel(cardData.status);
        switch (cardData.status) {
            case "Concluída":
                statusLabel.setForeground(new Color(21, 158, 57));
                break;
            case "Em progresso":
                statusLabel.setForeground(new Color(199, 183, 12));
                break;
            case "Cancelada":
                statusLabel.setForeground(Color.RED);
                break;
        }
        JLabel maintenenceLabel = new JLabel("<html><b>Manutenção:</b> " + cardData.maintenence + "</html>");
        maintenenceLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        statusLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        JLabel dateLabel = new JLabel("<html><b>Solicitada em:</b> " + cardData.date + "</html>");
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(statusLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(maintenenceLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(dateLabel);

        return card;
    }

    private List<CommonAreasMaintenenceRequests.CardData> createDummyCardData() { // método que cria uma lista de exemplo para as solicitações de manutenção
        List<CommonAreasMaintenenceRequests.CardData> data = new ArrayList<>();
        data.add(new CommonAreasMaintenenceRequests.CardData("Salão de Festas", "Limpeza e reparo do ar-condicionado", "Em progresso", "10/05/2023",
                Arrays.asList(new Cost("Mão de obra", 200.00), new Cost("Peças de reposição", 150.00))));
        data.add(new CommonAreasMaintenenceRequests.CardData("Jardim", "Poda de árvores e substituição de plantas", "Concluída", "15/06/2023",
                Arrays.asList(new Cost("Mão de obra", 100.00), new Cost("Novas plantas", 75.00))));
        data.add(new CommonAreasMaintenenceRequests.CardData("Piscina", "Tratamento da água e manutenção da borda", "Aguardando aprovação", "20/07/2023",
                Arrays.asList(new Cost("Produtos químicos", 50.00), new Cost("Limpeza", 80.00))));

        return data;
    }

    private static void showCardDetailsDialog(JFrame parentFrame, CommonAreasMaintenenceRequests.CardData cardData) {
        JDialog dialog = new JDialog(parentFrame, cardData.commonArea, true);
        dialog.setSize(600, 600);

        // Painel principal que conterá todos os componentes
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Define o padding do painel principal
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // 10 pixels de padding em cada lado

        // Componentes
        JLabel titleLabel = new JLabel(cardData.commonArea);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        JLabel maintenenceLabel = new JLabel("<html><b>Manutenção:</b> " + cardData.maintenence + "</html>");
        maintenenceLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        JLabel statusLabel = new JLabel(cardData.status);
        switch (cardData.status) {
            case "Concluída":
                statusLabel.setForeground(new Color(21, 158, 57));
                break;
            case "Em progresso":
                statusLabel.setForeground(new Color(199, 183, 12));
                break;
            case "Cancelada":
                statusLabel.setForeground(Color.RED);
                break;
        }
        statusLabel.setFont(new Font("Roboto", Font.BOLD, 15));
        JLabel dateLabel = new JLabel("<html><b>Solicitada em:</b> " + cardData.date + "</html>");
        dateLabel.setFont(new Font("Roboto", Font.PLAIN, 15));

        // Configurando a tabela de custos
        JLabel costsLabel = new JLabel("Custos");
        costsLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        String[] columnNames = {"Título", "Valor"};
        Object[][] data = cardData.getCosts().stream()
                .map(cost -> new Object[]{cost.getTitle(), cost.getValue()})
                .toArray(Object[][]::new);
        JTable costsTable = new JTable(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };
        costsTable.setFillsViewportHeight(true);
        // Adiciona a tabela em um JScrollPane para permitir rolagem
        JScrollPane tableScrollPane = new JScrollPane(costsTable);

        // Adiciona os componentes ao painel com espaçamento entre eles
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(statusLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(maintenenceLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(dateLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(costsLabel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(tableScrollPane);

        dialog.getContentPane().add(mainPanel);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }

    private static class CardData {
        String commonArea;
        String maintenence;
        String status;
        String date;
        List<Cost> costs;

        CardData(String commonArea, String maintenence, String status, String date, List<Cost> costs) {
            this.commonArea = commonArea;
            this.maintenence = maintenence;
            this.status = status;
            this.date = date;
            this.costs = costs;
        }

        public List<Cost> getCosts() {
            return costs;
        }
    }

    private static class Cost {
        String title;
        double value;

        Cost(String title, double value) {
            this.title = title;
            this.value = value;
        }

        public String getTitle() {
            return title;
        }

        public String getValue() {
            return "R$ " + value;
        }
    }
}
