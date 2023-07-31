package com.nttdata.bootcam.banca.transaction.contrato;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.nttdata.bootcam.banca.transaction.repository.dao.TransactionDAO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Contrato entre productos y transaction
 * para validaciones de las cuentas de clientes
 */
public interface CuentaClienteProductoRepository extends ReactiveMongoRepository<TransactionDAO,String>{
	 Mono<TransactionDAO> findByIdClientAndIdProduct(String idClient, String idProduct);
}
