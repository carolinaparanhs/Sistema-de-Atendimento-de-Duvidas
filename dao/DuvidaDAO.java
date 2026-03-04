package dao;

import model.Duvida;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuvidaDAO {

    // 1) Cadastrar dúvida (Aluno)
    public void cadastrar(Duvida d) {

        String sql = """
            INSERT INTO duvida 
            (id_aluno, titulo, descricao, prioridade, status_atendimento)
            VALUES (?, ?, ?, ?, 'Aguardando')
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, d.getIdAluno());
            stmt.setString(2, d.getTitulo());
            stmt.setString(3, d.getDescricao());
            stmt.setString(4, d.getPrioridade());

            stmt.executeUpdate();
            System.out.println("✅ Dúvida cadastrada!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar dúvida", e);
        }
    }

    // 2) Listar todas (ordenadas por prioridade)
    public List<Duvida> listarTodas() {

        String sql = """
            SELECT * FROM duvida
            ORDER BY FIELD(prioridade, 'Alta','Media','Baixa'), data_criacao
        """;

        List<Duvida> lista = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Duvida d = new Duvida();
                d.setIdDuvida(rs.getInt("id_duvida"));
                d.setIdAluno(rs.getInt("id_aluno"));

                int prof = rs.getInt("id_professor");
                d.setIdProfessor(rs.wasNull() ? null : prof);

                d.setTitulo(rs.getString("titulo"));
                d.setDescricao(rs.getString("descricao"));
                d.setPrioridade(rs.getString("prioridade"));
                d.setStatusAtendimento(rs.getString("status_atendimento"));
                d.setDataCriacao(rs.getTimestamp("data_criacao"));
                d.setDataResolucao(rs.getTimestamp("data_resolucao"));
                d.setResposta(rs.getString("resposta"));


                lista.add(d);
            }
            

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar dúvidas", e);
        }

        return lista;
    }
    
    public List<Duvida> listarPorAluno(int idAluno) {

    String sql = """
        SELECT *
        FROM duvida
        WHERE id_aluno = ?
        ORDER BY data_criacao DESC
    """;

    List<Duvida> lista = new ArrayList<>();

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idAluno);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Duvida d = new Duvida();
            d.setIdDuvida(rs.getInt("id_duvida"));
            d.setIdAluno(rs.getInt("id_aluno"));

            // id_professor pode ser NULL
            Integer idProf = (Integer) rs.getObject("id_professor");
            d.setIdProfessor(idProf);

            d.setTitulo(rs.getString("titulo"));
            d.setDescricao(rs.getString("descricao"));
            d.setPrioridade(rs.getString("prioridade"));
            d.setStatusAtendimento(rs.getString("status_atendimento"));
            d.setDataCriacao(rs.getTimestamp("data_criacao"));
            d.setDataResolucao(rs.getTimestamp("data_resolucao"));

            // se você adicionou a coluna resposta:
            try { d.setResposta(rs.getString("resposta")); } catch (Exception ignored) {}

            lista.add(d);
        }

        return lista;

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar dúvidas do aluno", e);
    }
}

    // 3) Professor assume a dúvida
    public void assumirDuvida(int idDuvida, int idProfessor) {

        String sql = """
            UPDATE duvida
            SET id_professor = ?, status_atendimento = 'Em Atendimento'
            WHERE id_duvida = ?
        """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfessor);
            stmt.setInt(2, idDuvida);

            stmt.executeUpdate();
            System.out.println("✅ Dúvida assumida pelo professor!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao assumir dúvida", e);
        }
    }

    // 4) Resolver dúvida
    public void resolverDuvida(int idDuvida, String resposta) {

    String sql = "UPDATE duvida SET status_atendimento = 'Resolvido', data_resolucao = NOW(), resposta = ? WHERE id_duvida = ?";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, resposta);
        stmt.setInt(2, idDuvida);

        stmt.executeUpdate();

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao resolver dúvida", e);
    }
  }

}