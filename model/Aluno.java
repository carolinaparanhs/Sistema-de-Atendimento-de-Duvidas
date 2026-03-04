package model;

public class Aluno extends Usuario {

    @Override
    public String getTipoUsuario() {
        return "Aluno";
    }

    public int getIdAluno() {
        return id;
    }

    public void setIdAluno(int id) {
        this.id = id;
    }
}