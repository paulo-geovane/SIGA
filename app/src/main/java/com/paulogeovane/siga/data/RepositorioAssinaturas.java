package com.paulogeovane.siga.data;
import com.paulogeovane.siga.model.Assinatura;

public interface RepositorioAssinaturas {
    Assinatura buscarPorClienteId(String clienteId);
}
