package org.sysCondo.views;

import org.sysCondo.components.Cost;
import org.sysCondo.components.Maintenence;
import org.sysCondo.components.RoundJButton;
import org.sysCondo.components.RoundJTextField;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class CommonAreasMaintenenceOverview extends JPanel {
    private JTable manutencoesTable;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private static List<Maintenence> todasManutencoes = new ArrayList<>();

    static {
        List<Cost> custosPiscina = new ArrayList<>();
        todasManutencoes.add(new Maintenence("Piscina", "Limpeza", "João Silva", "Aceita", custosPiscina, ""));

        List<Cost> custosSalaoFestas = new ArrayList<>();
        todasManutencoes.add(new Maintenence("Salão de Festas", "Reparo de Ar Condicionado", "Maria Oliveira", "Pendente", custosSalaoFestas, ""));

    }


    public CommonAreasMaintenenceOverview() {
        setLayout(new BorderLayout());

        // Painel de Cabeçalho
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("SOLICITAÇÕES DE MANUTENÇÃO", JLabel.CENTER);
        titleLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.setBackground(Color.WHITE);

        // Painel de Controle com Campo de Busca e Botões de Ações
        JPanel controlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new RoundJTextField(20, 10);
        searchField.setToolTipText("Buscar solicitação...");
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                filterTable(query);
            }
        });

        Font labelFont = new Font("Roboto Medium", Font.PLAIN, 14);

        JLabel searchLabel = new JLabel("Pesquisar por:");
        controlsPanel.add(searchLabel);
        searchLabel.setFont(labelFont);
        controlsPanel.add(searchField);
        controlsPanel.setBackground(new Color(202, 202, 202));

        // Botões para ações
        JButton btnAddCost = new RoundJButton("Adicionar Custo");
        btnAddCost.addActionListener(e -> adicionarCusto());
        controlsPanel.add(btnAddCost);

        JButton btnChangeStatus = new RoundJButton("Alterar Status");
        btnChangeStatus.addActionListener(e -> alterarStatus());
        controlsPanel.add(btnChangeStatus);

        JButton btnRefuse = new RoundJButton("Recusar Solicitação");
        btnRefuse.addActionListener(e -> recusarSolicitacao());
        controlsPanel.add(btnRefuse);

        headerPanel.add(controlsPanel, BorderLayout.SOUTH);
        add(headerPanel, BorderLayout.NORTH);

        searchField.setFont(labelFont); // Alterar a fonte do campo de texto

        // Configuração da Tabela
        // Configuração da Tabela
        tableModel = new DefaultTableModel(new String[]{"Área", "Tipo", "Solicitante", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilita a edição de qualquer célula
            }
        };

        manutencoesTable = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        manutencoesTable.setRowSorter(sorter);

        manutencoesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Verifica se é um duplo clique
                    // Se a célula estiver sendo editada, cancela a edição
                    if (manutencoesTable.isEditing()) {
                        manutencoesTable.getCellEditor().cancelCellEditing();
                    }

                    int selectedRow = manutencoesTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Obtém a área correspondente à linha selecionada
                        String area = (String) manutencoesTable.getValueAt(selectedRow, 0);
                        Maintenence manutencao = encontrarManutencao(area);

                        if (manutencao != null) {
                            // Cria a mensagem a ser exibida na caixa de diálogo
                            StringBuilder message = new StringBuilder();
                            message.append("Área: ").append(manutencao.getArea()).append("\n")
                                    .append("Tipo: ").append(manutencao.getTipo()).append("\n")
                                    .append("Solicitante: ").append(manutencao.getSolicitante()).append("\n")
                                    .append("Status: ").append(manutencao.getStatus()).append("\n");

                            if (!manutencao.getCustos().isEmpty()) {
                                message.append("Custos:\n");
                                for (Cost custo : manutencao.getCustos()) {
                                    message.append("  - Valor: R$").append(custo.getValor())
                                            .append(", Detalhes: ").append(custo.getDescricao()).append("\n");
                                }
                            } else {
                                message.append("Custos: Nenhum\n");
                            }

                            if ("Recusada".equalsIgnoreCase(manutencao.getStatus())) {
                                message.append("Motivo da Recusa: ").append(manutencao.getMotivoRecusa()).append("\n");
                            }

                            // Exibe a caixa de diálogo com as informações
                            JOptionPane.showMessageDialog(
                                    CommonAreasMaintenenceOverview.this,
                                    message.toString(),
                                    "Detalhes da Manutenção",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                }
            }
        });

        customizeTable();

        JScrollPane scrollPane = new JScrollPane(manutencoesTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane, BorderLayout.CENTER);

        updateTable(); // Preenche a tabela com dados iniciais
    }

    private void customizeTable() {
        manutencoesTable.setRowHeight(30);
        manutencoesTable.setFont(new Font("Roboto", Font.PLAIN, 14));
        manutencoesTable.getTableHeader().setFont(new Font("Roboto Bold", Font.PLAIN, 16));
        manutencoesTable.getTableHeader().setBackground(new Color(0, 132, 96));
        manutencoesTable.getTableHeader().setForeground(Color.WHITE);
        manutencoesTable.setFillsViewportHeight(true);
        manutencoesTable.setGridColor(new Color(230, 230, 230));
        manutencoesTable.setShowGrid(true);
        manutencoesTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));

        // Custom cell renderer for alternating row colors
        manutencoesTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(245, 245, 245));
                } else {
                    c.setBackground(Color.WHITE);
                }
                if (isSelected) {
                    c.setBackground(new Color(0, 123, 255));
                    c.setForeground(Color.WHITE);
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

    }

    private void filterTable(String query) {
        if (sorter != null) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }

    public void updateTable() {
        tableModel.setRowCount(0); // Limpa a tabela antes de atualizar
        for (Maintenence manutencao : todasManutencoes) {
            tableModel.addRow(new Object[]{
                    manutencao.getArea(),
                    manutencao.getTipo(),
                    manutencao.getSolicitante(),
                    manutencao.getStatus()
            });
        }
        tableModel.fireTableDataChanged();
    }

    private void adicionarCusto() {
        int selectedRow = manutencoesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String area = (String) manutencoesTable.getValueAt(selectedRow, 0);
            Maintenence manutencao = encontrarManutencao(area);
            if (manutencao != null && manutencao.getStatus().equalsIgnoreCase("Aceita")) {
                JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

                JTextField custoField = new JTextField();
                JTextField detalhesField = new JTextField();

                panel.add(new JLabel("Digite o custo adicional:"));
                panel.add(custoField);
                panel.add(new JLabel("Insira os detalhes do custo:"));
                panel.add(detalhesField);

                int result = JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Adicionar Custo",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        double custo = Double.parseDouble(custoField.getText());
                        String detalhes = detalhesField.getText();

                        manutencao.addCusto(new Cost(custo, detalhes));

                        // Debug prints
                        System.out.println("Custo adicionado: " + custo);
                        System.out.println("Detalhes: " + detalhes);
                        System.out.println("Manutenção atualizada: " + manutencao);

                        JOptionPane.showMessageDialog(this, "Custo adicionado com sucesso!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(this, "Valor inválido para o custo!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma manutenção com status 'Aceita'!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção!");
        }
    }

    private void alterarStatus() {
        int selectedRow = manutencoesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String area = (String) manutencoesTable.getValueAt(selectedRow, 0);
            Maintenence manutencao = encontrarManutencao(area);
            if (manutencao != null) {
                String novoStatus = JOptionPane.showInputDialog(this, "Digite o novo status:");

                if (novoStatus != null && !novoStatus.isEmpty()) {
                    manutencao.setStatus(novoStatus);

                    // Debug prints
                    System.out.println("Status alterado para: " + novoStatus);
                    System.out.println("Manutenção atualizada: " + manutencao);

                    updateTable();
                    JOptionPane.showMessageDialog(this, "Status atualizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Status inválido!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção!");
        }
    }

    private void recusarSolicitacao() {
        int selectedRow = manutencoesTable.getSelectedRow();
        if (selectedRow >= 0) {
            String area = (String) manutencoesTable.getValueAt(selectedRow, 0);
            Maintenence manutencao = encontrarManutencao(area);
            if (manutencao != null) {
                String motivo = JOptionPane.showInputDialog(this, "Digite o motivo da recusa:");

                if (motivo != null && !motivo.isEmpty()) {
                    manutencao.setStatus("Recusada");
                    manutencao.setMotivoRecusa(motivo);

                    // Debug prints
                    System.out.println("Solicitação recusada: " + motivo);
                    System.out.println("Manutenção atualizada: " + manutencao);

                    updateTable();
                    JOptionPane.showMessageDialog(this, "Solicitação recusada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Motivo da recusa não pode ser vazio!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma manutenção!");
        }
    }

    private Maintenence encontrarManutencao(String area) {
        for (Maintenence manutencao : todasManutencoes) {
            if (manutencao.getArea().equalsIgnoreCase(area)) {
                return manutencao;
            }
        }
        return null;
    }
}