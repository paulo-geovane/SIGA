package com.paulogeovane.siga.model;

/**
     * Representa o endereço do cliente (equivalente ao objeto address{...} do artigo).
     */
    public class Endereco {
        public String rua;
        public String numero;
        public String cidade;
        public String estado;
        public String cep;

        public Endereco() {}

        public Endereco(String rua, String numero, String cidade, String estado, String cep) {
            this.rua = rua;
            this.numero = numero;
            this.cidade = cidade;
            this.estado = estado;
            this.cep = cep;
        }

        public String formatado() {
            return rua + ", " + numero + " - " + cidade + "/" + estado + " - CEP: " + cep;
        }
}
