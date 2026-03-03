package com.paulogeovane.siga.auth;

/**
 * Interface para autenticação.
 * Depois poderá trocar por FirebaseAuth real sem mexer nas telas.
 */
public interface RepositorioAuth {
    boolean entrar(String email, String senha);
    void sair();
    boolean estaLogado();
    String emailAtual();
}
