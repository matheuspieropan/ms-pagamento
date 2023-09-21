package com.pieropan.pagamento.repository;

import com.pieropan.pagamento.entities.PagamentoEntity;
import com.pieropan.pagamento.enums.StatusPedidoEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends CrudRepository<PagamentoEntity, Long> {
    List<PagamentoEntity> findByStatus(StatusPedidoEnum statusPedidoEnum);
}