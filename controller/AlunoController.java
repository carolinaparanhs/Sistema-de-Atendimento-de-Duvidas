package controller;

import dao.AlunoDAO;
import model.Aluno;

public class AlunoController {

    private final AlunoDAO alunoDAO = new AlunoDAO();

    public void cadastrarAluno(String nome, String email, String senha) {

        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setSenha(senha);

        alunoDAO.cadastrar(aluno);
    }

    // útil pra login/validação
    public Aluno buscarAlunoPorEmail(String email) {
        return alunoDAO.buscarPorEmail(email);
    }
    public Aluno loginAluno(String email, String senha) {
    return alunoDAO.autenticar(email, senha);
    }

}
