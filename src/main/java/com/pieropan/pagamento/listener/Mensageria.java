package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.Pedido;
import com.pieropan.pagamento.enums.StatusPedidoEnum;

import java.util.Random;

public interface Mensageria {

    default void processarPagamento(Pedido pedido) {
        try {
            Thread.sleep(10000L);
            pedido.setStatus(StatusPedidoEnum.getEnum(new Random().nextInt(2) + 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void notificar(Pedido pedido);
}