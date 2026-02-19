package view;

import controller.DuvidaController;
import model.Aluno;
import model.Duvida;


import javax.swing.*;
import java.awt.*;

public class TelaAluno extends JFrame {

    private final DuvidaController controller = new DuvidaController();
    private final Aluno alunoLogado;

    private final JTextField txtTitulo = new JTextField();
    private final JTextArea txtDescricao = new JTextArea(4, 20);
    private final JComboBox<String> cbPrioridade = new JComboBox<>(new String[]{"Alta", "Media", "Baixa"});

    private final DuvidaTableModel tableModel = new DuvidaTableModel();
    private final JTable tabela = new JTable(tableModel);

    //recebe o aluno logado
    public TelaAluno(Aluno aluno) {
        this.alunoLogado = aluno;

        setTitle("Área do Aluno - " + alunoLogado.getNome());
        setSize(900, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel topo = new JLabel("Aluno - Criar Dúvida e Acompanhar", SwingConstants.CENTER);
        topo.setFont(new Font("Arial", Font.BOLD, 18));

        // Mostra quem está logado
        JLabel lblLogado = new JLabel("Logado como: " + alunoLogado.getNome() + " (" + alunoLogado.getEmail() + ")");

        // ====== Formulário ======
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Nova Dúvida"));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Título:"), c);
        c.gridx = 1; c.gridy = 0; form.add(txtTitulo, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Prioridade:"), c);
        c.gridx = 1; c.gridy = 1; form.add(cbPrioridade, c);

        c.gridx = 0; c.gridy = 2; c.anchor = GridBagConstraints.NORTHWEST;
        form.add(new JLabel("Descrição:"), c);
        c.gridx = 1; c.gridy = 2; c.fill = GridBagConstraints.BOTH;
        JScrollPane spDesc = new JScrollPane(txtDescricao);
        form.add(spDesc, c);

        // ====== Botões ======
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnAtualizar = new JButton("Atualizar Lista");
        JButton btnVerResposta = new JButton("Ver Resposta");
        JButton btnVoltar = new JButton("Voltar");
        
        

        btnCadastrar.addActionListener(e -> cadastrar());
        btnAtualizar.addActionListener(e -> carregarTabela());
        btnVerResposta.addActionListener(e -> verRespostaSelecionada());
        btnVoltar.addActionListener(e -> { new TelaLogin().setVisible(true); dispose(); });
        


        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnCadastrar);
        botoes.add(btnAtualizar);
        botoes.add(btnVerResposta);
        botoes.add(btnVoltar);

        // ====== Tabela ======
        tabela.setRowHeight(24);
        JScrollPane spTabela = new JScrollPane(tabela);
        spTabela.setBorder(BorderFactory.createTitledBorder("Dúvidas"));

        // Layout geral
        JPanel topoPanel = new JPanel(new BorderLayout());
        topoPanel.add(topo, BorderLayout.NORTH);
        topoPanel.add(lblLogado, BorderLayout.SOUTH);

        JPanel esquerda = new JPanel(new BorderLayout());
        esquerda.add(form, BorderLayout.CENTER);
        esquerda.add(botoes, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, esquerda, spTabela);
        split.setDividerLocation(360);

        setLayout(new BorderLayout());
        add(topoPanel, BorderLayout.NORTH);
        add(split, BorderLayout.CENTER);

        carregarTabela();
    }

    private void cadastrar() {
        try {
            int idAluno = alunoLogado.getIdAluno(); // pega do login
            String titulo = txtTitulo.getText().trim();
            String desc = txtDescricao.getText().trim();
            String prioridade = (String) cbPrioridade.getSelectedItem();

            if (titulo.isBlank() || desc.isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha título e descrição.");
                return;
            }

            controller.criarDuvida(idAluno, titulo, desc, prioridade);
            JOptionPane.showMessageDialog(this, "✅ Dúvida cadastrada!");

            txtTitulo.setText("");
            txtDescricao.setText("");

            carregarTabela();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    private void carregarTabela() {
    try {
        tableModel.setDados(controller.listarPorAluno(alunoLogado.getIdAluno()));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Erro ao carregar tabela: " + ex.getMessage());
    }
}

    private void verRespostaSelecionada() {
    int row = tabela.getSelectedRow();
    Duvida d = tableModel.getDuvidaAt(row);

    if (d == null) {
        JOptionPane.showMessageDialog(this, "Selecione uma dúvida na tabela.");
        return;
    }

    String resposta = d.getResposta();
    if (resposta == null || resposta.isBlank()) {
        JOptionPane.showMessageDialog(this, "Ainda não há resposta para esta dúvida.");
        return;
    }

    JTextArea area = new JTextArea(resposta, 10, 40);
    area.setLineWrap(true);
    area.setWrapStyleWord(true);
    area.setEditable(false);

    JOptionPane.showMessageDialog(this, new JScrollPane(area),
            "Resposta do Professor", JOptionPane.INFORMATION_MESSAGE);
}

}
