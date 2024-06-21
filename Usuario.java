// src/models/Usuario.java
package models;

public class Usuario extends Pessoa {
    private String tipo;

    public Usuario(String nome, String ID, String tipo) {
        super(nome, ID);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + getNome() + '\'' +
                ", ID='" + getID() + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
