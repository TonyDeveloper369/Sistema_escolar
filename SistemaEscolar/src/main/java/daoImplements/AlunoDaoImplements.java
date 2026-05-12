package daoImplements;

import dao.IAlunoDao;
import database.sqlConn;
import model.Aluno;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AlunoDaoImplements implements IAlunoDao {

    @Override
    public void salvar(Aluno aluno) {
        String sql =
                "INSERT INTO aluno(instituicao_id, nome, cpf, email, dataNascimento, telefone) VALUES (?, ?, ?, ?, ?, ?)";;
        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4,
                    java.sql.Date.valueOf(aluno.getDataNascimento()));
            stmt.setString(5, aluno.getTelefone());

            stmt.executeUpdate();

            System.out.println("Aluno salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public Optional<Aluno> buscarPorId(int id){
        String sql = "SELECT * FROM Aluno WHERE idAluno = ?";

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                Aluno aluno = new Aluno(rs.getInt("idAluno"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("email"), rs.getDate("dataNascimento").toLocalDate(), rs.getString("telefone"));
                return Optional.of(aluno);
            }

        }catch (SQLException ex){
            System.out.println("Erro ao buscar aluno" + ex.getMessage());
        }
        return Optional.empty();
    }
    @Override
    public List<Aluno> listarTodosAlunos() {
        String sql = "SELECT * FROM Aluno ORDER BY nome ASC";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(rs.getInt("idAluno"), rs.getString("nome"),
                        rs.getString("cpf"), rs.getString("email"), rs.getDate("dataNascimento").toLocalDate(), rs.getString("telefone")));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao lista os alunos: " + e.getMessage());
        }
        return alunos;
    }

    @Override
    public void atualizarAluno(Aluno aluno) {
        String sql = """
        UPDATE aluno
        SET
            instituicao_id = ?,
            nome = ?,
            cpf = ?,
            email = ?,
            telefone = ?,
            dataNascimento = ?
        WHERE idAluno = ?
    """;
        try (
                Connection conn = sqlConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, aluno.getId());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getCpf());
            stmt.setString(4, aluno.getEmail());
            stmt.setString(5, aluno.getTelefone());

            stmt.setDate(
                    6,
                    java.sql.Date.valueOf(aluno.getDataNascimento())
            );

            stmt.setInt(7, aluno.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {

                System.out.println("Aluno atualizado com sucesso!");

            } else {

                System.out.println("Nenhum aluno encontrado.");
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao atualizar aluno",
                    e
            );
        }


    }

    @Override
    public void excluirAluno(int id) {
        String sql = "DELETE FROM aluno WHERE idAluno = ?";

        try (
                Connection conn = sqlConn.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {

                System.out.println("Aluno deletado com sucesso!");

            } else {

                System.out.println("Aluno nao encontrado.");
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Erro ao deletar aluno",
                    e
            );
        }

    }
}
