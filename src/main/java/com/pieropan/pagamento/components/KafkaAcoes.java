package com.pieropan.pagamento.components;

import com.pieropan.pagamento.entities.Pedido;
import com.pieropan.pagamento.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KafkaAcoes {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "pedido-realizado-topic", groupId = "pedido_id", containerFactory = "pedidoListenerFactory")
    public void consumer(Pedido pedido) {
        pedido.setStatus(StatusPedidoEnum.getEnum(new Random().nextInt(2) + 1));
        notificar(pedido);
    }

    void notificar(Pedido pedido) {
        kafkaTemplate.send("pedido-pagamento-topic", pedido);
    }
}