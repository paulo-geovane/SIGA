package com.paulogeovane.siga.data;

import com.paulogeovane.siga.model.Assinatura;
import com.paulogeovane.siga.model.Cliente;
import com.paulogeovane.siga.model.Endereco;

import java.util.*;

/**
 * Simula o Firestore com dados fictícios.
 * pode ser expandido depois (pagamentos, boletos, etc.).
 */
public class RepositorioFirebaseFake implements RepositorioClientes, RepositorioAssinaturas {

    // ✅ Instância única (Singleton)
    private static RepositorioFirebaseFake instancia;

    public static synchronized RepositorioFirebaseFake getInstancia() {
        if (instancia == null) {
            instancia = new RepositorioFirebaseFake();
        }
        return instancia;
    }

    private final Map<String, Cliente> clientes = new HashMap<>();
    private final Map<String, Assinatura> assinaturaPorCliente = new HashMap<>();

    // ✅ Construtor privado: impede new() em outras telas
    private RepositorioFirebaseFake() {
        popularDados();
    }

    private void popularDados() {
        long agora = System.currentTimeMillis();

        // Cliente 1 (ATIVA)
        String c1 = UUID.randomUUID().toString();
        String a1 = UUID.randomUUID().toString();
        clientes.put(c1, new Cliente(
                c1,
                "João da Silva",
                "PF",
                "123.456.789-00",
                "joao@email.com",
                "(69) 99999-1111",
                new Endereco("Rua A", "100", "Ouro Preto do Oeste", "RO", "76920-000"),
                agora - dias(5),
                agora - dias(1),
                a1
        ));
        assinaturaPorCliente.put(c1, new Assinatura(
                a1, c1, "plano_mensal",
                agora - dias(5),
                agora + dias(25),
                "ativa",
                3,
                agora + dias(25)
        ));

        // Cliente 2 (INADIMPLENTE)
        String c2 = UUID.randomUUID().toString();
        String a2 = UUID.randomUUID().toString();
        clientes.put(c2, new Cliente(
                c2,
                "Oficina Ropa LTDA",
                "PJ",
                "12.345.678/0001-90",
                "contato@ropa.com",
                "(69) 98888-2222",
                new Endereco("Av. Central", "500", "Ji-Paraná", "RO", "76900-000"),
                agora - dias(40),
                agora - dias(2),
                a2
        ));
        assinaturaPorCliente.put(c2, new Assinatura(
                a2, c2, "plano_mensal",
                agora - dias(40),
                agora + dias(20),
                "inadimplente",
                3,
                agora - dias(2) // simula cobrança vencida
        ));
    }

    private long dias(int d) {
        return d * 24L * 60L * 60L * 1000L;
    }

    @Override
    public List<Cliente> listarClientes() {
        // Retorna em lista (ordenada por nome só para ficar bonito)
        List<Cliente> lista = new ArrayList<>(clientes.values());
        Collections.sort(lista, (a, b) -> a.nome.compareToIgnoreCase(b.nome));
        return lista;
    }

    @Override
    public Cliente buscarPorId(String clienteId) {
        return clientes.get(clienteId);
    }

    @Override
    public Assinatura buscarPorClienteId(String clienteId) {
        return assinaturaPorCliente.get(clienteId);
    }
}
