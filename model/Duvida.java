package model;

import java.sql.Timestamp;

public class Duvida {

    private int idDuvida;
    private int idAluno;
    private Integer idProfessor; 
    private String titulo;
    private String descricao;
    private String prioridade;
    private String statusAtendimento;
    private Timestamp dataCriacao;
    private Timestamp dataResolucao;
    private String resposta;

    // GETTERS
    public int getIdDuvida() {
        return idDuvida;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getStatusAtendimento() {
        return statusAtendimento;
    }
    public String getResposta() { 
        return resposta; 
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public Timestamp getDataResolucao() {
        return dataResolucao;
    }

    // SETTERS
    public void setIdDuvida(int idDuvida) {
        this.idDuvida = idDuvida;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdProfessor(Integer idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatusAtendimento(String statusAtendimento) {
        this.statusAtendimento = statusAtendimento;
    }
    
    public void setResposta(String resposta) { 
        this.resposta = resposta; 
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataResolucao(Timestamp dataResolucao) {
        this.dataResolucao = dataResolucao;
    }
    
}
