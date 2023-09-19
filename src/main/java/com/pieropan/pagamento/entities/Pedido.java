package com.pieropan.pagamento.entities;


import com.pieropan.pagamento.enums.StatusPedidoEnum;

import java.time.LocalDateTime;

public class Pedido {

    private Long id;

    private String pessoa;

    private int quantidadeItens;

    private LocalDateTime dataPedido;

    private StatusPedidoEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(int quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public StatusPedidoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPedidoEnum status) {
        this.status = status;
    }

    public Pedido() {
    }

    public Pedido(Long id, String pessoa, int quantidadeItens, LocalDateTime dataPedido, StatusPedidoEnum status) {
        this.id = id;
        this.pessoa = pessoa;
        this.quantidadeItens = quantidadeItens;
        this.dataPedido = dataPedido;
        this.status = status;
    }
}