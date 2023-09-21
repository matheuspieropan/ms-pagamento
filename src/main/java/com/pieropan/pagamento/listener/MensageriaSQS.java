package com.pieropan.pagamento.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pieropan.pagamento.entities.PedidoEntity;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class MensageriaSQS extends Mensageria {

    private final ObjectMapper objectMapper;

    public MensageriaSQS(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SqsListener("pedido-realizado-queue")
    public void consumer(Object obj) throws JsonProcessingException, InterruptedException {
        PedidoEntity pedidoEntity = objectMapper.readValue(obj.toString(), PedidoEntity.class);
        processarPagamento(pedidoEntity);
    }
}