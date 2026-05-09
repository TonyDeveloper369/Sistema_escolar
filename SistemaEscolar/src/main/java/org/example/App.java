package org.example;
import dao.IAlunoDao;
import daoImplements.AlunoDaoImplements;
import database.sqlConn;
import model.Aluno;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        sqlConn.testConnetion();

        AlunoDaoImplements alunoDaoMethods = new AlunoDaoImplements();
        Scanner input = new Scanner(System.in);

        int opcao;

        do{
            System.out.println("===== MENU =======");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Atualizar Aluno");
            System.out.println("3. Excluir Aluno");
            System.out.println("4. Listar Aluno");
            System.out.println("5. Buscar pelo id");
            System.out.println("0. Sair do Programa");

            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("===== CADASTRAR ALUNO =====");

                    System.out.print("Nome: ");
                    String nome = input.nextLine();

                    System.out.print("CPF: ");
                    String cpf = input.nextLine();

                    System.out.print("Email: ");
                    String email = input.nextLine();

                    System.out.print("Data nascimento (AAAA-MM-DD): ");
                    String data = input.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = input.nextLine();

                    Aluno novoAluno = new Aluno(
                            nome,
                            cpf,
                            email,
                            java.time.LocalDate.parse(data),
                            telefone
                    );

                    alunoDaoMethods.salvar(novoAluno);

                    break;

                case 2:
                    System.out.println("Atualizar Aluno");
                break;

                case 3:
                    System.out.println("Excluir Aluno");
                break;

                case 4:
                    System.out.println("Listar Aluno");
                    List<Aluno> todosAlunos = alunoDaoMethods.listarTodosAlunos();

                    if (todosAlunos.isEmpty()){
                        System.out.println("Nenhum aluno encontrado");
                    }else {
                        for (Aluno aluno : todosAlunos){
                            System.out.println(aluno);
                        }
                    }
                    break;

                case 5:
                    System.out.println("listar aluno por ID. informe um ID para pesquisar");
                    int idBusca = input.nextInt();
                    Optional<Aluno> alunoEncontrado = alunoDaoMethods.buscarPorId(idBusca);

                    if (alunoEncontrado.isPresent()){
                        System.out.println(alunoEncontrado);
                        System.out.println(alunoEncontrado.get());
                    }else{
                        System.out.println("Nenhum aluno encontrado" + idBusca + "Encontrado");
                    }
                    break;

            }

        }while(opcao != 0);

        input.close();
    }

}
