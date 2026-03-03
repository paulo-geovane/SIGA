package com.paulogeovane.siga.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.paulogeovane.siga.R;
import com.paulogeovane.siga.data.RepositorioFirebaseFake;
import com.paulogeovane.siga.model.Assinatura;
import com.paulogeovane.siga.model.Cliente;

/**
 * Mostra os dados do cliente + status da assinatura (ativa/inadimplente etc.).
 */
public class DetalheClienteActivity extends AppCompatActivity {

    private RepositorioFirebaseFake repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);

        repo = RepositorioFirebaseFake.getInstancia();
        String clienteId = getIntent().getStringExtra("clienteId");

        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtDoc = findViewById(R.id.txtDoc);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtTelefone = findViewById(R.id.txtTelefone);
        TextView txtEndereco = findViewById(R.id.txtEndereco);
        TextView txtStatus = findViewById(R.id.txtStatus);
        TextView txtPlano = findViewById(R.id.txtPlano);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        Cliente c = repo.buscarPorId(clienteId);
        Assinatura a = repo.buscarPorClienteId(clienteId);

        if (c != null) {
            txtNome.setText("Nome: " + c.nome);
            txtDoc.setText("Documento: " + c.tipo + " - " + c.cpfCnpj);
            txtEmail.setText("E-mail: " + c.email);
            txtTelefone.setText("Telefone: " + c.telefone);
            txtEndereco.setText("Endereço: " + (c.endereco != null ? c.endereco.formatado() : "Não informado"));
        }

        if (a != null) {
            txtStatus.setText("Status: " + a.status.toUpperCase());
            txtPlano.setText("Plano: " + a.planoId);
        }

        btnVoltar.setOnClickListener(v -> finish());
    }
}