package view;

import controller.DuvidaController;
import model.Duvida;
import model.Professor;

import javax.swing.*;
import java.awt.*;

public class TelaProfessor extends JFrame {

    private final DuvidaController controller = new DuvidaController();
    private final Professor professorLogado;

    private final DuvidaTableModel tableModel = new DuvidaTableModel();
    private final JTable tabela = new JTable(tableModel);

    
    public TelaProfessor(Professor professor) {
        this.professorLogado = professor;

        setTitle("Área do Professor - " + professorLogado.getNome());
        setSize(900, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel topo = new JLabel("Professor - Assumir e Resolver Dúvidas", SwingConstants.CENTER);
        topo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblLogado = new JLabel("Logado como: " + professorLogado.getNome() + " (" + professorLogado.getEmail() + ")");

        // Barra inferior com botões
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));

        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnAssumir = new JButton("Assumir Selecionada");
        JButton btnResolver = new JButton("Resolver Selecionada");
        JButton btnDetalhar = new JButton("Detalhar");
        JButton btnVoltar = new JButton("Sair");

        btnAtualizar.addActionListener(e -> carregarTabela());
        btnAssumir.addActionListener(e -> assumirSelecionada());
        btnResolver.addActionListener(e -> resolverSelecionada());
        btnDetalhar.addActionListener(e -> detalharSelecionada());
        btnVoltar.addActionListener(e -> { new TelaLogin().setVisible(true); dispose(); });

        barra.add(btnAtualizar);
        barra.add(btnDetalhar);
        barra.add(btnAssumir);
        barra.add(btnResolver);
        barra.add(btnVoltar);

        // Tabela
        tabela.setRowHeight(24);
        JScrollPane spTabela = new JScrollPane(tabela);
        spTabela.setBorder(BorderFactory.createTitledBorder("Dúvidas"));

        // Topo com título e logado
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(topo, BorderLayout.NORTH);
        painelTopo.add(lblLogado, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(spTabela, BorderLayout.CENTER);
        add(barra, BorderLayout.SOUTH);

        carregarTabela();
    }

    private void carregarTabela() {
        try {
            tableModel.setDados(controller.listarTodas());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar tabela: " + ex.getMessage());
        }
    }

    private void assumirSelecionada() {
        try {
            int row = tabela.getSelectedRow();
            Duvida d = tableModel.getDuvidaAt(row);

            if (d == null) {
                JOptionPane.showMessageDialog(this, "Selecione uma dúvida na tabela.");
                return;
            }

            int idProfessor = professorLogado.getIdProfessor(); // ✅ automático
            controller.assumirDuvida(d.getIdDuvida(), idProfessor);

            JOptionPane.showMessageDialog(this, "✅ Dúvida assumida!");
            carregarTabela();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void resolverSelecionada() {
    try {
        int row = tabela.getSelectedRow();
        Duvida d = tableModel.getDuvidaAt(row);

        if (d == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma dúvida.");
            return;
        }

        String resposta = JOptionPane.showInputDialog(this, "Digite a resposta: ");

        if (resposta == null || resposta.isBlank()) {
            JOptionPane.showMessageDialog(this, "Resposta não pode estar vazia.");
            return;
        }

        controller.resolverDuvida(d.getIdDuvida(), resposta);

        JOptionPane.showMessageDialog(this, "✅ Dúvida resolvida!");
        carregarTabela();

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
    }
 }
    private void detalharSelecionada() {
    int row = tabela.getSelectedRow();
    Duvida d = tableModel.getDuvidaAt(row);

    if (d == null) {
        JOptionPane.showMessageDialog(this, "Selecione uma dúvida na tabela.");
        return;
    }

    JTextArea area = new JTextArea(14, 45);
    area.setEditable(false);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);

    String resposta = (d.getResposta() == null || d.getResposta().isBlank())
            ? "Sem resposta ainda."
            : d.getResposta();

    area.setText(
            "ALUNO (ID): " + d.getIdAluno() + "\n" +
            "PROFESSOR (ID): " + d.getIdProfessor() + "\n\n" +
            "TÍTULO:\n" + d.getTitulo() + "\n\n" +
            "DESCRIÇÃO:\n" + d.getDescricao() + "\n\n" +
            "RESPOSTA:\n" + resposta + "\n\n" +
            "STATUS: " + d.getStatusAtendimento() + " | PRIORIDADE: " + d.getPrioridade()
    );

    JOptionPane.showMessageDialog(this, new JScrollPane(area),
            "Detalhes da Dúvida (ID: " + d.getIdDuvida() + ")",
            JOptionPane.INFORMATION_MESSAGE);
}

}