// src/models/Pessoa.java
package models;

public abstract class Pessoa {
    private String nome;
    private String ID;

    public Pessoa(String nome, String ID) {
        this.nome = nome;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
