package com.pieropan.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PagamentoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagamentoApplication.class, args);
    }
}