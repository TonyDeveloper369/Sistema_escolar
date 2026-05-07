package model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate dataNascimento;
    private String telefone;

    // Construtor para Criar um novo aluno
    public Aluno(String nome, String cpf, String email, LocalDate dataNascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }
    // Construtor para Criar um novo aluno
    public Aluno(int id, String nome, String cpf, String email, LocalDate dataNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    @Override
    public String toString(){
        return String.format(
                "Aluno: id=%d - nome=%s - cpf=%s - email=%s - nascimento=%s - telefone=%s",
                id, nome, cpf, email, dataNascimento, telefone
        );
    }
}
