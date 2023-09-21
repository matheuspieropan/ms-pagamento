package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.PagamentoEntity;
import com.pieropan.pagamento.entities.PedidoEntity;
import com.pieropan.pagamento.enums.StatusPagamentoEnum;
import com.pieropan.pagamento.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
        return PagamentoEntity
                .builder()
                .status(StatusPagamentoEnum.APROVADO)
                .pedido(pedidoEntity).build();
    }
}