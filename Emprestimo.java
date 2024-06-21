    // src/models/Emprestimo.java
    package models;
    
    import interfaces.Emprestavel;
    import java.util.Date;
    
    public class Emprestimo implements Emprestavel {
        private Livro livro;
        private Usuario usuario;
        private Date dataEmprestimo;
        private Date dataDevolucao;
    
        public Emprestimo(Livro livro, Usuario usuario) {
            this.livro = livro;
            this.usuario = usuario;
            this.dataEmprestimo = new Date();
            this.dataDevolucao = null;
            livro.setDisponivel(false);
        }
    
        @Override
        public void emprestar(Usuario usuario) {
            if (livro.isDisponivel()) {
                this.usuario = usuario;
                this.dataEmprestimo = new Date();
                this.dataDevolucao = null;
                livro.setDisponivel(false);
            } else {
                System.out.println("Livro já está emprestado.");
            }
        }
    
        @Override
        public void devolver() {
            this.dataDevolucao = new Date();
            livro.setDisponivel(true);
        }
    
        public Livro getLivro() {
            return livro;
        }
    
        public Date getDataDevolucao() {
            return dataDevolucao;
        }
    
        @Override
        public String toString() {
            return "Emprestimo{" +
                    "livro=" + livro +
                    ", usuario=" + usuario +
                    ", dataEmprestimo=" + dataEmprestimo +
                    ", dataDevolucao=" + dataDevolucao +
                    '}';
        }
    }
