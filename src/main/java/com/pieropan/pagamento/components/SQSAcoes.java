package com.pieropan.pagamento.components;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pieropan.pagamento.entities.Pedido;
import com.pieropan.pagamento.enums.StatusPedidoEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SQSAcoes {

    @Value("${pedido-pagamento-queue}")
    String pedidoPagamentoQueue;

    private final ObjectMapper objectMapper;

    private final AmazonSQS amazonSQS;

    public SQSAcoes(ObjectMapper objectMapper, AmazonSQS amazonSQS) {
        this.objectMapper = objectMapper;
        this.amazonSQS = amazonSQS;
    }

    @SqsListener("pedido-realizado-queue")
    public void consumer(Object obj) throws JsonProcessingException, InterruptedException {
        Pedido pedido = objectMapper.readValue(obj.toString(), Pedido.class);
        processarPagamento(pedido);

        notificar(pedido);
    }

    void processarPagamento(Pedido pedido) throws InterruptedException {
        Thread.sleep(10000L);
        pedido.setStatus(StatusPedidoEnum.getEnum(new Random().nextInt(2) + 1));
    }

    void notificar(Pedido pedido) throws JsonProcessingException {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(
                pedidoPagamentoQueue, objectMapper.writeValueAsString(pedido));
        amazonSQS.sendMessage(sendMessageRequest);
    }
}