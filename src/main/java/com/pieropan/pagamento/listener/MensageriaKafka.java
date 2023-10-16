package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.PedidoEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MensageriaKafka extends Mensageria {

    @KafkaListener(topics = "pedido-realizado-topic", groupId = "pedido_id", containerFactory = "pedidoListenerFactory")
    public void consumer(PedidoEntity pedidoEntity) {
        processarPagamento(pedidoEntity);
    }
}