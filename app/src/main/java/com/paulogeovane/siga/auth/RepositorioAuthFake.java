package com.paulogeovane.siga.auth;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Implementação fake (simula Firebase Auth).
 * - Valida um usuário fixo (admin@siga.com / 123456)
 * - Guarda sessão em SharedPreferences
 */
public class RepositorioAuthFake implements RepositorioAuth {

    private static final String PREF = "siga_auth";
    private static final String CHAVE_EMAIL = "email_logado";

    // Credenciais de demonstração (login fictício)
    private static final String EMAIL_DEMO = "admin@siga.com";
    private static final String SENHA_DEMO = "123456";

    private final SharedPreferences sp;

    public RepositorioAuthFake(Context context) {
        sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    @Override
    public boolean entrar(String email, String senha) {
        // Simula validação de credenciais como se fosse Firebase Auth
        if (email != null && senha != null
                && EMAIL_DEMO.equalsIgnoreCase(email.trim())
                && SENHA_DEMO.equals(senha)) {

            sp.edit().putString(CHAVE_EMAIL, email.trim()).apply();
            return true;
        }
        return false;
    }

    @Override
    public void sair() {
        sp.edit().remove(CHAVE_EMAIL).apply();
    }

    @Override
    public boolean estaLogado() {
        return sp.contains(CHAVE_EMAIL);
    }

    @Override
    public String emailAtual() {
        return sp.getString(CHAVE_EMAIL, null);
    }
}
