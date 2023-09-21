package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.PagamentoEntity;
import com.pieropan.pagamento.entities.PedidoEntity;
import com.pieropan.pagamento.enums.StatusPagamentoEnum;
import com.pieropan.pagamento.enums.StatusPedidoEnum;
import com.pieropan.pagamento.repository.PagamentoRepository;
import com.pieropan.pagamento.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Mensageria {

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    protected void processarPagamento(PedidoEntity pedido) throws InterruptedException {
        Thread.sleep(5000L);
        pagamentoRepository.save(criarPagamento(pedido));
        atualizarPedido(pedido);
    }

    void atualizarPedido(PedidoEntity pedido) {
        pedido.setStatus(StatusPedidoEnum.PAGAMENTO_PROCESSADO);
        pedidoRepository.save(pedido);
    }

    PagamentoEntity criarPagamento(PedidoEntity pedido) {
        return PagamentoEntity
                .builder()
                .status(StatusPagamentoEnum.APROVADO)
                .pedido(pedido).build();
    }
}