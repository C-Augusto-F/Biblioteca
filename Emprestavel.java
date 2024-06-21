// src/interfaces/Emprestavel.java
package interfaces;

import models.Usuario;

public interface Emprestavel {
    void emprestar(Usuario usuario);
    void devolver();
}
