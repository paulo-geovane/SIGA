package com.paulogeovane.siga.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paulogeovane.siga.R;
import com.paulogeovane.siga.auth.RepositorioAuth;
import com.paulogeovane.siga.auth.RepositorioAuthFake;

/**
 * Tela de login fictício.
 * Mantém coerência com o artigo porque "simula Firebase Auth" em protótipo.
 */
public class LoginActivity extends AppCompatActivity {

    private RepositorioAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = new RepositorioAuthFake(this);

        // Se já estiver logado, pula direto para a lista de clientes
        if (auth.estaLogado()) {
            abrirListaClientes();
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtSenha = findViewById(R.id.edtSenha);
        Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(v -> {
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();

            boolean ok = auth.entrar(email, senha);
            if (ok) {
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                abrirListaClientes();
                finish();
            } else {
                Toast.makeText(this, "E-mail ou senha inválidos (demo).", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void abrirListaClientes() {
        startActivity(new Intent(this, ListaClientesActivity.class));
    }
}
