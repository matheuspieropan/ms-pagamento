package com.pieropan.pagamento.repository;

import com.pieropan.pagamento.entities.PagamentoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends CrudRepository<PagamentoEntity, Long> {

    boolean existsByPedidoId(Long id);
}