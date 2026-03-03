package com.paulogeovane.siga.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulogeovane.siga.R;
import com.paulogeovane.siga.model.Cliente;

import java.util.List;

/**
 * Adapter simples para mostrar clientes.
 */
public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.VH> {

    public interface AoClicarNoCliente {
        void onClick(Cliente cliente);
    }

    private final List<Cliente> clientes;
    private final AoClicarNoCliente listener;

    public ClienteAdapter(List<Cliente> clientes, AoClicarNoCliente listener) {
        this.clientes = clientes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        Cliente c = clientes.get(position);
        h.txtNome.setText(c.nome);
        h.txtDocumento.setText(c.tipo + " - " + c.cpfCnpj);
        h.txtEmail.setText(c.email);

        h.itemView.setOnClickListener(v -> listener.onClick(c));
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView txtNome, txtDocumento, txtEmail;

        VH(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDocumento = itemView.findViewById(R.id.txtDocumento);
            txtEmail = itemView.findViewById(R.id.txtEmail);
        }
    }
}