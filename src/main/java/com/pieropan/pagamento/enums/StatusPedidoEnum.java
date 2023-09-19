package com.pieropan.pagamento.enums;

import java.util.Arrays;

public enum StatusPedidoEnum {

    APROVADO(1),

    REPROVADO(2),

    PENDENTE_PAGAMENTO(3),

    SEM_INTEGRACAO(4);

    private int codigo;

    StatusPedidoEnum(int codigo) {
        this.codigo = codigo;
    }

    public static StatusPedidoEnum getEnum(int codigo) {
        return Arrays.stream(StatusPedidoEnum.values()).filter(e -> e.getCodigo() == codigo).findFirst().get();
    }

    public int getCodigo() {
        return codigo;
    }
}