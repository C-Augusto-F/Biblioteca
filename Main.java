// src/main/Main.java
package main;

import models.Livro;
import models.Usuario;
import models.Emprestimo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Livro> livros;
    private HashMap<String, Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Main() {
        this.livros = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();
    }

    // Métodos para gerenciamento de livros
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(String ISBN) {
        livros.removeIf(livro -> livro.getISBN().equals(ISBN));
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    // Métodos para gerenciamento de usuários
    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getID(), usuario);
    }

    public void removerUsuario(String ID) {
        usuarios.remove(ID);
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario);
        }
    }

    // Métodos para gerenciamento de empréstimos
    public void realizarEmprestimo(String ISBN, String userID) {
        Livro livro = buscarLivroPorISBN(ISBN);
        Usuario usuario = usuarios.get(userID);

        if (livro != null && usuario != null && livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario);
            emprestimos.add(emprestimo);
            System.out.println("Empréstimo realizado com sucesso.");
        } else {
            System.out.println("Empréstimo não pode ser realizado.");
        }
    }

    public void registrarDevolucao(String ISBN) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getISBN().equals(ISBN) && emprestimo.getDataDevolucao() == null) {
                emprestimo.devolver();
                System.out.println("Livro devolvido com sucesso.");
                return;
            }
        }
        System.out.println("Empréstimo não encontrado.");
    }

    // Métodos de busca
    public Livro buscarLivroPorISBN(String ISBN) {
        for (Livro livro : livros) {
            if (livro.getISBN().equals(ISBN)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        List<Livro> resultados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    // Relatórios
    public void relatorioEmprestimos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println(emprestimo);
        }
    }

    public void relatorioLivrosDisponiveis() {
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println(livro);
            }
        }
    }

    // Menu interativo
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nSistema de Gerenciamento de Biblioteca");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Listar Livros");
            System.out.println("4. Registrar Usuário");
            System.out.println("5. Remover Usuário");
            System.out.println("6. Listar Usuários");
            System.out.println("7. Realizar Empréstimo");
            System.out.println("8. Registrar Devolução");
            System.out.println("9. Relatório de Empréstimos");
            System.out.println("10. Relatório de Livros Disponíveis");
            System.out.println("11. Buscar Livro por Título");
            System.out.println("12. Buscar Livro por Autor");
            System.out.println("0. Sair e Salvar Dados");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    adicionarLivro(new Livro(titulo, autor, isbn));
                    System.out.println("Livro adicionado com sucesso.");
                    break;
                case 2:
                    System.out.print("ISBN do Livro a ser removido: ");
                    String isbnRemover = scanner.nextLine();
                    removerLivro(isbnRemover);
                    System.out.println("Livro removido com sucesso.");
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Tipo de Usuário (Aluno/Professor): ");
                    String tipo = scanner.nextLine();
                    registrarUsuario(new Usuario(nome, id, tipo));
                    System.out.println("Usuário registrado com sucesso.");
                    break;
                case 5:
                    System.out.print("ID do Usuário a ser removido: ");
                    String idRemover = scanner.nextLine();
                    removerUsuario(idRemover);
                    System.out.println("Usuário removido com sucesso.");
                    break;
                case 6:
                    listarUsuarios();
                    break;
                case 7:
                    System.out.print("ISBN do Livro: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("ID do Usuário: ");
                    String idUsuario = scanner.nextLine();
                    realizarEmprestimo(isbnEmprestimo, idUsuario);
                    break;
                case 8:
                    System.out.print("ISBN do Livro a ser devolvido: ");
                    String isbnDevolucao = scanner.nextLine();
                    registrarDevolucao(isbnDevolucao);
                    break;
                case 9:
                    relatorioEmprestimos();
                    break;
                case 10:
                    relatorioLivrosDisponiveis();
                    break;
                case 11:
                    System.out.print("Título do Livro: ");
                    String tituloBusca = scanner.nextLine();
                    List<Livro> livrosPorTitulo = buscarLivrosPorTitulo(tituloBusca);
                    for (Livro livro : livrosPorTitulo) {
                        System.out.println(livro);
                    }
                    break;
                case 12:
                    System.out.print("Autor do Livro: ");
                    String autorBusca = scanner.nextLine();
                    List<Livro> livrosPorAutor = buscarLivrosPorAutor(autorBusca);
                    for (Livro livro : livrosPorAutor) {
                        System.out.println(livro);
                    }
                    break;
                case 0:
                    System.out.println("Salvando dados e saindo...");
                    salvarDados();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Método para salvar dados em um arquivo de texto
    public void salvarDados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("biblioteca_dados.txt"))) {
            writer.write("Livros:\n");
            for (Livro livro : livros) {
                writer.write(livro.toString() + "\n");
            }
            writer.write("\nUsuários:\n");
            for (Usuario usuario : usuarios.values()) {
                writer.write(usuario.toString() + "\n");
            }
            writer.write("\nEmpréstimos:\n");
            for (Emprestimo emprestimo : emprestimos) {
                writer.write(emprestimo.toString() + "\n");
            }
            System.out.println("Dados salvos com sucesso em biblioteca_dados.txt");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Main biblioteca = new Main();
        biblioteca.exibirMenu();
    }
}
