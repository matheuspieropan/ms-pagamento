package com.pieropan.pagamento.listener;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pieropan.pagamento.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Component
public class MensageriaSQS implements Mensageria {

    @Value("${pedido-pagamento-queue}")
    String pedidoPagamentoQueue;

    private final ObjectMapper objectMapper;

    @Autowired
    AmazonSQS amazonSQS;

    public MensageriaSQS(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @SqsListener("pedido-realizado-queue")
    public void consumer(Object obj) throws JsonProcessingException {
        Pedido pedido = objectMapper.readValue(obj.toString(), Pedido.class);
        processarPagamento(pedido);

        notificar(pedido);
    }

    @Override
    public void notificar(Pedido pedido) {
        try {
            SendMessageRequest sendMessageRequest =
                    new SendMessageRequest(pedidoPagamentoQueue, objectMapper.writeValueAsString(pedido));
            amazonSQS.sendMessage(sendMessageRequest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}