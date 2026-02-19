package controller;

import dao.ProfessorDAO;
import model.Professor;

public class ProfessorController {

    private final ProfessorDAO professorDAO = new ProfessorDAO();

    // Cadastro de professor
    public void cadastrarProfessor(String nome, String email, String senha) {

        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setEmail(email);
        professor.setSenha(senha);

        professorDAO.cadastrar(professor);
    }

    // Buscar professor por email (caso queira usar)
    public Professor buscarProfessorPorEmail(String email) {
        return professorDAO.buscarPorEmail(email);
    }

    // Login do professor
    public Professor loginProfessor(String email, String senha) {
        return professorDAO.autenticar(email, senha);
    }
}
