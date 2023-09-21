package com.pieropan.pagamento.repository;

import com.pieropan.pagamento.entities.PedidoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoEntity,Long> {
}