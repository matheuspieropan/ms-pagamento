package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.PagamentoEntity;
import com.pieropan.pagamento.entities.PedidoEntity;
import com.pieropan.pagamento.enums.StatusPedidoEnum;
import com.pieropan.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public abstract class Mensageria {

    @Autowired
    PagamentoRepository repository;

    protected void processarPagamento(PedidoEntity pedidoEntity) {
        try {
            Thread.sleep(5000L);
            repository.save(criarPagamentoEntity(pedidoEntity));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    PagamentoEntity criarPagamentoEntity(PedidoEntity pedidoEntity) {
        StatusPedidoEnum statusEnum = StatusPedidoEnum.getEnum(new Random().nextInt(2) + 1);
        return PagamentoEntity
                .builder()
                .status(statusEnum)
                .pedido(pedidoEntity).build();
    }
}