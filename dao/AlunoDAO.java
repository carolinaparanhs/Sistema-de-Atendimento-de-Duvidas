package dao;

import model.Aluno;
import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDAO {

    public void cadastrar(Aluno aluno) {

        String sql = "INSERT INTO aluno (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getSenha());

            stmt.executeUpdate();
            System.out.println("✅ Aluno cadastrado!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar aluno", e);
        }
    }

    public Aluno buscarPorEmail(String email) {

        String sql = "SELECT * FROM aluno WHERE email = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha"));
                return aluno;
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }
    }
    public Aluno autenticar(String email, String senha) {

    String sql = "SELECT * FROM aluno WHERE email = ? AND senha = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, email);
        stmt.setString(2, senha);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setIdAluno(rs.getInt("id_aluno"));
            aluno.setNome(rs.getString("nome"));
            aluno.setEmail(rs.getString("email"));
            aluno.setSenha(rs.getString("senha"));
            return aluno;
        }

        return null;

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao autenticar aluno", e);
    }
}

}
