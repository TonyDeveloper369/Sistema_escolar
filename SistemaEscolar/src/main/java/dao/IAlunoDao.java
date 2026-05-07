package dao;

import model.Aluno;

import java.util.List;

public interface IAlunoDao {
    //CRUD
    // C - create

    void salvar(Aluno aluno);
    // R - listar
    List<Aluno>listarTodosAlunos();
    //U - update
    void atualizarAluno(Aluno aluno);
    // D - delete
    void excluirAluno(int id);


}
