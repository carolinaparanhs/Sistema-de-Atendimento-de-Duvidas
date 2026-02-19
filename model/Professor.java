package model;

public class Professor extends Usuario {

    @Override
    public String getTipoUsuario() {
        return "Professor";
    }

    public int getIdProfessor() {
        return id;
    }

    public void setIdProfessor(int id) {
        this.id = id;
    }
}
