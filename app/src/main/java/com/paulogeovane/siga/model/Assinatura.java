package com.paulogeovane.siga.model;

/**
 * Representa SUBSCRIPTIONS/{subscriptionId} (conforme descrito no artigo).
 */
public class Assinatura {
    public String id;
    public String clienteId;
    public String planoId;

    public long inicio;
    public long fim;

    // status ∈ {trial, ativa, pendente, inadimplente, suspensa, cancelada}
    public String status;

    public int diasCarencia;
    public long proximaCobrancaEm;

    public Assinatura() {}

    public Assinatura(String id, String clienteId, String planoId, long inicio, long fim,
                      String status, int diasCarencia, long proximaCobrancaEm) {
        this.id = id;
        this.clienteId = clienteId;
        this.planoId = planoId;
        this.inicio = inicio;
        this.fim = fim;
        this.status = status;
        this.diasCarencia = diasCarencia;
        this.proximaCobrancaEm = proximaCobrancaEm;
    }
}
