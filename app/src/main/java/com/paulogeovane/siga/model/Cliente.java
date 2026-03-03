package com.paulogeovane.siga.model;

/**
 * Representa o documento CLIENTS/{clientId} do Firestore (conforme descrito no artigo).
 */
public class Cliente {
    public String id;
    public String nome;
    public String tipo; // PF ou PJ
    public String cpfCnpj;
    public String email;
    public String telefone;
    public Endereco endereco;
    public long criadoEm;
    public long atualizadoEm;
    public String assinaturaAtivaId;

    public Cliente() {}

    public Cliente(String id, String nome, String tipo, String cpfCnpj, String email, String telefone,
                   Endereco endereco, long criadoEm, long atualizadoEm, String assinaturaAtivaId) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.assinaturaAtivaId = assinaturaAtivaId;
    }
}