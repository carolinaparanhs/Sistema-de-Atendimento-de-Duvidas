package controller;

import dao.DuvidaDAO;
import model.Duvida;

import java.util.List;

public class DuvidaController {

    private final DuvidaDAO duvidaDAO = new DuvidaDAO();

    public void criarDuvida(int idAluno, String titulo, String descricao, String prioridade) {
        Duvida d = new Duvida();
        d.setIdAluno(idAluno);
        d.setTitulo(titulo);
        d.setDescricao(descricao);
        d.setPrioridade(prioridade);
        duvidaDAO.cadastrar(d);
    }

    public List<Duvida> listarTodas() {
        return duvidaDAO.listarTodas();
    }
    public List<Duvida> listarPorAluno(int idAluno) {
        return duvidaDAO.listarPorAluno(idAluno);
    }
    public void assumirDuvida(int idDuvida, int idProfessor) {
        duvidaDAO.assumirDuvida(idDuvida, idProfessor);
    }
    public void resolverDuvida(int idDuvida, String resposta) {
    duvidaDAO.resolverDuvida(idDuvida, resposta);
    }
    public void excluirDuvida(int idDuvida){
    duvidaDAO.excluirDuvida(idDuvida);
    }
}