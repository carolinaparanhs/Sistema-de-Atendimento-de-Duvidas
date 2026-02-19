package view;

import controller.AlunoController;
import controller.ProfessorController;
import model.Aluno;
import model.Professor;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private final JComboBox<String> cbPerfil = new JComboBox<>(new String[]{"Aluno", "Professor"});
    private final JTextField txtEmail = new JTextField();
    private final JPasswordField txtSenha = new JPasswordField();

    private final AlunoController alunoController = new AlunoController();
    private final ProfessorController professorController = new ProfessorController();

    public TelaLogin() {
        setTitle("Login - Sistema de Dúvidas");
        setSize(420, 260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Sistema de Dúvidas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; form.add(new JLabel("Perfil:"), c);
        c.gridx = 1; c.gridy = 0; form.add(cbPerfil, c);

        c.gridx = 0; c.gridy = 1; form.add(new JLabel("Email:"), c);
        c.gridx = 1; c.gridy = 1; txtEmail.setColumns(20); form.add(txtEmail, c);

        c.gridx = 0; c.gridy = 2; form.add(new JLabel("Senha:"), c);
        c.gridx = 1; c.gridy = 2; form.add(txtSenha, c);

        JButton btnEntrar = new JButton("Entrar");
        JButton btnCadastrar = new JButton("Cadastrar");

        btnEntrar.addActionListener(e -> entrar());
        btnCadastrar.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnCadastrar);
        botoes.add(btnEntrar);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);
    }

    private void entrar() {
        try {
            String perfil = (String) cbPerfil.getSelectedItem();
            String email = txtEmail.getText().trim();
            String senha = new String(txtSenha.getPassword()).trim();

            if (email.isBlank() || senha.isBlank()) {
                JOptionPane.showMessageDialog(this, "Preencha email e senha.");
                return;
            }

            if ("Aluno".equals(perfil)) {
                Aluno aluno = alunoController.loginAluno(email, senha);
                if (aluno == null) {
                    JOptionPane.showMessageDialog(this, "Email ou senha inválidos!");
                    return;
                }
                new TelaAluno(aluno).setVisible(true);
                dispose();
            } else {
               Professor professor = professorController.loginProfessor(email, senha);
                if (professor == null) {
                    JOptionPane.showMessageDialog(this, "Email ou senha inválidos!");
                    return;
                }
                new TelaProfessor(professor).setVisible(true);
                dispose();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }
}
