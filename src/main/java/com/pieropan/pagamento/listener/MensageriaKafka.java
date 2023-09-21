package com.pieropan.pagamento.listener;

import com.pieropan.pagamento.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MensageriaKafka implements Mensageria {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "pedido-realizado-topic", groupId = "pedido_id", containerFactory = "pedidoListenerFactory")
    public void consumer(Pedido pedido) {
        processarPagamento(pedido);
        notificar(pedido);
    }

    @Override
    public void notificar(Pedido pedido) {
        kafkaTemplate.send("pedido-pagamento-topic", pedido);
    }
}