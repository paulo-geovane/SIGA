package com.paulogeovane.siga.data;

import com.paulogeovane.siga.model.Cliente;

import java.util.List;

public interface RepositorioClientes {
    List<Cliente> listarClientes();
    Cliente buscarPorId(String clienteId);
}
