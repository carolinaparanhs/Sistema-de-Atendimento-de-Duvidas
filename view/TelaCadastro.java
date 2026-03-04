package view;

import controller.AlunoController;
import controller.ProfessorController;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {

    private final JComboBox<String> cbPerfil = new JComboBox<>(new String[]{"Aluno", "Professor"});
    private final JTextField txtNome = new JTextField();
    private final JTextField txtEmail = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();

    private final AlunoController alunoController = new AlunoController();
    private final ProfessorController professorController = new ProfessorController();

    public TelaCadastro() {
        setTitle("Cadastro - Sistema de Dúvidas");
        setSize(460, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Criar Conta", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Perfil:"), c);
        c.gridx = 1; c.gridy = 0; form.add(cbPerfil, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Nome:"), c);
        c.gridx = 1; c.gridy = 1; txtNome.setColumns(22); form.add(txtNome, c);

        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Email:"), c);
        c.gridx = 1; c.gridy = 2; form.add(txtEmail, c);

        c.gridx = 0; c.gridy = 3; form.add(new JLabel("Senha:"), c);
        c.gridx = 1; c.gridy = 3; form.add(txtSenha, c);

        JButton btnSalvar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar pro Login");

        btnSalvar.addActionListener(e -> cadastrar());
        btnVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnVoltar);
        botoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void cadastrar() {
        try {
            String perfil = (String) cbPerfil.getSelectedItem();
            String nome = txtNome.getText().trim();
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha nome, email e senha.");
                return;
            }

            if ("Aluno".equals(perfil)) {
                alunoController.cadastrarAluno(nome, email, senha);
            } else {
                professorController.cadastrarProfessor(nome, email, senha);
            }

            JOptionPane.showMessageDialog(this, "✅ Cadastro realizado! Faça login.");

            new TelaLogin().setVisible(true);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}