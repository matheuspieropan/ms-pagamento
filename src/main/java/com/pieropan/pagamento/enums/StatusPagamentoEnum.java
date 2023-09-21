package com.pieropan.pagamento.enums;

import java.util.Random;

public enum StatusPagamentoEnum {

    APROVADO,

    REPROVADO;

    public static StatusPagamentoEnum getRandomStatus() {
        int index = new Random().nextInt(StatusPagamentoEnum.values().length);
        return StatusPagamentoEnum.values()[index];
    }
}