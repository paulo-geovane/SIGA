package com.paulogeovane.siga.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paulogeovane.siga.R;
import com.paulogeovane.siga.auth.RepositorioAuth;
import com.paulogeovane.siga.auth.RepositorioAuthFake;
import com.paulogeovane.siga.data.RepositorioFirebaseFake;
import com.paulogeovane.siga.model.Cliente;

import java.util.List;

/**
 * Tela de listagem.
 * Puxa dados do RepositorioFirebaseFake (simula Firestore).
 */
public class ListaClientesActivity extends AppCompatActivity {

    private RepositorioAuth auth;
    private RepositorioFirebaseFake repo; // aqui está o "fake firebase"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = new RepositorioAuthFake(this);
        repo = RepositorioFirebaseFake.getInstancia();
        // Se não estiver logado, volta para login
        if (!auth.estaLogado()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_lista_clientes);

        RecyclerView rv = findViewById(R.id.rvClientes);
        Button btnSair = findViewById(R.id.btnSair);

        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Cliente> clientes = repo.listarClientes();

        ClienteAdapter adapter = new ClienteAdapter(clientes, cliente -> {
            // Ao clicar, abre detalhes
            Intent it = new Intent(this, DetalheClienteActivity.class);
            it.putExtra("clienteId", cliente.id);
            startActivity(it);
        });

        rv.setAdapter(adapter);

        btnSair.setOnClickListener(v -> {
            auth.sair();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}