package dao;

import model.Professor;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfessorDAO {

    // =========================
    // CADASTRAR PROFESSOR
    // =========================
    public void cadastrar(Professor professor) {

        String sql = "INSERT INTO professor (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getSenha());

            stmt.executeUpdate();
            System.out.println("Professor cadastrado!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar professor", e);
        }
    }

    // =========================
    // BUSCAR POR EMAIL
    // =========================
    public Professor buscarPorEmail(String email) {

        String sql = "SELECT * FROM professor WHERE email = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(rs.getInt("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getString("senha"));
                return professor;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar professor", e);
        }
    }

    // =========================
    // AUTENTICAR (LOGIN)
    // =========================
    public Professor autenticar(String email, String senha) {

        String sql = "SELECT * FROM professor WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Professor professor = new Professor();
                professor.setIdProfessor(rs.getInt("id_professor"));
                professor.setNome(rs.getString("nome"));
                professor.setEmail(rs.getString("email"));
                professor.setSenha(rs.getString("senha"));
                return professor;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autenticar professor", e);
        }
    }
}
